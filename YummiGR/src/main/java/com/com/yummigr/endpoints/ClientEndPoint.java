package com.com.yummigr.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.com.yummigr.services.MessengerService;
import com.com.yummigr.services.UserService;

@RestController
@RequestMapping("/yummicr/api/v1/toolkit")
public class ClientEndPoint {
	
	
	private final UserService userService;
	
	private final MessengerService messengerService;
	
	
	
	@Autowired
	public ClientEndPoint(UserService userService , MessengerService service) {
		this.userService=userService;
		this.messengerService=service;
	}
	
	
	/**
	 * creates a messenger connector for an associated user, 
	 * an associated user can only have a messenger co-connector
	 * @param identifierUser
	 * @param message
	 * @param phone_number
	 * @return ResponseEntity
	 */
	@PostMapping(value="/c/messenger/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public boolean createMessengerUser(@RequestParam String identifierUser , @RequestParam String account_sid,
			@RequestParam String auth_token){
		
		return false;
	}

}
