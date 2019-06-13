package com.com.yummigr.security;

import java.util.Arrays;
import java.util.Collection;

import javax.transaction.Transactional;

import com.com.yummigr.models.User;
import com.com.yummigr.repositories.RoleRepository;
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

	private final RoleRepository roleRepository;
	/**
	 * Default Constructor;
	 * @param userService
	 * @param roleRepository
	 */
	@Autowired
	public DataLoader(UserService userService, RoleRepository roleRepository) {
		this.userService=userService;
		this.roleRepository = roleRepository;
	}
	protected static final String ADMIN="ROLE_ADMIN";
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		
		if(s) {
			return ;
		}
		Privilege privilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
		Privilege privilege_write = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
		
		Collection<Privilege> adminPrivileges = Arrays.asList(privilege,privilege_write);
		Role admin = createRoleIfNotFound("ROLE_ADMIN" , adminPrivileges);
		Role user = createRoleIfNotFound("ROLE_USER" , Arrays.asList(privilege));
		
		Role r = this.userService.getRoleByName("ROLE_ADMIN");

		if(this.userService.searchUserByIdentifier()) {
			User u = new User();
			u.setPassword("$2a$10$WwnEzg0ppWn1q1qBwgnMEe5PN5m.a00VKrwtg2HRvBrcirJPK90b2");
			u.setUsername("admin");
			u.setActived(true);
			u.setEmail("osvaldo.airon@dce.ufpb.br");
			u.setIdentifier("admin");
			u.setFirst_name("OSVALDO AIRON");
			u.setLast_name("CAVALCANTI");
			u.setRoles(Arrays.asList(this.roleRepository.findRoleByNameParam(ADMIN)));
			this.userService.addUser(u);
		}
		s = true;
	}

	private void insertRelationAdmin(Long user_id, Long role_id){
		this.userService.insertRelationUser(user_id,role_id);
	}

	
	/**
	 * creates the roles of users for administrator and normal user being 
	 * the administrator access permission with read and write powers and the normal user only reading
	 * @param param_role
	 * @param collection
	 * @return
	 */
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
