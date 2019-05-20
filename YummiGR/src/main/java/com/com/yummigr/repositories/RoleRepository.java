package com.com.yummigr.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.com.yummigr.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
	
	
	@Query(value="select * from role where name=:name", nativeQuery=true)
	Role findRoleByNameParam(@Param("name") String name);
	
	
	@Transactional
	@Modifying
	@Query(value="insert into role (id,name) values (:id,:name)", nativeQuery=true)
	void insertRole(@Param("id") Integer id , @Param("name") String name);
	
	
	@Query(value= "select role_id from usuarios_role where usuarios_id=:user_id",nativeQuery=true)
	Integer getIdRoleAssociateUser(@Param("user_id") Long user_id);
	
	
	@Query(value = "select name from role where id=:id",nativeQuery=true)
	String getRoleById(@Param("id") Integer id);

}
