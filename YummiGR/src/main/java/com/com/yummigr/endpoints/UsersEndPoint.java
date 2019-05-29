package com.com.yummigr.endpoints;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.com.yummigr.services.UserService;
import com.com.yummigr.dtos.FailureCreateUser;
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
@RequestMapping("/yummicr/api/v1/mgmnt/users")
public class UsersEndPoint {


	private UserService userService;

	@Autowired
	public UsersEndPoint(UserService userService){
		this.userService=userService;
	}


	@PostMapping(value="/c/" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FailureCreateUser> createUser(
			@RequestParam String first_name,
			@RequestParam String last_name,
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email,
			@RequestParam boolean actived,
			@RequestParam String identifier
			, HttpServletResponse response , HttpServletRequest request) {
		FailureCreateUser msgf = new FailureCreateUser("There is already a user with this username.please insert again.");
		boolean result = this.userService.createUser(first_name,last_name,email,username,password,actived,identifier);
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("").buildAndExpand().toUri();
		response.setHeader("Location", uri.toASCIIString());
		if(!result) {
			return ResponseEntity
					.status(HttpServletResponse.SC_FORBIDDEN).body(msgf);
		}else {
			FailureCreateUser msg_accept = new FailureCreateUser("User created successfully");
			return ResponseEntity
					.status(HttpServletResponse.SC_CREATED).body(msg_accept);
		}
	}

}
