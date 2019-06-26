package com.com.yummigr.umbrella;

import java.io.IOException;

import com.com.yummigr.models.User;
import com.com.yummigr.umbrella.core.Profile;
import com.com.yummigr.umbrella.core.UmbrellaUser;

import okhttp3.Credentials;
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

	protected static final String API_URL_BASE = "http://localhost:8000/";
	public static final String CONTENT_TYPE = "application/json";
	
	
	private UmbrellaEntry service;
	
	public UmbrellaEntryImpl() {
		Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(UmbrellaEntry.class);
	}
	
	public UmbrellaUser create(UmbrellaUser u, String content_type) throws IOException {
		Call<UmbrellaUser> call = service.createUser(content_type, u);
		Response<UmbrellaUser>  res =call.execute();
		return res.body();
	}
	
	public Profile create(User u ,Profile e , String content_type) throws IOException {	
		String basic = u.getUsername()+":"+u.getPassword();
		System.err.println(basic);
		Call<Profile> call = service.createProfile(content_type,e,basic);
		Response<Profile> res = call.execute();
		return res.body();
		
	}
	
	

}
