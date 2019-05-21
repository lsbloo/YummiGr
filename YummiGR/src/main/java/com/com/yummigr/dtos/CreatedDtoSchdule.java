package com.com.yummigr.dtos;


/**
 * SIMPLE DTO RESPONSE;
 * @author osvaldoairon
 *
 */
public class CreatedDtoSchdule {
	
	private String name;
	
	/**
	 * 
	 * @param param
	 */
	public CreatedDtoSchdule(String param) { setName(param);}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

}
