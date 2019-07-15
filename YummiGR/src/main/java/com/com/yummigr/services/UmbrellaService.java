package com.com.yummigr.services;

import java.io.IOException;

import com.com.yummigr.umbrella.core.ConnectorProfiles;
import com.com.yummigr.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.com.yummigr.models.User;
import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.umbrella.UmbrellaEntryImpl;
import com.com.yummigr.umbrella.core.Profile;

@Service
public class UmbrellaService {
	
	
	
	
	private final UserRepository userRepository;
	private UmbrellaEntryImpl impl;
	
	
	@Autowired
	public UmbrellaService(UserRepository userRepository ) throws IOException {
		this.userRepository=userRepository;
		this.impl=new UmbrellaEntryImpl();
	}
	
	

	
	public User checkUser(String identifier) {
		User u = this.userRepository.findByUserIdentifier(identifier);
		if(u != null) return u;
		return null;
	}
	public boolean createProfileInst(Profile e ) throws IOException {
		User u = checkUser(e.getManager_identifier());
		if(u!=null) {
			this.impl.create(u, e, Constants.CONTENT_TYPE);
			return true;
		}
		return false;
	}

	public boolean connectProfileInst(ConnectorProfiles e ) throws IOException {
		User u = checkUser((e.getManager_identifier()));
		if(u != null){
			this.impl.connect(u,e,Constants.CONTENT_TYPE);
			return true;
		}

		return false;
	}


	public boolean getNewFollowers(ConnectorProfiles e ) throws IOException {
		User u = checkUser((e.getManager_identifier()));

		if(u!=null){
			this.impl.getNewFollowers(u,e,Constants.CONTENT_TYPE);
			return true;
		}
		return false;
	}

	public boolean followFollowersOfMyFriend(ConnectorProfiles e) throws IOException {
		User u = checkUser((e.getManager_identifier()));
		if(u!=null){
			this.impl.followFollowersOfMyFriend(u,e,Constants.CONTENT_TYPE);
			return true;
		}else{
			return false;
		}
	}
}
