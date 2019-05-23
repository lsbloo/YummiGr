package com.com.yummigr.toolkit.core;

import java.io.IOException;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;
import com.com.yummigr.models.User;
import com.com.yummigr.services.MessengerService;
import com.com.yummigr.services.ScheduleService;

import sun.security.util.SecurityConstants;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * this class is reponsavel by the creation of tasks for execution of automatic emails. 
 * it inherits attributes of the thread class, each instance of that object is connected to a 
 * connector messenger which in turn defines the runtime in the runnable method call run () method.
 * @author osvaldoairon
 *
 */
@EnableScheduling
public class ActivatorScheduleEmail  extends Thread implements Runnable,Future {
	
	/**
	 * Constants
	 */
	protected static final String PREFIX="Schedule Execution Send Emails";
	protected static final Integer POOL_SIZE = 1;
	
	/**
	 * MySendConnectorMessenger;
	 */
	private Messenger messengerConnector;
	
	
	private JavaMailSender sender;
	
	private MessengerService messengerService;
	private boolean activate;
	private User user;
	
	private ThreadPoolTaskScheduler pooltaskScheduler;
	
	protected HandlerMail handlerEmail;
	
	protected String email;
	
	protected String password;
	
	protected String message_customize;
	protected String subject_message;
	protected Schedule schedule;
	private String response;
	
	private int threadStatus=0;
	
	public ActivatorScheduleEmail() {}
	/**
	 * @throws IOException 
	 * 
	 */
	public ActivatorScheduleEmail(JavaMailSender sender , MessengerService msn ,Schedule sh ,Messenger conector, boolean activate , User u , String email,String message,String subject_message) throws IOException {
		this.pooltaskScheduler = new ThreadPoolTaskScheduler();
		this.schedule=sh;
		this.messengerService=msn;
		this.messengerConnector= conector;
		this.activate=activate;
		this.user=u;
		this.email=email;
		this.sender=sender;
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
	
	public void destroy() {
		this.pooltaskScheduler = null;
		this.schedule=null;
		this.messengerService=null;
		this.messengerConnector= null;
		this.activate=false;
		this.user=null;;
		this.email=null;;
		this.sender=null;;
		this.message_customize=null;;
		this.subject_message = null;;
		this.handlerEmail= null;;
		this.response = null;;
	}
	@Override
	public void run() {
		
		
		List<Contacts> receiver = this.messengerService.getAllContactsForMessenger(this.messengerConnector);
		
		
		List<String> receiver_email = new ArrayList<String>();
		for(Contacts c :receiver) {
			receiver_email.add(c.getEmail());
		}
		this.handlerEmail.sendMessengerAll(this.sender,this.email, true, 
		receiver_email, this.email, this.message_customize, this.subject_message);
	
		try {
			initiateTasks();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Messenger getMessenger() {
		return this.messengerConnector;
	}
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}


	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		// TODO Auto-generated method stub
		return this.cancel(mayInterruptIfRunning);
	}



	@Override
	public boolean isCancelled() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isDone() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Object get() throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		return this;
	}



	@Override
	public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		// TODO Auto-generated method stub
		return null;
	}

}
