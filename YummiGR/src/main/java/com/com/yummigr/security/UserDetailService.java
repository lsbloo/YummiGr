package com.com.yummigr.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.services.UserService;
import com.com.yummigr.models.Role;
import com.com.yummigr.models.User;
import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * responsavel por procurar um usuario especificado pelo seu ID;
 * é necessario que o usuario esteva ativo no sistema; actived=true;
 * retorna um objeto do tipo UserDetails para ser autenticado no websecurityconfig.class;
 * @author osvaldoairon
 *
 */
@Service
@Transactional
public class UserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User u = userRepository.findByUserNameEnabled(username);
		
			if(u==null) {
				throw new UsernameNotFoundException(username);
			}
		
		return new org.springframework.security.core.userdetails.User(
				
		          u.getEmail(), u.getPassword(), u.isActived(), true, true, 
		          true, getAuthorities(u.getRoles(),u.getId()));
	}
	
		private Collection<? extends GrantedAuthority> getAuthorities(
		      Collection<Role> roles , Long id) {
		        return getGrantedAuthorities(roles,id);
		    }
		    private List<GrantedAuthority> getGrantedAuthorities(Collection<Role> roles , Long id) {
		        List<GrantedAuthority> authorities = new ArrayList<>();
		        //Integer id_r = this.userService.getRoleIdByUserId(id);
		        //String role = this.userService.getRoleById(role_id);
		        for (Role privilege : roles) {
		        	
		        	
		            authorities.add(new SimpleGrantedAuthority(privilege.getAuthority()));
		        }
		      
		        return authorities;
		    }
		    
		    
		    private List<String> getPrivileges(Collection<Role> roles) {
		        List<String> privileges = new ArrayList<>();
		        List<String> collection = new ArrayList<>();
		        for (Role role : roles) {
		            collection.add(role.getAuthority());
		        }
		        if(collection!=null) {
		        	for(int i =0 ; i<collection.size();i++) {
		        		System.err.println(privileges.get(i));
		        		privileges.add(collection.get(i));
		        	}
		        }
		        return privileges;
		        
		    }

}
