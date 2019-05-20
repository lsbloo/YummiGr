package com.com.yummigr.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.com.yummigr.services.MessengerService;
import com.com.yummigr.models.Messenger;
import java.util.List;

@RestController
@RequestMapping("/yummicr/api/v1/mgmnt/messenger")
public class MessengerEndPoint {
	
	
	@Autowired
	private MessengerService messengerService;
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/ga/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Messenger> getAllMessenger(){
		return this.messengerService.getAllMessengers();
	}
	
	
	

}
