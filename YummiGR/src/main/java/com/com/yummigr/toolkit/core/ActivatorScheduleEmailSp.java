package com.com.yummigr.toolkit.core;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;
import com.com.yummigr.models.User;
import com.com.yummigr.services.MessengerService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
public class ActivatorScheduleEmailSp extends ActivatorScheduleEmail{

	private List<Contacts> list_contacts;
	private HandlerMail handlerMail;
	private JavaMailSender sender;


	private boolean activate;

	protected String email;

	protected String password;

	protected String message_customize;
	protected String subject_message;
	protected Schedule schedule;
	protected List<String> emails_selected;


	public ActivatorScheduleEmailSp(JavaMailSender sender , MessengerService msn , Schedule sh , Messenger conector, boolean activate , User u , String email, String password,String message, String subject_message , List<Contacts>cc) throws IOException {
		super(sender,msn,sh,conector,activate,u,email,password,message, subject_message);
		this.sender=sender;
		this.email=email;
		this.password=password;
		this.message_customize=message;
		this.subject_message=subject_message;
		this.activate=activate;
		this.list_contacts = cc;
		this.emails_selected = getEmails(this.list_contacts);
	}

	public List<String > getEmails(List<Contacts> cc){
		List<String> get = new ArrayList<String>();
		for(Contacts c : cc){
			get.add(c.getEmail());
		}
		return get;
	}
	
	public List<String> getMessagesContacts(List<Contacts> cc){
		List<String> get = new ArrayList<String>();
		for(Contacts c : cc){
			get.add(c.getMessage());
		}
		return get;
	}
	
	public List<String> getSubjectMessagesContacts(List<Contacts> cc){
		List<String> get = new ArrayList<String>();
		for(Contacts c : cc){
			get.add(c.getSubject_message());
		}
		return get;
	}
	
	@Override
	public void run() {

		handlerMail = new HandlerMail();
		try {
			if(this.message_customize != null && this.subject_message != null) {
			this.handlerMail.sendMessengerForSelectedContacts(sender
			,email,password,activate,this.emails_selected,null,this.message_customize,this.subject_message);
			}
			else {
				this.handlerEmail.sendMessengerForSelectedContactsPredetermined(sender, this.email, this.password, this.activate, this.emails_selected, null, this.getMessagesContacts(this.list_contacts), getSubjectMessagesContacts(this.list_contacts));
				
			}
			try {
				initiateTasks();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} 
		
		catch (MessagingException e) {
			e.printStackTrace();
		}

		try{
			initiateTasks();

		}catch(IOException e){
			e.printStackTrace();
		}


	}
}
