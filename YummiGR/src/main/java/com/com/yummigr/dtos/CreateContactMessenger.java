package com.com.yummigr.dtos;

/**
 * Simple DTO;
 * @author osvaldoairon
 *
 */
public class CreateContactMessenger {

	private String id;

	public String getId() {

		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
	
	public CreateContactMessenger(String param,String description,String id) {
		setName(param);
		setDescription(description);
		setId(id);
	}
	public CreateContactMessenger() {}
	
}
