package com.com.yummigr.models;

import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name="contacts_yummi")
public class Contacts implements Serializable{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;

	@Column(name="path_img" , length = 2048)
	private String path_img;

	public String getPath_img() {
		return path_img;
	}

	public void setPath_img(String path_img) {
		this.path_img = path_img;
	}
	
	@Column(name="name")
	private String name;
	
	
	@Column(name="subject_message")
	private String subject_message;

	@ManyToMany
	private List<LoggerSender> loggerSenders;

	@CreationTimestamp
	private Date created_at;


	private String update_at;

	public Date getCreated_at() {
		return created_at;
	}

	public String getUpdate_at() {
		return update_at;
	}

	public List<LoggerSender> getLoggerSenders() {
		return loggerSenders;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public void setLoggerSenders(List<LoggerSender> loggerSenders) {
		this.loggerSenders = loggerSenders;
	}

	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}

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
	
	public Contacts(String email, String message , String phoneNumber , String subject_message,String name) {
		setEmail(email);
		setMessage(message);
		setPhone_number(phoneNumber);
		setSubject_message(subject_message);
		setName(name);
		
	}
	public Contacts() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSubject_message() {
		return subject_message;
	}

	public void setSubject_message(String subject_message) {
		this.subject_message = subject_message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
