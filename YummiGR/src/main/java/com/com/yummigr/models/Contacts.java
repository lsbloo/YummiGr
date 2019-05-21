package com.com.yummigr.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import javax.persistence.Id;


@Entity
@Table(name="contacts_yummi")
public class Contacts implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;

	
	@Column(name="email")
	private String email;
	
	@Column(name="phone_number")
	private String phone_number;
	
	@Column(name="message")
	private String message;

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Contacts(String email, String message , String phoneNumber) {
		setEmail(email);
		setMessage(message);
		setPhone_number(phoneNumber);
	}
	public Contacts() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
