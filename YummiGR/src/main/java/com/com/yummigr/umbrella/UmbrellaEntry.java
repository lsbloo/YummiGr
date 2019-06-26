package com.com.yummigr.umbrella;


import com.com.yummigr.models.User;


import com.com.yummigr.umbrella.core.Profile;
import com.com.yummigr.umbrella.core.UmbrellaUser;
import com.com.yummigr.umbrella.core.ConnectorProfiles;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;



/**
 * define all iteractions with umbrellaServer entrypoints;
 * @author osvaldoairon
 *
 */


public interface UmbrellaEntry {
	
	
	
	/**
	 * permite criar um usuário.
	 * @param content_type
	 * @param user
	 * @return
	 */
	@POST("umbrella/api/v1/mgmnt/users/c/")
	Call<UmbrellaUser> createUser(@Header("Content-Type") String content_type,
			@Body UmbrellaUser user);

	/**
	 * permite criar n perfis instagram para um usuário/gestor.
	 * @param content_type
	 * @param p
	 * @param basic
	 * @return
	 */
	@POST("umbrella/api/v1/mgmnt/profiles/c/")
	Call<Profile> createProfile(@Header("Content-Type") String content_type , 
			@Body Profile p,
			@Header("Authorization") String basic);
	
	
	/**
	 * cria conexão entre o perfil do instagram e o instagram.
	 * permite deixar a sessão logada;
	 * @param content_type
	 * @param c
	 * @param basic
	 * @return
	 */
	@POST("umbrella/api/v1/toolkit/connect/profile/")
	Call<ConnectorProfiles> connectProfile(@Header("Content-Type") String content_type,
			@Body ConnectorProfiles c ,@Header("Authorization") String basic);
	
	/**
	 * retorna novos seguidores do perfil, caso existam.
	 * @param content_type
	 * @param c
	 * @param basic
	 * @return
	 */
	@POST("umbrella/api/v1/toolkit/follow/g/")
	Call<ConnectorProfiles> getNewMyFollowers(@Header("Content-Type") String content_type,
	@Body ConnectorProfiles c , @Header("Authorization") String basic);
	
	
	/**
	 * dada uma determinada lista de tags. Procura por perfis que tenham algo relacionado com essas tags.
	 * 
	 * @param content_type
	 * @param c
	 * @param basic
	 * @return
	 */
	@POST("umbrella/api/v1/toolkit/follow/tags/")
	Call<ConnectorProfiles> followUsersByListOfTags(@Header("Content-Type") String content_type,
			@Body ConnectorProfiles c , @Header("Authorization") String basic);
	
	
	/**
	 * segue alguns amigos aleatorios dos meus seguidores.
	 * @param content_type
	 * @param c
	 * @param basic
	 * @return
	 */
	@POST("umbrella/api/v1/toolkit/follow/friends/")
	Call<ConnectorProfiles> followFollowersOfMyFriend(@Header("Content-Type") String content_type,
			@Body ConnectorProfiles c, @Header("Authorization") String basic);
	
	
	
}
