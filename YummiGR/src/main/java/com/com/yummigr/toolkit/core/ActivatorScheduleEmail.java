package com.com.yummigr.toolkit.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;
import com.com.yummigr.models.User;
import com.com.yummigr.services.MessengerService;
import com.com.yummigr.services.ScheduleService;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
/**
 * implementation;;
 * @author osvaldoairon
 *
 */
@EnableScheduling
public class ActivatorScheduleEmail  implements Runnable{
	
	/**
	 * Constants
	 */
	protected static final String PREFIX="Schedule Execution Send Emails";
	protected static final Integer POOL_SIZE = 2;
	
	/**
	 * MySendConnectorMessenger;
	 */
	private Messenger messengerConnector;
	
	
	private boolean activate;
	private User user;
	@Autowired
	private MessengerService messengerService;
	
	@Autowired
	private ScheduleService schedulerService;
	
	private ThreadPoolTaskScheduler pooltaskScheduler;
	
	protected HandlerMail handlerEmail;
	
	protected String email;
	
	protected String password;
	
	protected String message_customize;
	protected String subject_message;
	protected Schedule schedule;
	private String response;
	/**
	 * @throws IOException 
	 * 
	 */
	public ActivatorScheduleEmail(Schedule sh ,Messenger conector, boolean activate , User u , String email, String password,String message,String subject_message) throws IOException {
		this.pooltaskScheduler = new ThreadPoolTaskScheduler();
		this.schedule=sh;
		this.messengerConnector= conector;
		this.activate=activate;
		this.user=u;
		this.email=email;
		this.password=password;
		this.message_customize=message;
		this.subject_message = subject_message;
		this.handlerEmail= new HandlerMail();
		this.response = this.initiateTasks();
		
	}
	
	public String getResponseExecution() {
		if(this.response!=null) return this.response;
		return null;
	}
	public String initiateTasks() throws IOException{
		this.pooltaskScheduler.initialize();
		this.pooltaskScheduler.setPoolSize(POOL_SIZE);
		this.pooltaskScheduler.setThreadNamePrefix(PREFIX);
		this.pooltaskScheduler.schedule(this, new Date(System.currentTimeMillis() 
				+ this.schedule.getTime()));
		
		String thread = "Thread Init time: " + this.schedule.getTime();
		return thread;
	}
	
	@Override
	public void run() {
		List<Contacts> receiver = this.messengerService.getAllContactsForMessenger(this.messengerConnector);
		List<String> receiver_email = new ArrayList<String>();
		for(Contacts c :receiver) {
			receiver_email.add(c.getEmail());
		}
		try {
			this.handlerEmail.sendMessenger(this.email, this.password, true, 
					receiver_email, this.email, this.message_customize, this.subject_message);
			
			
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
	

}
