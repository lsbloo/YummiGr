package com.com.yummigr.services;

import com.com.yummigr.models.*;
import com.com.yummigr.repositories.LoggerSenderRepository;
import com.com.yummigr.stack.StackActivatorSMS;
import com.com.yummigr.toolkit.core.ActivatorScheduleSMS;
import com.com.yummigr.toolkit.models.AuthorizationSMSFacilita;
import com.com.yummigr.util.MyCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.com.yummigr.context.HandlerAuthentication;
import com.com.yummigr.repositories.ContactsRepository;
import com.com.yummigr.repositories.MessengerRepository;
import com.com.yummigr.repositories.ScheduleRepository;
import com.com.yummigr.stack.StackActivator;
import com.com.yummigr.toolkit.core.ActivatorScheduleEmail;
import com.com.yummigr.validator.MessengerValidator;
import com.com.yummigr.validator.UserValidator;
import com.com.yummigr.validator.core.Result;
import com.com.yummigr.validator.core.ValidatorBuilder;
import com.com.yummigr.toolkit.core.ActivatorScheduleEmailSp;

import javax.validation.constraints.Null;

/**
 * all interactions of services(messenger-connector) and controlls ; 
 * @author osvaldoairon
 *
 */
@Service
public class MessengerService {

	/**
	 * messengerRepository
	 */
	private final MessengerRepository messengerRepository;
	
	/**
	 * MessengerValidator();
	 */
	private final MessengerValidator messengerValidator;
	
	private final ContactsRepository contactsRepository;
	
	private final ScheduleRepository scheduleRepository;

	private final LoggerSenderRepository loggerSenderRepository;
	
	/**
	 */
	private final UserService userService;
	
	private final UserValidator userValidator;
	
	
	private HashMap<Boolean,Integer> hashMapVerificUpdate;
	
	protected ActivatorScheduleEmail activatorEmail;
	protected ActivatorScheduleSMS activatorSMS;
	private AuthorizationSMSFacilita auth;

	private ActivatorScheduleEmailSp activatorScheduleEmailSp;

	private MyCalendar myCalendar;

	private HandlerAuthentication handlerAuthentication;
	
	protected  static StackActivator stack =  new StackActivator(300000);
	protected  static StackActivatorSMS stack_sms = new StackActivatorSMS();


	@Autowired
	private MessengerService( MessengerRepository menssengerRepository , MessengerValidator messengerValidator
			,UserService userService, UserValidator userValidator, ContactsRepository contactsRepository
			, ScheduleRepository scheduleRepository , LoggerSenderRepository loggerSenderRepository1) {
		
		this.messengerRepository= menssengerRepository;
		this.messengerValidator=messengerValidator;
		this.userService = userService;
		this.userValidator=userValidator;
		this.scheduleRepository=scheduleRepository;
		this.hashMapVerificUpdate = new HashMap<Boolean,Integer>();
		this.contactsRepository =contactsRepository;
		this.loggerSenderRepository=loggerSenderRepository1;
	}
	
	
	public List<Messenger> getAllMessengers(){
		return this.messengerRepository.getAllMessenger();
	}
	
	
	
	public boolean createConnectorMessenger(String identifieruser, String account_sid , String auth_token) {
		Messenger m = new Messenger(account_sid,auth_token);
		
		Result result = new ValidatorBuilder<Messenger>().apply(messengerValidator.checkIfMessengerHasAssociate()).validate(m);
		
		if(result.ok()) {
			User u = this.userService.getUserByIdentifier(identifieruser);
			if(u != null) {
				Integer id = this.messengerRepository.getIdMessengerEntity(u.getId());
				try {
					if(id != 0 || id == null) {
						this.messengerRepository.save(m);
						this.messengerRepository.insertRelationMessengerUser(u.getId(), m.getId());
						result.ok();
						return true;
					}else {
						result.error("associate exist;");
						return false;
						
					}
				}catch(NullPointerException e) {
					this.messengerRepository.save(m);
					this.messengerRepository.insertRelationMessengerUser(u.getId(), m.getId());
					result.ok();
					return true;
				}
			}
		}else {
			return false;
		}
		
		return true;
		
	}
	
