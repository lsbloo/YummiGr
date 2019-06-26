package com.com.yummigr.umbrella;

import java.io.IOException;

import com.com.yummigr.models.User;
import com.com.yummigr.umbrella.core.Profile;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 
responsible for all iterations with an umbrella api,
methods of creation and use of umbrella api toolkit are implemented here.
 * @author osvaldoairon
 *
 */
public class UmbrellaEntryImpl {

	protected static final String API_URL_BASE = "localhost:8000/";
	public static final String CONTENT_TYPE = "application/json";
	
	
	private UmbrellaEntry service;
	
	public UmbrellaEntryImpl() {
		Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(UmbrellaEntry.class);
	}
	
	public User create(User u, String content_type) throws IOException {
		Call<User> call = service.createUser(content_type, u.getUsername(), u.getPassword(), u.getEmail());
		Response<User>  res =call.execute();
		return res.body();
		
		
	}
	
	public Profile create(User u ,Profile e , String content_type) throws IOException {
		
		Call<Profile> call = service.createProfile(content_type, u, e.getUsername_inst(), e.getPassword_inst(), u.getIdentifier());
		
		Response<Profile> res = call.execute();
		
		return res.body();
		
	}
	
	

}
