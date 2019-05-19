package com.com.yummigr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.models.User;


@Service
public class UserService {
	
	
	
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	
	public void createUser(User user) {
		String pass = user.getPassword();
		user.setPassword(passwordEncoder().encode(pass));
		this.userRepository.save(user);
		
	}

	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