	public boolean  getMessengerConnectorUser(String identifier) {
		
		User u = this.userService.getUserByIdentifier(identifier);
		Result result = new ValidatorBuilder<User>().apply(userValidator.checkIfExistUserByIdentifier())
				.apply(userValidator.checkIfUserHaveAssociateConnectorMessage()).validate(u);
		if(result.ok()) {
			result.ok();
			return true;
		}
		else {
			result.error("dont have associate or not exist user");
			return false;
		}
		
	}
	
	public Messenger searchConnectorMessengerUser(String identifier) {
		User u = this.userService.getUserByIdentifier(identifier);
		if(u!=null) {
			try {
				Integer messenger_id = this.userService.getMessengerIdByIdUser(u.getId());
				if (messenger_id != 0 ) {
					Messenger e = this.messengerRepository.getMessengerEntity(Long.valueOf(messenger_id));
					if (e != null) {
						return e;
					}
				}
			}catch(NullPointerException e){
				return null;
			}
		}
		return null;
		
	}
	public boolean actionDeleteContact(String identifier , String email, String phone_number){
		Messenger u = this.searchConnectorMessengerUser(identifier);
		if(u!=null){
			String id = this.getContactId(email,phone_number,identifier);
			if(id!=null && !id.isEmpty() && id != "null") {
				this.contactsRepository.deleteContactRelation(Long.valueOf(id));
				this.contactsRepository.deleteContact(Long.valueOf(id));
				return true;
			}
		}
		return false;
	}
	public String getContactId(String email, String phone_number,String identifier){
		Messenger u = this.searchConnectorMessengerUser(identifier);
		String id = String.valueOf(this.contactsRepository.getContactsByEmailId(email,phone_number));
		if(id!=null && !id.isEmpty() && id != "null") {
			Long messenger_id = this.contactsRepository.getMessengerId(Long.valueOf(id));
			if (messenger_id != null) {
				if (messenger_id == u.getId()) {
					return (id);
				} else {
					return null;
				}
			}
		}
		return null;

	}
	
	public Integer insertRelationScheduleConnectorMessenger(Messenger u , Schedule sh) {
		return this.messengerRepository.updateMessengerConnectorSchedule(sh.getId(), u.getId());
	}
	
	public HashMap<Boolean,Integer> verificExistRelationScheduleConnectorMessenger(Messenger u) {
		try{
			Integer result  = this.messengerRepository.getConnectorIdByUser(u.getId());
			
			if(result!=0) {
				this.hashMapVerificUpdate.put(true, result);
				return this.hashMapVerificUpdate;
			}else {
				this.hashMapVerificUpdate.put(false, 0);
				return this.hashMapVerificUpdate;
			}
		}catch(NullPointerException e) {
			this.hashMapVerificUpdate.put(false, 0);
			return this.hashMapVerificUpdate;
		}
	}
	
	
	public List<Contacts> getAllContactsForMessenger(Messenger u ){
		try {
			List<Integer> ids_related_contact = this.contactsRepository.getAllIdContactByMessengerId(u.getId());
			List<Contacts> list_ = new ArrayList<Contacts>();
			for (Integer y : ids_related_contact) {
				list_.add(this.contactsRepository.getContact(y));
			}

			return list_;
		}catch(NullPointerException e){
			return null;
		}
	}

	
	
	public String activateSendSMSMessengerAllContactsPreDetermined(String identifier,String username,String password,boolean actived) throws Exception {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		if(u != null){
			this.myCalendar = new MyCalendar();
			String date = this.myCalendar.getDateToday();
			String[] result = this.myCalendar.getFormatedDataToday(date);
			LoggerSender l_sender = new LoggerSender(result[0],result[1],result[2],result[3],"sms");
			auth = new AuthorizationSMSFacilita(username,password);
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorSMS = new ActivatorScheduleSMS(sh,this,u,auth,true,null,null);
			LoggerSender ss = this.loggerSenderRepository.save(l_sender);
			stack_sms.push(activatorSMS);
			stack_sms.print();
			insertRelationContactsSenderEmailBroadCast(getAllContactsForMessenger(u),ss);
			return this.activatorSMS.getResponseExecution();
		}
		
		return null;
	}

