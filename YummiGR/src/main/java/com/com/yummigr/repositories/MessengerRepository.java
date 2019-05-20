package com.com.yummigr.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.com.yummigr.models.Messenger;

@Repository
public interface MessengerRepository extends CrudRepository<Messenger,Integer>{
	
	

	@Query(value="select * from messenger_user",nativeQuery=true)
	List<Messenger> getAllMessenger();
	
	
	@Query(value = "select * from messenger_user where account_sid=:account_sid or auth_token=:auth_token",
			nativeQuery=true)
	Messenger getMessengerConnectorIfExist(@Param("account_sid") String account_sid
			,@Param("auth_token") String auth_token);
	
	
	
	
	@Query(value="select messenger_user from users_messenger where yuumi_user_id=:id",nativeQuery=true)
	Integer getIdMessengerEntity(@Param("id") Long user_id);
	
	
	
	@Query(value="select * from messenger_user where id=:id",nativeQuery=true)
	Messenger getMessengerEntity(@Param("id") Integer user_id);
	
	
	
	@Transactional
	@Modifying
	@Query(value="insert into users_messenger (yuumi_user_id,messenger_user) values (:user_id,:messenger_id)",nativeQuery=true)
	void insertRelationMessengerUser(@Param("user_id") Long user_id , @Param("messenger_id") Long messenger_id);
	
}


