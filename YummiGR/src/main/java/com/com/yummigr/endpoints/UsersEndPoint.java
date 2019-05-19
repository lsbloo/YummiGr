package com.com.yummigr.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.com.yummigr.services.UserService;
import com.com.yummigr.models.User;

/**
 * responsavel pelas interações de usuário
 * 	-> criação de usuario( url publica )
 * 	-> visualização de usuario (url privada {admin})
 *  -> desative usuario (url privada {admin} )
 *  -> update de usuario (url privada {user} logado}
 *  
 * @author osvaldoairon
 *
 */
@RestController
@RequestMapping("/yummicr/api/v1/mgmnt/user")
public class UsersEndPoint {
	
	
	@Autowired 
	private UserService userService;
	
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value="/c/" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createUser(@RequestBody User user) {
		this.userService.createUser(user);
	}
	
	
	

}