	public String activateSendSMSMessengerAllContacts(String identifier,String username, String password,boolean activate,String subject_message,String message) throws Exception {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		if(u != null){
			this.myCalendar = new MyCalendar();
			String date = this.myCalendar.getDateToday();
			String[] result = this.myCalendar.getFormatedDataToday(date);
			LoggerSender l_sender = new LoggerSender(result[0],result[1],result[2],result[3],"sms");
			auth = new AuthorizationSMSFacilita(username,password);
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorSMS = new ActivatorScheduleSMS(sh,this,u,auth,true,subject_message,message);
			LoggerSender ss = this.loggerSenderRepository.save(l_sender);
			stack_sms.push(activatorSMS);
			stack_sms.print();
			insertRelationContactsSenderEmailBroadCast(getAllContactsForMessenger(u),ss);
			return this.activatorSMS.getResponseExecution();
		}
		return null;
	}

	public String activateSendEmailMessengerAllPredetermined(JavaMailSender sender , String identifier, boolean activate, String email, String password) throws Exception {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		if(u != null) {
			this.myCalendar = new MyCalendar();
			String date = this.myCalendar.getDateToday();
			String[] result = this.myCalendar.getFormatedDataToday(date);
			LoggerSender l_sender = new LoggerSender(result[0],result[1],result[2],result[3],"email");
			handlerAuthentication = new HandlerAuthentication();
			User user = handlerAuthentication.getUserAuthenticate();
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorEmail = new ActivatorScheduleEmail(sender,this,sh,u,activate, user, email,password,null,null);
			LoggerSender ss = this.loggerSenderRepository.save(l_sender);
			stack.push(activatorEmail);
			stack.print();
			insertRelationContactsSenderEmailBroadCast(getAllContactsForMessenger(u),ss);
			return this.activatorEmail.getResponseExecution();
		}
		return null;
	}

	public String activateSendEmailMessengerAll(JavaMailSender sender , String identifier , boolean activate, String email,String password, String message,String subject_message) throws Exception {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		if(u != null) {
			this.myCalendar = new MyCalendar();
			String date = this.myCalendar.getDateToday();
			String[] result = this.myCalendar.getFormatedDataToday(date);
			LoggerSender l_sender = new LoggerSender(result[0],result[1],result[2],result[3],"email");
			handlerAuthentication = new HandlerAuthentication();
			User user = handlerAuthentication.getUserAuthenticate();
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorEmail = new ActivatorScheduleEmail(sender,this,sh,u,activate, user, email,password,message,subject_message);
			LoggerSender ss = this.loggerSenderRepository.save(l_sender);
			stack.push(activatorEmail);
			stack.print();
			insertRelationContactsSenderEmailBroadCast(getAllContactsForMessenger(u),ss);
			return this.activatorEmail.getResponseExecution();
		}else {
			return null;
		}
	}
	
	public void insertRelationContactsSenderEmailBroadCast(List<Contacts> broads,LoggerSender sender){
		for(Contacts c : broads){
			this.contactsRepository.insertRelationContactsSenderLogger(c.getId(),sender.getId());
		}
	}
	public void insertRelationContactsSenderEmailSelect(List<Contacts> select , LoggerSender sender){
		for(Contacts c : select){
			this.contactsRepository.insertRelationContactsSenderLogger(c.getId(),sender.getId());
		}
	}

