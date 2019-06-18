package com.com.yummigr.services;

import com.com.yummigr.util.MyCalendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.repositories.ContactsRepository;
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

	private MyCalendar myCalendar;
	
	@Autowired
	private ContactService( ContactValidator contactValidator , ContactsRepository  contactRepository, MessengerService messengerService) {
		this.contactRepository=contactRepository;
		this.contactValidator=contactValidator;
		this.messengerService=messengerService;
		this.myCalendar = new MyCalendar();
	}
	
	
	public boolean createNewContact(String email, String subject,String phone_number, String identifier,String message) {
		Contacts c = new Contacts(email,message,phone_number,subject);
		
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

	public boolean updateContact(String id , String identifierUser , String message,String subject_message , String email ,String phone_number){
		Messenger u = this.messengerService.searchConnectorMessengerUser(identifierUser);
		Contacts cy = this.contactRepository.getContactById(Long.valueOf(id));
		Long messenger_id = this.contactRepository.getMessengerId(cy.getId());
		if(messenger_id == u.getId()) {
			String date_update = this.myCalendar.getDateToday();
			this.contactRepository.updateContacts(email, phone_number, message,subject_message,date_update , cy.getId());
			return true;
		}
		return false;
	}

	
}
