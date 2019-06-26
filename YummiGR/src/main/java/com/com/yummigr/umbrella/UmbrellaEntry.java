package com.com.yummigr.umbrella;


import com.com.yummigr.models.User;


import com.com.yummigr.umbrella.core.Profile;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * define all iteractions with umbrellaServer entrypoints;
 * @author osvaldoairon
 *
 */


public interface UmbrellaEntry {
	
	
	
	@POST("umbrella/api/v1/mgmnt/users/c/{username}/{password}/{email}")
	Call<User> createUser(@Header("Content-Type") String content_type,
			@Path("username") String username , @Path("password") String password,
			@Path("email") String email);

	
	@POST("umbrella/api/v1/mgmnt/profiles/c/{username}/{password}/{identifier}")
	Call<Profile> createProfile(@Header("Content-Type") String content_type , 
			@Header("Authorization") User u,
			@Path("username") String username_inst,
			@Path("password") String password_inst,
			@Path("identifier") String manager_identifier);
	
	
}
