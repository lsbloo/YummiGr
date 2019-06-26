package com.com.yummigr.dtos;

import java.util.List;

public class UmbrellaDTO {
	
	private String description;
	
	
	private boolean profile_generate;
	
	private boolean new_followers;
	
	
	private List<String> followers;


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public boolean isProfile_generate() {
		return profile_generate;
	}


	public void setProfile_generate(boolean profile_generate) {
		this.profile_generate = profile_generate;
	}


	public boolean isNew_followers() {
		return new_followers;
	}


	public void setNew_followers(boolean new_followers) {
		this.new_followers = new_followers;
	}


	public List<String> getFollowers() {
		return followers;
	}


	public void setFollowers(List<String> followers) {
		this.followers = followers;
	}
	
	public UmbrellaDTO(String description , boolean profile) {
		setDescription(description);
		setProfile_generate(profile);
	}

}
