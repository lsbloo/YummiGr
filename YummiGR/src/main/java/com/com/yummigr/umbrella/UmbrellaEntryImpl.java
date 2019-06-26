package com.com.yummigr.umbrella;

import com.com.yummigr.models.User;
import com.com.yummigr.umbrella.core.Profile;

import retrofit2.Call;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;


public class UmbrellaEntryImpl implements UmbrellaEntry{

	protected static final String API_URL_BASE = "localhost:8000/";
	
	private UmbrellaEntry service;
	
	public UmbrellaEntryImpl() {
		Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(UmbrellaEntry.class);
	}
	@Override
	public Call<User> createUser(String content_type, String username, String password, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Call<Profile> createProfile(String content_type, User u, String username_inst, String password_inst,
			String manager_identifier) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
