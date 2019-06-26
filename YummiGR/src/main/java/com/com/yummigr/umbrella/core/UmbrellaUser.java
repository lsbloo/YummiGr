package com.com.yummigr.umbrella.core;

public class UmbrellaUser {
	
	
	private String username;
	
	private String email;
	
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public UmbrellaUser() {
		
	}
	public UmbrellaUser(String username,String pass , String email) {
		setUsername(username);
		setPassword(pass);
		setEmail(email);
		
	}
}
