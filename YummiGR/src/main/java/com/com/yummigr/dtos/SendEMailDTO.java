package com.com.yummigr.dtos;

/**
 * DTO FOR SENDING EMAIL OF HANDLER EMAIL.
 * @author osvaldoairon
 *
 */
public class SendEMailDTO {
	
	
	public SendEMailDTO (String name,String description , String time , boolean status) {
		setName(name);
		setDescription(description);
		setTime(time);
		setStatus(status);
	}
	private String name;
	
	private String description;
	
	private String time;
	
	private boolean status;

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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
