package com.com.yummigr.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.com.yummigr.repositories.ScheduleRepository;
import com.com.yummigr.validator.core.Validator;
import com.com.yummigr.models.Schedule;
@Component
public class ScheduleValidator {

	@Autowired
	private ScheduleRepository scheduleRepository;
	
	
	
	/**
	 * check attr time;
	 * @return
	 */
	public Validator<Schedule> checkParamTime(){
		return (result, schedule) -> {
			if(schedule.getTime().equals("")) result.error("attr time invalid");
			if(schedule.getTime() == null || schedule.getTime() == 0) result.error("attr time invalid!");
			result.ok();
			
		};
	}
	
	
}
