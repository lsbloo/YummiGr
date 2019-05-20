package com.com.yummigr.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.validator.core.Validator;
import com.com.yummigr.validator.core.Validators;
import com.com.yummigr.validator.core.Messages;
import com.com.yummigr.models.User;

/**
 * Responsible for creating validations regarding system users.
 * @author osvaldoairon
 *
 */
@Component
public class UserValidator {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * checks whether a user with the specified username already exists;
	 * @return
	 */
	public Validator<User> checkIfExistUserByUsername(){
		return (result,user) -> {
			List<User> usv = this.userRepository.findUserbyUsernameValidator(user.getUsername());
			if(usv.size() >= 1) {
				result.error("User already exists");
			}else {
				result.ok();
			}
		};
	}
	
}
