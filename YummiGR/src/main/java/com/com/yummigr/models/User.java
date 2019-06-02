package com.com.yummigr.models;

import java.io.Serializable;
import java.sql.Date;
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
import javax.persistence.JoinColumns;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="yummi_user")
@JsonAutoDetect
public class User implements UserDetails,Serializable{
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private long id;
	
	@JsonProperty("first_name")
	@Column(name="first_name", length=100)
	private String first_name;
	
	@JsonProperty("last_name")
	@Column(name="last_name" , length=100)
	private String last_name;
	
	@JsonProperty("username")
	@Column(name="username" , length=150)
	private String username;
	
	@JsonProperty("password")
	@Column(name="password" , length=200)
	private String password;
	
	@JsonProperty("email")
	@Column(name="email" , length=300)
	private String email;
	
	@JsonProperty("actived")
	@Column(name="actived")
	private boolean actived;
	
	@JsonProperty("identifier")
	@Column(name="identifier")
	private String identifier;

	@CreationTimestamp
	private Date date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	
	@ManyToMany
	@JoinTable(name="users_messenger" , joinColumns= {@JoinColumn(name="yuumi_user_id", referencedColumnName="id")},
	inverseJoinColumns= {@JoinColumn(name="messenger_user" , referencedColumnName="id")})
	private Collection<Messenger> messengers;
	
	/**
	 * default constructor entity
	 */
	public User() {}
	/**
	 * my default constructor
	 * @param username
	 * @param password
	 */
	public User(String username , String password) {
		setUsername(username);
		setPassword(password);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	@ManyToMany
	@JoinTable(name="usuarios_role",joinColumns= @JoinColumn(
			name="usuarios_id", referencedColumnName="id"),
	inverseJoinColumns = @JoinColumn(name="role_id" , referencedColumnName = "id"))
	private Collection<Role> roles;

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}



}
