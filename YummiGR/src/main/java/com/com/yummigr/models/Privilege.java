package com.com.yummigr.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="privilege")
public class Privilege {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;

	/**
	 * Default Constructor;
	 * @param param
	 */
	public Privilege(String param) {
		setName(param);
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToMany(mappedBy="privileges")
	private Collection<Role> roles;
	
	
	public Collection<Role> getRoles(){
		return this.roles;
	}
	public void setRole(Collection<Role> roles) {
		this.roles=roles;
	}
}
