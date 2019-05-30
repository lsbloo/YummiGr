package com.com.yummigr.toolkit.core;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import java.util.List;
import javax.mail.Address;
import java.io.Serializable;

/**
 * responsible for creating all instances of sessions for sending messages.
 * @author osvaldoairon
 *
 */
public class HandlerMail{
	
	
	private JavaMailSender javaMailSender;
	
	protected static final String[] HOST_SMTP= {"mail.smtp.host","smtp.gmail.com"};
	protected static final String[] FACTORY_PORT = {"mail.smtp.socketFactory.port" , "587"};
	protected static final String[] SSL_FACTORY = {"mail.smtp.socketFactory.class" , "javax.net.ssl.SSLSocketFactory"};
	protected static final String[] SMTP_AUTH = {"mail.smtp.auth", "true"};
	protected static final String[] MAIL_PORT = {"mail.smtp.port" , "587"};

	
	private Properties properties;
	
	private Session sessionUser;
	
	private boolean start;
	
	private Address[] destination;
	private String[] receivers;
	
	
	/**
	 * default constructor
	 * @param
	 * @param
	 */
	public HandlerMail() {
		this.properties=new Properties();
		
	}
	
	
	/**
	 * properties for session creation.
	 * @return
	 */
	public Properties getDefaultValuesSmtp() {
		this.properties.put(HOST_SMTP[0], HOST_SMTP[1]);
		this.properties.put(FACTORY_PORT[0], FACTORY_PORT[1]);
		this.properties.put(SSL_FACTORY[0], SSL_FACTORY[1]);
		this.properties.put(SMTP_AUTH[0], SMTP_AUTH[1]);
		this.properties.put(MAIL_PORT[0], MAIL_PORT[1]);
		return this.properties;
	}
	
	
	/**
	 * receives as parameter the user's email session and password, 
	 * creates a session and returns the authenticated object.
	 * @param email_session
	 * @param password_email_session
	 * @param activedDebug
	 * @return
	 */
	public Session createAuthenticator(String email_session , String password_email_session, boolean activedDebug) {
		return null;
	}

	/**
	 * this method is responsible for the transport of messages admits, some values ​​set by the user. such as custom messages, recipient mailing list.
	 * @param email_session
	 * @param password_email_session
	 * @param activedDebug
	 * @param receivers
	 * @param obj_sender
	 * @param message_costumize
	 * @param subject_message
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public boolean sendMessenger(String email_session,String password_email_session, boolean activedDebug,List<String> receivers, String obj_sender, String message_costumize, String subject_message) throws AddressException, MessagingException {
		
		this.sessionUser = Session.getDefaultInstance(getDefaultValuesSmtp()
				, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthenticator() {
					return new PasswordAuthentication(email_session,password_email_session);
				}
		});
		this.sessionUser.setDebug(activedDebug);
		Message message = new MimeMessage(this.sessionUser);
		message.setFrom(new InternetAddress(obj_sender));
		message.setRecipients(Message.RecipientType.TO, getDestination(getReceivers(receivers)));
		message.setSubject(subject_message);
		message.setText(message_costumize);
		
		Transport.send(message);
		return true;
	}

	
	public boolean sendMessengerForOnlyContact(JavaMailSender sender ,String email_session, boolean activedDebug,String receiver, String obj_sender, String message_costumize, String subject_message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(subject_message);
		simpleMailMessage.setTo(receiver);
		simpleMailMessage.setFrom(email_session);
		simpleMailMessage.setText(message_costumize);
		sender.send(simpleMailMessage);
		return true;
	}

	public boolean sendMessengerForSelectedContacts(JavaMailSender sender ,String email_session, boolean activedDebug,List<String> receivers_selected, String obj_sender, String message_costumize, String subject_message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(subject_message);

		for(String receiver : receivers_selected){
			simpleMailMessage.setTo(receiver);
			simpleMailMessage.setFrom(email_session);
			simpleMailMessage.setText(message_costumize);
			sender.send(simpleMailMessage);
		}
		return true;
	
	}
	
	public boolean sendMessengerAll(JavaMailSender sender ,String email_session, boolean activedDebug,List<String> receivers, String obj_sender, String message_costumize, String subject_message) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setSubject(subject_message);
		for(String receiver : receivers) {
			simpleMailMessage.setTo(receiver);
			simpleMailMessage.setFrom(email_session);
			simpleMailMessage.setText(message_costumize);
			sender.send(simpleMailMessage);
		}
		return true;
	}

	public boolean isStart() {
		return start;
	}


	public void setStart(boolean start) {
		this.start = start;
	}
	
	public String[] getReceivers(List<String> receiver_customize) {
		receivers = new String[receiver_customize.size()];
		for(int i = 0 ; i<receiver_customize.size() ; i ++) {
			receivers[i] = receiver_customize.get(i);
		}
		
		return receivers;
	}
	
	/**
	 * return array of destination receivers;
	 * @param receivers
	 * @return
	 * @throws AddressException
	 */
	public Address[] getDestination(String[] receivers) throws AddressException {
		
		for(int i = 0 ; i < receivers.length ; i ++) {
			destination = InternetAddress.parse(receivers[i]);
		}
		return destination;
	}
}
