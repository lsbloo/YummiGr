package com.com.yummigr.umbrella;


import com.com.yummigr.models.User;

import retrofit.Call;
import retrofit.http.GET;

/**
 * define all iteractions with umbrellaServer entrypoints;
 * @author osvaldoairon
 *
 */


public interface UmbrellaEntry {
	
	/**
	 * Example
	 * @return
	 */
	@GET("THIS ENTRYPOINT")
	Call<User> getUsers();

	
}
