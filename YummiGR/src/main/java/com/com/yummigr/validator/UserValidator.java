package com.com.yummigr.validator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.validator.core.Validator;
import com.com.yummigr.validator.core.Validators;
import com.com.yummigr.validator.core.Messages;
import com.com.yummigr.models.User;

@Component
public class UserValidator {
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	/**
	 * verifica se ja existe um usuario com o username especificado;
	 * @return
	 */
	public Validator<User> checkIfExistUserByUsername(){
		return (result,user) -> {
			List<User> usv = this.userRepository.findUserbyUsernameValidator(user.getUsername());
			if(usv.size() >= 1) {
				result.error("Usuário ja existe");
				System.err.println("oO");
			}else {
				result.ok();
			}
		};
	}
	
}
