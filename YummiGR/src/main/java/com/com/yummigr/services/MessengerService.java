package com.com.yummigr.services;

import com.com.yummigr.repositories.LoggerSenderRepository;
import com.com.yummigr.stack.StackActivatorSMS;
import com.com.yummigr.toolkit.core.ActivatorScheduleSMS;
import com.com.yummigr.toolkit.models.AuthorizationSMSFacilita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.com.yummigr.context.HandlerAuthentication;
import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;
import com.com.yummigr.models.User;
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
			Integer messenger_id = this.userService.getMessengerIdByIdUser(u.getId());
		return this.messengerRepository.getMessengerEntity(Long.valueOf(messenger_id));
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
		List<Integer> ids_related_contact = this.contactsRepository.getAllIdContactByMessengerId(u.getId());
		List<Contacts> list_ = new ArrayList<Contacts>();
		for(Integer y : ids_related_contact) {
			list_.add(this.contactsRepository.getContact(y));
		}
		
		return list_;
	}


	public String activateSendSMSMessengerAllContacts(String identifier,String username, String password,boolean activate,String subject_message,String message) throws Exception {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		if(u != null){
			auth = new AuthorizationSMSFacilita(username,password);
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorSMS = new ActivatorScheduleSMS(sh,this,u,auth,true,subject_message,message);
			stack_sms.push(activatorSMS);
			stack_sms.print();
			return this.activatorSMS.getResponseExecution();
		}
		return null;
	}

	public String activateSendEmailMessengerAll(JavaMailSender sender , String identifier , boolean activate, String email,String password, String message,String subject_message) throws Exception {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		if(u != null) {
			handlerAuthentication = new HandlerAuthentication();
			User user = handlerAuthentication.getUserAuthenticate();
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorEmail = new ActivatorScheduleEmail(sender,this,sh,u,activate, user, email,password,message,subject_message);
			stack.push(activatorEmail);
			stack.print();
			return this.activatorEmail.getResponseExecution();
		}else {
			return null;
		}
	}

	public List<String> getSelectEmails(String contactsSelect){
		List<String> result = new ArrayList<String>();
		String[] rr = contactsSelect.split(",");
		for(int i = 0 ; i < rr.length; i++){
			result.add(rr[i]);
		}
		return result;
	}

	public  String activateSendEmailMessengerSelectedContacts(JavaMailSender sender , String identifier , boolean activate , String email, String password , String message , String subject_message
	,String contacts_selected) throws Exception{
		Messenger u = this.searchConnectorMessengerUser(identifier);
		List<Contacts> contactsList = getContactsByLisString(getSelectEmails(contacts_selected));
		if(u != null){
			System.err.println(email);
			handlerAuthentication = new HandlerAuthentication();
			User user = handlerAuthentication.getUserAuthenticate();
			Schedule sh = this.scheduleRepository.getScheduleById(u.getSchedule_connector().getId());
			activatorScheduleEmailSp = new ActivatorScheduleEmailSp(sender,this,sh,u,activate, user, email,password,message,subject_message,contactsList);
			stack.push(activatorScheduleEmailSp);
			stack.print();

			return this.activatorScheduleEmailSp.getResponseExecution();
		}

		return null;
	}

	public List<Contacts> getContactsByLisString(List<String> contacts){
		List<Contacts> result = new ArrayList<Contacts>();
		for(String r : contacts) {
			//System.err.println(r);
			Contacts c = this.contactsRepository.getContactsByEmail(r);
			result.add(c);
		}
		System.err.println(result);
		return result;
	}

	public ActivatorScheduleEmail ActivatorGetSchedule(String  identifier ) {
		Messenger u = this.searchConnectorMessengerUser(identifier);
		
		
		return stack.getSchedule(u);
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
