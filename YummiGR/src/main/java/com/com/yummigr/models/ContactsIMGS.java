package com.com.yummigr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContactsIMGS {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	
	
	@Column(name="subject_img")
	private String subject_img;
	
	@Column(name="url_img", length=2048)
	private String url_img;
	
	

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl_img() {
		return url_img;
	}

	public void setUrl_img(String url_img) {
		this.url_img = url_img;
	}

	public String getSubject_img() {
		return subject_img;
	}

	public void setSubject_img(String subject_img) {
		this.subject_img = subject_img;
	}

}
