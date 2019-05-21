package com.com.yummigr.toolkit.models;

import java.io.Serializable;

public class ClientSMS implements Serializable{
	
	public ClientSMS() {
		
	}
	private String key;
	
	private String phone_number;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	
	

}
