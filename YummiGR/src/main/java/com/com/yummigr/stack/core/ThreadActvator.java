package com.com.yummigr.stack.core;
/**
 * Define this stack of threads activatorSchedule
 * @author osvaldoairon
 *
 * @param <T>
 */
import com.com.yummigr.toolkit.core.ActivatorScheduleEmail;
public interface ThreadActvator{

	
	public void push(ActivatorScheduleEmail act) throws Exception;
	
	
	public ActivatorScheduleEmail pop(ActivatorScheduleEmail act);
	
	public int size();
	
	
	public String notfiy(ActivatorScheduleEmail act);
	
	
	
	
}
