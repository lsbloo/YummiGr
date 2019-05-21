package com.com.yummigr.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
@Entity
@Table(name="schedule_messenger_connector")
public class Schedule {
	
	@Id
	@GeneratedValue(strategy  = GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;

	 /**
	  * Default Constructor - ENTITY;
	  */
	public Schedule() {}
	
	/**
	 * 
	 */
	private Integer time;
	
	/**
	 * 
	 */
	@CreationTimestamp
	private Date created_at;
	
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}
	
	

}
