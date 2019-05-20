package com.com.yummigr.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.com.yummigr.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Integer>{
	
	
	@Query(value="select * from yummi_user where username=:username and actived=true",nativeQuery=true)
	User findByUserNameEnabled(@Param("username") String username);
	
	
	@Query(value="select * from yummi_user where username=:username",nativeQuery=true)
	List<User> findUserbyUsernameValidator(@Param("username") String username);
	

}
