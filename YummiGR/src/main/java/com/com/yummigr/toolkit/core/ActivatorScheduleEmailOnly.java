package com.com.yummigr.toolkit.core;

import java.io.IOException;

import org.springframework.mail.javamail.JavaMailSender;

import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.models.Schedule;
import com.com.yummigr.models.User;
import com.com.yummigr.services.MessengerService;

/**
 * Implementation;
 * @author osvaldoairon
 *
 */
public class ActivatorScheduleEmailOnly extends ActivatorScheduleEmail{

	private Contacts contacts;
	
	/**
	 *  -> MSG FOR ONLY CONTACT;
	 * @param sender
	 * @param msn
	 * @param sh
	 * @param conector
	 * @param activate
	 * @param u
	 * @param email
	 * @param message
	 * @param subject_message
	 * @param cc
	 * @throws IOException
	 */
	public ActivatorScheduleEmailOnly(JavaMailSender sender , MessengerService msn ,Schedule sh ,Messenger conector, boolean activate , User u , String email,String message,String subject_message , Contacts cc) throws IOException {
		super(sender,msn,sh,conector,activate,u,email,message, subject_message);
		
		this.setContacts(cc);
	}
	
	@Override
	public void run() {
		
	}

	public Contacts getContacts() {
		return contacts;
	}

	public void setContacts(Contacts contacts) {
		this.contacts = contacts;
	}
}