	public List<Long> getSelectEmails(String contactsSelect,Messenger u){
		List<String> result = new ArrayList<String>();
		String[] rr = contactsSelect.split(",");
		for(int i = 0 ; i < rr.length; i++){
			result.add(rr[i]);
		}
		List<Contacts> cc = this.getAllContactsForMessenger(u);
		List<Long> exit = new ArrayList<Long>();
		for(String r : result) {
			Long id = checkContactSelected(r,cc);
			if(id!=null) {
				exit.add(id);
			}
		}
		return exit;
	}
	public Long checkContactSelected(String result , List<Contacts> cc) {
		for(Contacts c : cc) {
			if(c.getEmail() .equals(result)) {
				return c.getId();
			}
		}
		return null;
	}

	
	public String activateSendEmailMessengerSelectedContactsPredetermined(JavaMailSender sender , String identifier , boolean activate , String email, String password,String contacts_selected) throws Exception {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		if(u != null) {
			List<Contacts> contactsList = getContactsByLisString(getSelectEmails(contacts_selected,u));
			this.myCalendar = new MyCalendar();
			String date = this.myCalendar.getDateToday();
			String[] result = this.myCalendar.getFormatedDataToday(date);
			LoggerSender l_sender = new LoggerSender(result[0],result[1],result[2],result[3],"email");
			handlerAuthentication = new HandlerAuthentication();
			User user = handlerAuthentication.getUserAuthenticate();
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorScheduleEmailSp = new ActivatorScheduleEmailSp(sender,this,sh,u,activate, user, email,password,null,null,contactsList);
			LoggerSender ss = this.loggerSenderRepository.save(l_sender);
			stack.push(activatorScheduleEmailSp);
			stack.print();
			insertRelationContactsSenderEmailBroadCast(contactsList,ss);
			return this.activatorScheduleEmailSp.getResponseExecution();
		}
		
		return null;
	}
	
	public  String activateSendEmailMessengerSelectedContacts(JavaMailSender sender , String identifier , boolean activate , String email, String password , String message , String subject_message
	,String contacts_selected) throws Exception{
		Messenger u = this.searchConnectorMessengerUser(identifier);
		List<Contacts> contactsList = getContactsByLisString(getSelectEmails(contacts_selected,u));
		if(u != null){
			this.myCalendar = new MyCalendar();
			String date = this.myCalendar.getDateToday();
			String[] result = this.myCalendar.getFormatedDataToday(date);
			LoggerSender l_sender = new LoggerSender(result[0],result[1],result[2],result[3],"email");
			handlerAuthentication = new HandlerAuthentication();
			User user = handlerAuthentication.getUserAuthenticate();
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorScheduleEmailSp = new ActivatorScheduleEmailSp(sender,this,sh,u,activate, user, email,password,message,subject_message,contactsList);
			LoggerSender ss = this.loggerSenderRepository.save(l_sender);
			stack.push(activatorScheduleEmailSp);
			stack.print();
			insertRelationContactsSenderEmailBroadCast(contactsList,ss);
			return this.activatorScheduleEmailSp.getResponseExecution();
		}

		return null;
	}

	public List<Contacts> getContactsByLisString(List<Long> contacts_id){
		List<Contacts> result = new ArrayList<Contacts>();
		for(Long r : contacts_id) {
			Contacts c = this.contactsRepository.getContactById(r);
			result.add(c);
		}
		return result;
	}

	public ActivatorScheduleEmail ActivatorGetSchedule(String  identifier ) {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		return stack.getSchedule(u);
	}
	
	public ActivatorScheduleSMS ActivatorGetSMSSch(String identifier) {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		return stack_sms.getSchedule(u);
	}
	
	
	public List<String> stopActivatorSMS(ActivatorScheduleSMS e){
		List<String> list_ = new ArrayList<String>();
		list_.add(String.valueOf(e.isCancelled())); // status cancel 0
		list_.add(e.getMessengerConnector().getAccount_sid()); // account_sid messenger connector; 1
		list_.add(e.getResponseExecution()); // response execution 2
		list_.add(e.getName()); // name of thread object; 3
		e.destroyActivator();
		stack_sms.pop(e);
		return list_;
		
		
	}
	public List<String>stopActivatorSch(ActivatorScheduleEmail act) {
		List<String> list_ = new ArrayList<String>();
		list_.add(String.valueOf(act.isCancelled())); // status cancel 0
		list_.add(act.getMessenger().getAccount_sid()); // account_sid messenger connector; 1
		list_.add(act.getResponseExecution()); // response execution 2
		list_.add(act.getName()); // name of thread object; 3
		act.destroyActivator();
		stack.pop(act);
		return list_;
		/**
		 * Depreciado.
		 */
		//act.stop();
	}
	
	
	
	
	}
