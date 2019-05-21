package com.com.yummigr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;
import com.com.yummigr.models.User;
import com.com.yummigr.repositories.MessengerRepository;
import com.com.yummigr.validator.MessengerValidator;
import com.com.yummigr.validator.UserValidator;
import com.com.yummigr.validator.core.Result;
import com.com.yummigr.validator.core.ValidatorBuilder;


/**
 * all interactions of services(messenger-connector) and controlls ; 
 * @author osvaldoairon
 *
 */
@Service
public class MessengerService {

	/**
	 * messengerRepository
	 */
	private final MessengerRepository messengerRepository;
	
	/**
	 * MessengerValidator();
	 */
	private final MessengerValidator messengerValidator;
	
	/**
	 */
	private final UserService userService;
	
	private final UserValidator userValidator;
	
	
	private HashMap<Boolean,Integer> hashMapVerificUpdate;
	
	
	@Autowired
	private MessengerService( MessengerRepository menssengerRepository , MessengerValidator messengerValidator
			,UserService userService, UserValidator userValidator) {
		
		this.messengerRepository= menssengerRepository;
		this.messengerValidator=messengerValidator;
		this.userService = userService;
		this.userValidator=userValidator;
		this.hashMapVerificUpdate = new HashMap<Boolean,Integer>();
	}
	
	
	public List<Messenger> getAllMessengers(){
		return this.messengerRepository.getAllMessenger();
	}
	
	
	
	public boolean createConnectorMessenger(String identifieruser, String account_sid , String auth_token) {
		Messenger m = new Messenger(account_sid,auth_token);
		
		Result result = new ValidatorBuilder<Messenger>().apply(messengerValidator.checkIfMessengerHasAssociate()).validate(m);
		
		if(result.ok()) {
			User u = this.userService.getUserByIdentifier(identifieruser);
			if(u != null) {
				Integer id = this.messengerRepository.getIdMessengerEntity(u.getId());
				try {
					if(id != 0 || id == null) {
						this.messengerRepository.save(m);
						this.messengerRepository.insertRelationMessengerUser(u.getId(), m.getId());
						result.ok();
						return true;
					}else {
						result.error("associate exist;");
						return false;
						
					}
				}catch(NullPointerException e) {
					this.messengerRepository.save(m);
					this.messengerRepository.insertRelationMessengerUser(u.getId(), m.getId());
					result.ok();
					return true;
				}
			}
		}else {
			return false;
		}
		
		return true;
		
	}
	
	public boolean  getMessengerConnectorUser(String identifier) {
		
		User u = this.userService.getUserByIdentifier(identifier);
		Result result = new ValidatorBuilder<User>().apply(userValidator.checkIfExistUserByIdentifier())
				.apply(userValidator.checkIfUserHaveAssociateConnectorMessage()).validate(u);
		if(result.ok()) {
			result.ok();
			return true;
		}
		else {
			result.error("dont have associate or not exist user");
			return false;
		}
		
	}
	
	public Messenger searchConnectorMessengerUser(String identifier) {
		User u = this.userService.getUserByIdentifier(identifier);
		Integer messenger_id = this.userService.getMessengerIdByIdUser(u.getId());
		return this.messengerRepository.getMessengerEntity(messenger_id);
		
	}
	
	
	
	
	public Integer insertRelationScheduleConnectorMessenger(Messenger u , Schedule sh) {
		return this.messengerRepository.updateMessengerConnectorSchedule(sh.getId(), u.getId());
	}
	
	public HashMap<Boolean,Integer> verificExistRelationScheduleConnectorMessenger(Messenger u) {
		try{
			Integer result  = this.messengerRepository.getConnectorIdByUser(u.getId());
			
			if(result!=0) {
				this.hashMapVerificUpdate.put(true, result);
				return this.hashMapVerificUpdate;
			}else {
				this.hashMapVerificUpdate.put(false, 0);
				return this.hashMapVerificUpdate;
			}
		}catch(NullPointerException e) {
			this.hashMapVerificUpdate.put(false, 0);
			return this.hashMapVerificUpdate;
		}
	}
	
	
	
	
	
	
	
	}
