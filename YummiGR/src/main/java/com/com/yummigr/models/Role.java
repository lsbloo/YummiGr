package com.com.yummigr.models;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import com.com.yummigr.models.User;
import com.com.yummigr.models.*;

@Entity
public class Role implements GrantedAuthority{
	
	
	private String  name;
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;
	
	@ManyToMany
	private Collection<User> users;
	
	
	@ManyToMany
	@JoinTable(name="roles_privilege" , joinColumns= {@JoinColumn(name="role_id", referencedColumnName="id")},
	inverseJoinColumns= {@JoinColumn(name="privilege_id" , referencedColumnName="id")})
	private Collection<Privilege> privileges;
	

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}


	public Collection<User> getUsers() {
		return users;
	}


	public void setUsers(Collection<User> users) {
		this.users = users;
	}


	public Collection<Privilege> getPrivileges() {
		return privileges;
	}


	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}

}
