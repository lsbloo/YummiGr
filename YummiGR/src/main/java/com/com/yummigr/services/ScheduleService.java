package com.com.yummigr.services;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.com.yummigr.repositories.ScheduleRepository;
import com.com.yummigr.validator.ScheduleValidator;
import com.com.yummigr.validator.core.Result;
import com.com.yummigr.validator.core.ValidatorBuilder;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;

@Service
public class ScheduleService {
	
	private final ScheduleRepository scheduleRepository;
	
	private final ScheduleValidator scheduleValidator;
	
	private final MessengerService messengerService;
	
	
	@Autowired
	private ScheduleService(ScheduleRepository scheduleRepository
			, ScheduleValidator scheduleValidator, MessengerService messengerService) {
		this.scheduleRepository=scheduleRepository;
		this.scheduleValidator=scheduleValidator;
		this.messengerService=messengerService;
	}
	
	/**
	 * insert only instance of object schedule;
	 * this validator check if param time its valid;
	 * @param time
	 * @return
	 */
	public boolean createSchedule(String time, String identifierUser) {
		Schedule sh = new Schedule();
		if(checkAttrTimeInput(time)) {
			sh.setTime(Integer.parseInt(time));
			Result result = new ValidatorBuilder<Schedule>().apply(scheduleValidator.checkParamTime())
					.validate(sh);
				if(result.ok()) {
				try {
				if(updateScheduleMessengerConnector(identifierUser,time)) {
					return true;
				}else {
					Schedule x = this.scheduleRepository.save(sh);
					Messenger u = this.messengerService.searchConnectorMessengerUser(identifierUser);
					Integer exit = this.messengerService.insertRelationScheduleConnectorMessenger(u, x);
					if(exit != 0) return true;
				}
				
				}catch(NullPointerException e) {
					return  false;
				}
				return true;
				
			}
			return false;
		}else {
			return false;
		}
	}
	public boolean  updateScheduleMessengerConnector(String identifierUser,String time) {
		Messenger u = this.messengerService.searchConnectorMessengerUser(identifierUser);
		HashMap<Boolean,Integer> result = this.messengerService.verificExistRelationScheduleConnectorMessenger(u);
		if(result.containsKey(true)) {
			Integer result_id = result.get(true);
			this.scheduleRepository.updateScheduleTime(Integer.parseInt(time), result_id);
			return true;
		}else {
			return false;
		}
		
		
		
	}
	
	public boolean checkAttrTimeInput(String time) {
		if(time == null || time.length() == 0) {
			return false;
		}
		for(int i = 0 ; i < time.length() ; i++) {
			if(!Character.isDigit(time.charAt(i))) {
				return false;
			}
		}
		
		return true;
	}
	

}
