package com.com.yummigr.umbrella;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import com.com.yummigr.models.User;
import com.com.yummigr.umbrella.core.ConnectorProfiles;
import com.com.yummigr.umbrella.core.Profile;
import com.com.yummigr.umbrella.core.UmbrellaUser;

import okhttp3.Credentials;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
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

	/**
	 * criar uma variavel de ambiente para setar a url base da umbrella.
	 *
	 * 
	 */
	protected static final String API_URL_BASE = "http://192.168.0.117:8000/";
	
	/**
	 * default;
	 */
	public static final String CONTENT_TYPE = "application/json";
	
	
	private UmbrellaEntry service;
	
	public UmbrellaEntryImpl() {
		
		Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        this.service = retrofit.create(UmbrellaEntry.class);
	}
	
	
	public String getAuthorization(String username,String password) throws UnsupportedEncodingException {
		 String b = Credentials.basic(username, password);
		 return b;
	}
	
	public void create(UmbrellaUser u, String content_type) throws IOException {
		
		Call<UmbrellaUser> call = service.createUser(content_type, u);
		 call.enqueue(new Callback<UmbrellaUser>() {
			@Override
			public void onResponse(Call<UmbrellaUser> call, Response<UmbrellaUser> response) {
				// adicionar ao logger
				// :D

			}

			@Override
			public void onFailure(Call<UmbrellaUser> call, Throwable throwable) {

			}
		});

	}
	
	public void create(User u ,Profile e , String content_type) throws IOException {
		System.err.println(u.getUsername()  +" : "+ u.getFirst_name());

		Call<Profile> call = this.service.createProfile(content_type,e);
		call.enqueue(new Callback<Profile>() {
			@Override
			public void onResponse(Call<Profile> call, Response<Profile> response) {


			}

			@Override
			public void onFailure(Call<Profile> call, Throwable throwable) {

			}
		});
		
	}
	
	public void connect(User u , ConnectorProfiles e , String content_type) throws IOException {
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<ConnectorProfiles> call = service.connectProfile(content_type, e, basic);
		call.enqueue(new Callback<ConnectorProfiles>() {
			@Override
			public void onResponse(Call<ConnectorProfiles> call, Response<ConnectorProfiles> response) {

			}

			@Override
			public void onFailure(Call<ConnectorProfiles> call, Throwable throwable) {

			}
		});

		
	}
	
	public void getNewFollowers(User u , ConnectorProfiles e , String content_type) throws IOException {
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<ConnectorProfiles> call = this.service.getNewMyFollowers(content_type, e, basic);
		call.enqueue(new Callback<ConnectorProfiles>() {
			@Override
			public void onResponse(Call<ConnectorProfiles> call, Response<ConnectorProfiles> response) {

			}

			@Override
			public void onFailure(Call<ConnectorProfiles> call, Throwable throwable) {

			}
		});

	}
	
	
	public void followUsersByListOfTags(User u , ConnectorProfiles e , String content_type) throws IOException {
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<ConnectorProfiles> call = service.followUsersByListOfTags(content_type, e, basic);
		call.enqueue(new Callback<ConnectorProfiles>() {
			@Override
			public void onResponse(Call<ConnectorProfiles> call, Response<ConnectorProfiles> response) {

			}

			@Override
			public void onFailure(Call<ConnectorProfiles> call, Throwable throwable) {

			}
		});

	}
	

	public void followFollowersOfMyFriend(User u , ConnectorProfiles e , String content_type) throws IOException {
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<ConnectorProfiles> call = service.followFollowersOfMyFriend(content_type, e, basic);
		call.enqueue(new Callback<ConnectorProfiles>() {
			@Override
			public void onResponse(Call<ConnectorProfiles> call, Response<ConnectorProfiles> response) {
				
			}

			@Override
			public void onFailure(Call<ConnectorProfiles> call, Throwable throwable) {

			}
		});
	}
}
