package com.com.yummigr.security;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.com.yummigr.repositories.UserRepository;
import com.com.yummigr.models.User;
import static java.util.Collections.emptyList;

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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User u = userRepository.findByUserNameEnabled(username);
		
		
		if(u==null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(u.getUsername(), u.getPassword() , emptyList());
	}	

}
