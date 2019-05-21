package com.com.yummigr.toolkit.core;

import org.springframework.stereotype.Component;
import com.com.yummigr.toolkit.models.ClientSMS;

import br.com.facilitamovel.bean.Retorno;
import br.com.facilitamovel.bean.SmsMultiplo;
import br.com.facilitamovel.bean.SmsMultiploMessages;
import br.com.facilitamovel.service.SendMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author osvaldoairon
 *
 */
@Component
public class HandlerSMSV1 {
	
	private String username;
	
	private String password;
	
	private List<ClientSMS> contacts;
	
	private SmsMultiplo sms_m;
	
	private SmsMultiploMessages sms_multiple_messages;
	
	
	private HashMap<Integer,String> result;
	
	public HandlerSMSV1(String username,String password) {
		setUsername(username);
		setPassword(password);
		this.contacts = new ArrayList<ClientSMS>();
		this.sms_m = new SmsMultiplo();
		this.sms_multiple_messages = new SmsMultiploMessages();
		this.result = new HashMap<Integer,String>();
	}
	
	/**
	 * 
	 * sends an SMS message to multiple recipient users.
	 * @param contacts
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public HashMap<Integer,String> createMessageForMultipleReceivers(List<ClientSMS> contacts , String message) throws Exception {
		return sendSMS(getReceivers(contacts), getKeyReceivers(contacts), message);
	
	
	}
	
	
	/**
	 * sends multiple SMS messages to multiple recipient users.
	 * @param contacts
	 * @param messages
	 * @return
	 * @throws Exception 
	 */
	public HashMap<Integer,String> createMultipleMessageForMultipleReceivers(List<ClientSMS>
	contacts, List<String> messages) throws Exception{
		return sendSMSMultiple(getReceivers(contacts), getKeyReceivers(contacts), messages);
		
	}
	
	public HashMap<Integer,String> sendSMSMultiple(List<String> receivers , List<String> key_receivers , List<String> messages) throws Exception {
		this.sms_multiple_messages.setUser(getUsername()); this.setPassword(getPassword());
		this.sms_multiple_messages.setDestinatarios(receivers);
		this.sms_multiple_messages.setChaveClientes(key_receivers);
		this.sms_multiple_messages.setMessages(messages);
		Retorno retorno = SendMessage.multipleSend(this.sms_m);
		if(retorno != null) {
			this.result.put(retorno.getCodigo(), retorno.getMensagem());
		}
		return this.result;
	}
	
	
	
	public HashMap<Integer,String> sendSMS(List<String> receivers , List<String> key_receivers , String message) throws Exception {
		this.sms_m.setUser(getUsername()); this.setPassword(getPassword());
		this.sms_m.setDestinatarios(receivers);
		this.sms_m.setChaveClientes(key_receivers);
		this.sms_m.setMessage(message);
		Retorno retorno = SendMessage.multipleSend(this.sms_m);
		if(retorno != null) {
			this.result.put(retorno.getCodigo(), retorno.getMensagem());
		}
		return this.result;
	}
	
	public List<String> getReceivers(List<ClientSMS> contacts) {
		List<String> receiver = new ArrayList<String>();
		for(ClientSMS client : contacts) {
			receiver.add(client.getPhone_number());
		}
		return receiver;
	}
	
	public List<String> getKeyReceivers(List<ClientSMS> contacts) {
		List<String> receiver = new ArrayList<String>();
		for(ClientSMS client : contacts) {
			receiver.add(client.getKey());
		}
		
		return receiver;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
