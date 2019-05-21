package com.com.yummigr.endpoints;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.com.yummigr.dtos.CreatedDtoSchdule;
import com.com.yummigr.dtos.FailureConnectorMessenger;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.services.MessengerService;
import com.com.yummigr.services.ScheduleService;
import com.com.yummigr.services.UserService;
import com.com.yummigr.validator.core.Result;

@RestController
@RequestMapping("/yummicr/api/v1/toolkit")
public class ClientEndPoint {
	
	
	private final UserService userService;
	
	private final MessengerService messengerService;
	
	private final ScheduleService scheduleService;
	
	
	@Autowired
	public ClientEndPoint(UserService userService , MessengerService service
			, ScheduleService scheduleService ) {
		this.userService=userService;
		this.messengerService=service;
		this.scheduleService=scheduleService;
	}
	/**
	 * creates a messenger connector for an associated user, 
	 * an associated user can only have a messenger co-connector
	 * @param identifierUser
	 * @param message
	 * @param phone_number
	 * @return ResponseEntity
	 */
	@PostMapping(value="/messenger/c/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FailureConnectorMessenger> createMessengerUser(@RequestParam String identifierUser , @RequestParam String account_sid,
			@RequestParam String auth_token){
		
		boolean result = this.messengerService.createConnectorMessenger(identifierUser, account_sid, auth_token);
		if(!result) {
			FailureConnectorMessenger msg_f = new FailureConnectorMessenger("There is already a connector message associated with this user.");
			
			return ResponseEntity
					.status(HttpServletResponse.SC_FORBIDDEN).body(msg_f);
		}else {
			FailureConnectorMessenger msg_a = new FailureConnectorMessenger("connector messagem successfully created");
			
			return ResponseEntity
					.status(HttpServletResponse.SC_CREATED).body(msg_a);
		}
		
	}
	
	@PostMapping(value="/messenger/schedule/c/" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreatedDtoSchdule> createSchedule(@RequestParam String time, @RequestParam String identifier,HttpServletResponse response, HttpServletRequest request){
		boolean result = this.scheduleService.createSchedule(time,identifier);
		if(result) {
			CreatedDtoSchdule creat_sh = new CreatedDtoSchdule("object team scheduler successfully created!");
			
			return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(creat_sh);
		}else {
			CreatedDtoSchdule creat_f = new CreatedDtoSchdule("Enter the time field correctly can not be null or different from zero"
					+ "    Example input: 500  "
					+ "Enter identifier of messenger_user correctly");
			
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(creat_f);
		}
	}
	/**
	 * 
	 * @param identifier
	 * @param response
	 * @return
	 */
	@GetMapping(value="/messenger/g/identifier", produces =  MediaType.APPLICATION_JSON_VALUE)
	public Messenger getConnectonMessengerUser(@RequestParam String identifier
			,HttpServletResponse response) {
		boolean result = this.messengerService.getMessengerConnectorUser(identifier);
		if(result) {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return this.messengerService.searchConnectorMessengerUser(identifier);
		}else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
	}

}
