package com.com.yummigr.security;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import com.com.yummigr.services.UserService;
import com.com.yummigr.models.Privilege;
import com.com.yummigr.models.Role;

/**
 * Define all interactions of dataloaders and permissions acess for all the users;
 * @author osvaldoairon
 *
 */
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent>{
	
	boolean s = false;
	/**
	 * default service for manager users;
	 */
	private final UserService userService;

	@Autowired
	public DataLoader(UserService userService) {
		this.userService=userService;
	}
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
		if(s) {
			return ;
		}
		Privilege privilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege privilege_write = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
		
		Collection<Privilege> adminPrivileges = Arrays.asList(privilege,privilege_write);
		createRoleIfNotFound("ADMIN" , adminPrivileges);
		createRoleIfNotFound("USER" , Arrays.asList(privilege));
		
		s = true;
	}
	
	@Transactional
	private Role createRoleIfNotFound(String param_role , Collection<Privilege> collection) {
		Role rol = this.userService.getRoleByName(param_role);
		if(rol == null) {
			Role r = new Role(param_role);
			r.setPrivileges(collection);
			this.userService.insertRoleEntity(r);
		}
		return rol;
	}
	@Transactional
	private Privilege createPrivilegeIfNotFound(String param_privilege) {
		Privilege p = this.userService.getPrivilegeByName(param_privilege);
		
		if(p == null) {
			Privilege priv = new Privilege(param_privilege);
			this.userService.insertPrivilegeEntity(priv);
		}
		return p;
	}
}
