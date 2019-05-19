package com.com.yummigr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.com.yummigr.models.Messenger;

import com.com.yummigr.repositories.MessengerRepository;

@Service
public class MessengerService {

	
	private MessengerRepository messengerRepository;
	
	
	@Autowired
	private MessengerService( MessengerRepository menssengerRepository) {
		
		this.messengerRepository=messengerRepository;
	}
	
	
	public List<Messenger> getAllMessengers(){
		return this.messengerRepository.getAllMessenger();
	}
	
	
}
