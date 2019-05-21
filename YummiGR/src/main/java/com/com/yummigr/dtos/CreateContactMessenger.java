package com.com.yummigr.dtos;

/**
 * Simple DTO;
 * @author osvaldoairon
 *
 */
public class CreateContactMessenger {

	
	private String name;
	
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public CreateContactMessenger(String param,String description) {
		setName(param);
		setDescription(description);
	}
	public CreateContactMessenger() {}
	
}
