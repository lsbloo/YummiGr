package com.com.yummigr.umbrella;


import com.com.yummigr.models.User;


import com.com.yummigr.umbrella.core.Profile;
import com.com.yummigr.umbrella.core.UmbrellaUser;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * define all iteractions with umbrellaServer entrypoints;
 * @author osvaldoairon
 *
 */


public interface UmbrellaEntry {
	
	
	
	@POST("umbrella/api/v1/mgmnt/users/c/")
	Call<UmbrellaUser> createUser(@Header("Content-Type") String content_type,
			@Body UmbrellaUser user);

	
	@POST("umbrella/api/v1/mgmnt/profiles/c/")
	Call<Profile> createProfile(@Header("Content-Type") String content_type , 
			@Body Profile p,
			@Header("Authorization") String basic);
	
	
}
