package com.com.yummigr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.com.yummigr.models.User;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
	

	@Modifying
	@Transactional
	@Query(value =" update yummi_user set actived=false where id=:id",nativeQuery = true)
	void desativeUser(@Param("id") Long id);


	@Query(value="select * from yummi_user where username=:username and actived=true",nativeQuery=true)
	User findByUserNameEnabled(@Param("username") String username);
	
	
	@Query(value="select * from yummi_user where username=:username",nativeQuery=true)
	List<User> findUserbyUsernameValidator(@Param("username") String username);
	
	
	@Query(value="select * from yummi_user where identifier=:identifier and actived=true", nativeQuery=true)
	User findUserByIdentifier(@Param("identifier") 
	String identifier);
	
	
	@Query(value="select messenger_user from users_messenger where yuumi_user_id=:id",nativeQuery=true)
	Integer getMessengerId(@Param("id") Long id);
	
	
	@Query(value="select * from yummi_user where identifier=:identifier and actived=true",nativeQuery=true)
	User findByUserIdentifier(@Param("identifier") String identifier);

}
