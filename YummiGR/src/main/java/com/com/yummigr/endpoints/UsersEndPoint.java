package com.com.yummigr.endpoints;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	
	
	
	@PostMapping(value="/c/" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createUser(
			@RequestParam String first_name,
			@RequestParam String last_name,
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email,
			@RequestParam boolean actived,
			@RequestParam String identifier
			, HttpServletResponse response , HttpServletRequest request) {
		
		boolean result = this.userService.createUser(first_name,last_name,email,username,password,actived,identifier);
		if(!result) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			request.setAttribute("forbbiden", "There is already a user with this username.please insert again.");
		}else {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
		}
	}
	
	
	

}
