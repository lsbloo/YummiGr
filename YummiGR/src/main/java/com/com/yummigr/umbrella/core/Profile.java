package com.com.yummigr.umbrella.core;

public class Profile {
	
	
	private String username_inst;
	
	private String password_inst;
	
	
	private String manager_identifier;

	
	
	public Profile() {}
	public Profile(String username,String password, String identifier) {
		setUsername_inst(username);
		setPassword_inst(password);
		setManager_identifier(identifier);
	}

	public String getManager_identifier() {
		return manager_identifier;
	}


	public void setManager_identifier(String manager_identifier) {
		this.manager_identifier = manager_identifier;
	}


	public String getPassword_inst() {
		return password_inst;
	}


	public void setPassword_inst(String password_inst) {
		this.password_inst = password_inst;
	}


	public String getUsername_inst() {
		return username_inst;
	}


	public void setUsername_inst(String username_inst) {
		this.username_inst = username_inst;
	}
	
	
	

}
