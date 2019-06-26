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
	 */
	protected static final String API_URL_BASE = "http://localhost:8000/";
	
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
	
	public UmbrellaUser create(UmbrellaUser u, String content_type) throws IOException {
		
		Call<UmbrellaUser> call = service.createUser(content_type, u);
		Response<UmbrellaUser>  res =call.execute();
		return res.body();
	}
	
	public Profile create(User u ,Profile e , String content_type) throws IOException {	
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<Profile> call = this.service.createProfile(content_type,e,basic);
		Response<Profile> res = call.execute();
		return res.body();
		
	}
	
	public ConnectorProfiles connect(User u , ConnectorProfiles e , String content_type) throws IOException {
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<ConnectorProfiles> call = service.connectProfile(content_type, e, basic);
		Response<ConnectorProfiles> res = call.execute();
		return res.body();
		
	}
	
	public ConnectorProfiles getNewFollowers(User u , ConnectorProfiles e , String content_type) throws IOException {
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<ConnectorProfiles> call = this.service.getNewMyFollowers(content_type, e, basic);
		Response<ConnectorProfiles> res = call.execute();
		return res.body();
	}
	
	
	public ConnectorProfiles followUsersByListOfTags(User u , ConnectorProfiles e , String content_type) throws IOException {
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<ConnectorProfiles> call = service.followUsersByListOfTags(content_type, e, basic);
		Response<ConnectorProfiles> res = call.execute();
		return res.body();
	}
	

	public ConnectorProfiles followFollowersOfMyFriend(User u , ConnectorProfiles e , String content_type) throws IOException {
		String basic = getAuthorization(u.getUsername(),u.getFirst_name());
		Call<ConnectorProfiles> call = service.followFollowersOfMyFriend(content_type, e, basic);
		Response<ConnectorProfiles> res = call.execute();
		return res.body();
	}
}
