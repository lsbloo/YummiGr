package com.com.yummigr.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.com.yummigr.repositories.ContactsRepository;
import com.com.yummigr.validator.core.Validator;
import com.com.yummigr.models.Contacts;
import com.com.yummigr.util.MaskEditText;

@Component
public class ContactValidator {
	
	@Autowired
	private ContactsRepository contactRepository;
	
	protected MaskEditText mask;
	
	
	public Validator<Contacts> validateExist(){
		return (result,contacts) -> {
			try {
				Contacts c = this.contactRepository.checkExistContact(contacts.getEmail(),contacts.getPhone_number());
				if(c!=null) {
					result.error("Contact Found!");
				}else {
					result.ok();
				}
				
			}catch(NullPointerException e) {
				result.ok();
			}
		};
	}
	
	public Validator<Contacts> validatePhoneNumber(){
		return (result,contacts) -> {
			mask = new MaskEditText();
			boolean r = mask.valid_pattern(contacts.getPhone_number());
			if(r) {result.ok();
			
			}
			else {
				
			result.error("Number invalid format!");
			
			}
		};
	}
	
	public Validator<Contacts> validateEmail(){
		return (result,contacts) -> {
			mask = new MaskEditText();
			boolean r = mask.valid_pattern_email(contacts.getEmail());
			if(r) {
				result.ok();
				
				}
			else {
				
			result.error("Number invalid format!");
			}
		};
	}
	
	

}
