package com.com.yummigr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.models.User;
import com.com.yummigr.validator.core.Result;
import com.com.yummigr.validator.UserValidator;
import com.com.yummigr.validator.core.*;

@Service
public class UserService {
	
	
	
	private final UserRepository userRepository;
	private final UserValidator userValidator;
	
	
	@Autowired
	public UserService(UserRepository userRepository , UserValidator userValidator) {
		this.userRepository=userRepository;
		this.userValidator = userValidator;
	}
	
	
	public boolean createUser(String first_name, String last_name, String email, String username,String password, boolean actived , String identifier) {
		User u = new User();
		u.setActived(actived);
		u.setFirst_name(first_name);
		u.setLast_name(last_name);
		u.setEmail(email);
		u.setPassword(password);
		u.setIdentifier(identifier);
		u.setUsername(username);
		
		String pass = u.getPassword();
		u.setPassword(passwordEncoder().encode(pass));
		
		Result result = new 
				ValidatorBuilder<User>().
				apply(userValidator.checkIfExistUserByUsername())
				.validate(u);
		//System.err.println(result.getErrors());
		if(result.ok()) {
			this.userRepository.save(u);
		}else {
			return false;
		}
		return true;
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
