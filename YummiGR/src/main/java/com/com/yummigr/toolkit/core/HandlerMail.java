package com.com.yummigr.toolkit.core;

import java.util.Properties;

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
@Component
public class HandlerMail implements Serializable{
	
	protected static final String[] HOST_SMTP= {"mail.smtp.host","smtp.gmail.com"};
	protected static final String[] FACTORY_PORT = {"mail.smtp.socketFactory.port" , "465"};
	protected static final String[] SSL_FACTORY = {"mail.smtp.socketFactory.class" , "javax.net.ssl.SSLSocketFactory"};
	protected static final String[] SMTP_AUTH = {"mail.smpt.auth", "true"};
	protected static final String[] MAIL_PORT = {"mail.smpt.port" , "465"};

	
	private Properties properties;
	
	private Session sessionUser;
	
	private boolean start;
	
	private Address[] destination;
	
	
	/**
	 * default constructor
	 * @param properties
	 * @param sessionUser
	 */
	public HandlerMail(boolean start) {
		this.properties=new Properties();
		this.setStart(start);
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
		this.sessionUser = Session.getDefaultInstance(getDefaultValuesSmtp()
				, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthenticator() {
					return new PasswordAuthentication(email_session,password_email_session);
				}
		});
		if(activedDebug) this.sessionUser.setDebug(true);
		
		return this.sessionUser;
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
		Message message = new MimeMessage(createAuthenticator(email_session,password_email_session,activedDebug));
		message.setFrom(new InternetAddress(obj_sender));
		String[] receiver=null;
		message.setRecipients(Message.RecipientType.TO, getDestination(getReceivers(receivers, receiver)));
		message.setSubject(subject_message);
		message.setText(message_costumize);
		Transport.send(message);
		return true;
	}

	public boolean isStart() {
		return start;
	}


	public void setStart(boolean start) {
		this.start = start;
	}
	
	public String[] getReceivers(List<String> receiver_customize, String[] receiver) {
		for(int i = 0 ; i<receiver_customize.size() ; i ++) {
			receiver[i] = receiver_customize.get(i);
		}
		
		return receiver;
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
