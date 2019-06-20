package com.com.yummigr.services;

import com.com.yummigr.util.MyCalendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.LoggerSender;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.repositories.ContactsRepository;
import com.com.yummigr.repositories.LoggerSenderRepository;
import com.com.yummigr.validator.ContactValidator;
import com.com.yummigr.validator.core.Result;
import com.com.yummigr.validator.core.ValidatorBuilder;

/**
 * 
 * @author osvaldoairon
 *
 */
@Service
public class ContactService {
	
	
	private final ContactValidator contactValidator;
	
	private final ContactsRepository contactRepository;
	
	private final MessengerService messengerService;
	
	private final LoggerSenderRepository logger;

	private MyCalendar myCalendar;
	
	@Autowired
	private ContactService( LoggerSenderRepository logger ,ContactValidator contactValidator , ContactsRepository  contactRepository, MessengerService messengerService) {
		this.contactRepository=contactRepository;
		this.contactValidator=contactValidator;
		this.messengerService=messengerService;
		this.myCalendar = new MyCalendar();
		this.logger=logger;
	}
	
	
	public boolean createNewContact(String email, String subject,String phone_number, String identifier,String message,String name) {
		Contacts c = new Contacts(email,message,phone_number,subject,name);
		
		Result result = new ValidatorBuilder<Contacts>().apply(this.contactValidator.validatePhoneNumber())
				.apply(contactValidator.validateEmail())
				.validate(c);
		
		Result result_c = new ValidatorBuilder<Contacts>().apply(this.contactValidator.validateExist()).validate(c);
		
		if(result.ok()) {
			if(result_c.ok()) {
				Messenger m = this.messengerService.searchConnectorMessengerUser(identifier);
				Contacts ci = this.contactRepository.save(c);
				this.contactRepository.insertRelationContactsMessenger(m.getId(), ci.getId());
				return true;
			}
		}	
		return false;
	}

	public boolean updateContact(String id , String identifierUser , String message,String subject_message , String email ,String phone_number,String name){
		Messenger u = this.messengerService.searchConnectorMessengerUser(identifierUser);
		Contacts cy = this.contactRepository.getContactById(Long.valueOf(id));
		Long messenger_id = this.contactRepository.getMessengerId(cy.getId());
		if(messenger_id == u.getId()) {
			String date_update = this.myCalendar.getDateToday();
			this.contactRepository.updateContacts(email, phone_number, message,subject_message,date_update,name , cy.getId());
			return true;
		}
		return false;
	}

	
	public LoggerSender getLoggerById(Long id) {
	
		return this.logger.getLoggerById(id);
	
	}
	public HashMap<LoggerSender,Integer> getLoggerEmailByDataYear(List<LoggerSender> cc , String year) {
		HashMap<LoggerSender, Integer> m = new HashMap<LoggerSender, Integer>();
		List<LoggerSender> ss = new ArrayList<LoggerSender>();
		List<Integer> occorencia = new ArrayList<Integer>();
		Integer cont=0;
		for(LoggerSender s : cc ) {
			if(s.getYear().equals(year)) {
				cont++;
			}
		}
		m.put(cc.get(0), cont);
		return m;
	}
	
	public HashMap<LoggerSender,Integer> getLoggerEmailByDataFull(List<LoggerSender> cc , String month,String day , String year) {
		HashMap<LoggerSender, Integer> m = new HashMap<LoggerSender, Integer>();
		List<LoggerSender> ss = new ArrayList<LoggerSender>();
		List<Integer> occorencia = new ArrayList<Integer>();
		Integer cont=0;
		for(LoggerSender s : cc ) {
			if(s.getMonth().equals(month) && s.getYear().equals(year) && s.getDay().equals(day)) {
				cont++;
			}
		}
		m.put(cc.get(0), cont);
		return m;
	}
	
	public HashMap<LoggerSender,Integer> getLoggerEmailByDataMonth(List<LoggerSender> cc , String month) {
		HashMap<LoggerSender, Integer> m = new HashMap<LoggerSender, Integer>();
		List<LoggerSender> ss = new ArrayList<LoggerSender>();
		List<Integer> occorencia = new ArrayList<Integer>();
		Integer cont=0;
		for(LoggerSender s : cc ) {
			if(s.getMonth().equals(month)) {
				cont++;
			}
		}
		m.put(cc.get(0), cont);
		return m;
	}
	
	public List<Long> getIdsLoggerSenderRelatedContact(Long contact_id){
		return this.logger.getIdsLoggersRelatedContact(contact_id);
	}
	
	public Contacts getContactById(Long id_contact) {
		return this.contactRepository.getContactById(id_contact);
	}
	
	public Contacts getContactRelatedDirectLogger(LoggerSender sender) {
		List<Long> conts_id = this.logger.getDistinctContactsIdByLoggerId(sender.getId());
		List<Contacts> cc = new ArrayList<Contacts>();
		for(Long cont : conts_id) {
			cc.add(this.contactRepository.getContactById(cont));
		}
		return cc.get(0);
		
	}
}
