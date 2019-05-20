package com.com.yummigr.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.models.Privilege;
import com.com.yummigr.models.User;
import com.com.yummigr.validator.core.Result;
import com.com.yummigr.validator.UserValidator;
import com.com.yummigr.validator.core.*;
import com.com.yummigr.repositories.PrivilegeRepository;
import com.com.yummigr.models.Role;
import com.com.yummigr.repositories.RoleRepository;

@Service
public class UserService {
	
	
	
	private final UserRepository userRepository;
	
	private final UserValidator userValidator;
	
	private final PrivilegeRepository privilegeRepository;
	
	private final RoleRepository roleRepository;
	
	@Autowired
	public UserService(UserRepository userRepository , UserValidator userValidator, PrivilegeRepository privilegeRepository 
			,RoleRepository roleRepository) {
		this.userRepository=userRepository;
		this.userValidator = userValidator;
		this.privilegeRepository=privilegeRepository;
		this.roleRepository=roleRepository;
	}
	
	/**
	 * method responsible for creating users in the system.
	 * returns the boolean for the appropriate responseEntity.
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param username
	 * @param password
	 * @param actived
	 * @param identifier
	 * @return boolean entityResponse;
	 */
	public boolean createUser(String first_name, String last_name, String email, String username,String password, boolean actived , String identifier) {
		User u = new User();
		u.setActived(actived);
		u.setFirst_name(first_name);
		u.setLast_name(last_name);
		u.setEmail(email);
		u.setPassword(password);
		u.setIdentifier(identifier);
		u.setUsername(username);
		
		String pass = u.getPassword();
		u.setPassword(passwordEncoder().encode(pass));
		
		Result result = new 
				ValidatorBuilder<User>().
				apply(userValidator.checkIfExistUserByUsername())
				.validate(u);
		//System.err.println(result.getErrors());
		if(result.ok()) {
			this.userRepository.save(u);
		}else {
			return false;
		}
		return true;
	}

	/**
	 * creates a new bcryptpassword object and encrypts the user's password.
	 * 
	 * @return new instance of BcryptPasswordEncoder;
	 */
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * return privilege object by search name;
	 * @param name
	 * @return
	 */
	public Privilege getPrivilegeByName(String name) {
		return this.privilegeRepository.findPrivilegeByNameParam(name);
	}
	
	/**
	 * Insert new Privilege;
	 * @param name
	 * @param id
	 */
	public void insertPrivilege(String name, int id) {
		this.privilegeRepository.insertPrivilege(id, name);
	}
	
	/**
	 * Insert new Object Privilege;
	 * @param privilege
	 */
	public void insertPrivilegeEntity(Privilege privilege) {
		this.privilegeRepository.save(privilege);
	}
	
	
	/**
	 * return role object by search name;
	 * @param name
	 * @return
	 */
	public Role getRoleByName(String name) {
		return this.roleRepository.findRoleByNameParam(name);
	}
	
	/**
	 * Insert new Object Privilege;
	 * @param privilege
	 */
	public void insertRoleEntity(Role role) {
		this.roleRepository.save(role);
	}
}
