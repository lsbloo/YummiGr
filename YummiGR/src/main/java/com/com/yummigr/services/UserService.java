package com.com.yummigr.services;

import java.util.Arrays;
import java.util.List;

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
	
	protected static final String USER="ROLE_USER";
	
	
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

	public void insertRelationUser(Long user_id , Long role_id){
		this.userRepository.insertRelationUser(user_id,role_id);
	}

	public boolean searchUserByIdentifier(){
		User u = this.userRepository.findUserByIdentifier("admin");
		if(u == null){
			return true;
		}
		return false;
	}


	public void addUser(User u){
		this.userRepository.save(u);
	}
	public List<User> getAllUsers(){
		return this.userRepository.getAllUsersEnabled();
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
			u.setRoles(Arrays.asList(this.roleRepository.findRoleByNameParam(USER)));
			this.userRepository.save(u);
		}else {
			return false;
		}
		return true;
	}

	public boolean desativeUser(String identifier){
		User u = this.userRepository.findByUserIdentifier(identifier);
		if(u!=null){
			this.userRepository.desativeUser(u.getId());
			return true;
		}
		return false;
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
	 * @param
	 */
	public void insertRoleEntity(Role role) {
		this.roleRepository.save(role);
	}
	
	public int getRoleIdByUserId(Long user_id) {
		return this.roleRepository.getIdRoleAssociateUser(user_id);
	}
	
	public String getRoleById(Integer role_id) {
		return this.roleRepository.getRoleById(role_id);
	}
	/**
	 * !! find by identifier and actived (true);
	 * @param identifier
	 * @return
	 */
	public User getUserByIdentifier(String identifier) {
		try {
		return this.userRepository.findUserByIdentifier(identifier);
		}catch(NullPointerException e) {
			return null;
		}
	}
	
	public Integer getMessengerIdByIdUser(Long user_id) {
		return this.userRepository.getMessengerId(user_id);
	}
}
