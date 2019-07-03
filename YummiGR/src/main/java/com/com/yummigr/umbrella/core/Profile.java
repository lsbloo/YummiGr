package com.com.yummigr.umbrella.core;

public class Profile {
	
	
	private String username;
	
	private String password;
	
	
	private String identifier;

	
	
	public Profile() {}
	public Profile(String username,String password, String identifier) {
		setUsername_inst(username);
		setPassword_inst(password);
		setManager_identifier(identifier);
	}

	public String getManager_identifier() {
		return identifier;
	}


	public void setManager_identifier(String manager_identifier) {
		this.identifier = manager_identifier;
	}


	public String getPassword_inst() {
		return password;
	}


	public void setPassword_inst(String password_inst) {
		this.password = password_inst;
	}


	public String getUsername_inst() {
		return username;
	}


	public void setUsername_inst(String username_inst) {
		this.username = username_inst;
	}
	
	
	

}
