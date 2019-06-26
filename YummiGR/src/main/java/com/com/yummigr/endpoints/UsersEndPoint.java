package com.com.yummigr.endpoints;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.com.yummigr.dtos.DesactiveUser;
import com.com.yummigr.dtos.DtoUserInformation;
import com.com.yummigr.models.Contacts;
import com.com.yummigr.models.Messenger;
import com.com.yummigr.services.MessengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
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

	private MessengerService messengerService;

	@Autowired
	public UsersEndPoint(UserService userService,MessengerService messengerService){
		this.userService=userService;
		this.messengerService=messengerService;
	}







	@GetMapping(value="/list/select/user/",consumes = MediaType.APPLICATION_JSON_VALUE)
	@Secured("ADMIN")
	public ResponseEntity<List<DtoUserInformation>> getUserEnabled(@RequestParam String identifier){
		List<DtoUserInformation> users = new ArrayList<DtoUserInformation>();
		User u = this.userService.getUserByIdentifier(identifier);
		if(u != null){
			DtoUserInformation d  = new DtoUserInformation();
			List<Contacts> listc = this.messengerService.getAllContactsForMessenger(this.messengerService.searchConnectorMessengerUser(u.getIdentifier()));
			d.setMessenger(this.messengerService.searchConnectorMessengerUser(u.getIdentifier()));
			d.setContatos(listc);
			d.setUsername(u.getUsername());
			d.setStatus_account(u.isActived());
			d.setIdentifier(u.getIdentifier());
			users.add(d);
		}
		return ResponseEntity.status(HttpServletResponse.SC_OK).body(users);
	}

	@GetMapping(value="/list/all/users/" , consumes=MediaType.APPLICATION_JSON_VALUE)
	@Secured("ADMIN")
	public ResponseEntity<List<DtoUserInformation>> getAllUsersEnabled(){
		List<DtoUserInformation> users = new ArrayList<DtoUserInformation>();
		for(User u  : this.userService.getAllUsers()){
			DtoUserInformation d  = new DtoUserInformation();
			List<Contacts> listc = this.messengerService.getAllContactsForMessenger(this.messengerService.searchConnectorMessengerUser(u.getIdentifier()));
			d.setMessenger(this.messengerService.searchConnectorMessengerUser(u.getIdentifier()));
			d.setContatos(listc);
			d.setUsername(u.getUsername());
			d.setStatus_account(u.isActived());
			d.setIdentifier(u.getIdentifier());

			users.add(d);

		}
		return ResponseEntity.status(HttpServletResponse.SC_OK).body(users);

	}

	@DeleteMapping(value="/desative/account/user" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<DesactiveUser> desactiveUser(@RequestParam String identifier){

		boolean result = this.userService.desativeUser(identifier);
		if(result){
			DesactiveUser dd = new DesactiveUser("User: " + identifier , " This user are desactived!");
			return ResponseEntity.status(HttpServletResponse.SC_OK).body(dd);
		}
		else{
			DesactiveUser dd = new DesactiveUser("User: " + identifier , " This user don't are desactived!");
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(dd);
		}


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
			, HttpServletResponse response , HttpServletRequest request) throws IOException {
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
