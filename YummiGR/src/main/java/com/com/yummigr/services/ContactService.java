package com.com.yummigr.services;

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
	
	
	@Autowired
	private ContactService( ContactValidator contactValidator , ContactsRepository  contactRepository, MessengerService messengerService) {
		this.contactRepository=contactRepository;
		this.contactValidator=contactValidator;
		this.messengerService=messengerService;
	}
	
	
	public boolean createNewContact(String email, String phone_number, String identifier,String message) {
		Contacts c = new Contacts(email,message,phone_number);
		
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
			}else {
				Contacts cy = this.contactRepository.checkExistContact(c.getEmail(), c.getPhone_number());
				this.contactRepository.updateContacts(c.getEmail(), c.getPhone_number(), c.getMessage() , cy.getId());
				return true;
			}
		}	
		return false;
	}

	
}
