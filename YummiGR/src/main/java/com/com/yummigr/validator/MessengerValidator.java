package com.com.yummigr.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.com.yummigr.repositories.MessengerRepository;
import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.validator.core.Validators;
import com.com.yummigr.validator.core.Validator;
import com.com.yummigr.models.Messenger;

@Component
public class MessengerValidator {
	
	@Autowired
	private MessengerRepository messengerRepository;
	
	
	public Validator<Messenger> checkIfMessengerHasAssociate(){
		return (result,messenger) -> {
			Messenger m = this.messengerRepository.getMessengerConnectorIfExist(messenger.getAccount_sid(), messenger.getAccount_sid());
			if(m == null) {
				result.ok();
			}else {
				result.error(" messenger connector exist");
			}
			
		};
	
}
	
}
