package com.com.yummigr.models;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name="messenger_user")
public class Messenger implements Serializable{
	
	
	public Messenger() {}
	
	public Messenger(String account_sid , String auth_token) {
		setAccount_sid(account_sid);
		setAuth_token(auth_token);
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getAccount_sid() {
		return account_sid;
	}

	public void setAccount_sid(String account_sid) {
		this.account_sid = account_sid;
	}


	public String getAuth_token() {
		return auth_token;
	}

	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}


	private String account_sid;
	
	private String auth_token;
	
	@ManyToMany
	@JoinTable(name="contacts_messenger", joinColumns= {@JoinColumn(name="messenger_id", referencedColumnName="id")},
	inverseJoinColumns= {@JoinColumn(name="contacts_id", referencedColumnName="id")})
	private Collection<Contacts> contacts;

}
