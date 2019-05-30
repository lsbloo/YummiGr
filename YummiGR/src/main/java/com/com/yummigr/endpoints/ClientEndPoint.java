package com.com.yummigr.endpoints;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.com.yummigr.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import com.com.yummigr.models.Messenger;
import com.com.yummigr.services.ContactService;
import com.com.yummigr.services.MessengerService;
import com.com.yummigr.services.ScheduleService;
import com.com.yummigr.services.UserService;
import com.com.yummigr.validator.core.Result;

@RestController
@RequestMapping("/yummicr/api/v1/toolkit")
public class ClientEndPoint {
	
	
	private final UserService userService;
	
	private final MessengerService messengerService;
	
	private final ScheduleService scheduleService;
	
	private ContactService contactService;
	
	private JavaMailSender javaMailSender;
	@Autowired
	public ClientEndPoint(UserService userService , MessengerService service
			, ScheduleService scheduleService , ContactService contactService 
			, JavaMailSender javaMailSender) {
		this.userService=userService;
		this.messengerService=service;
		this.scheduleService=scheduleService;
		this.contactService=contactService;
		this.javaMailSender=javaMailSender;
	}
	/**
	 * creates a messenger connector for an associated user, 
	 * an associated user can only have a messenger co-connector
	 * @param
	 * @param
	 * @param
	 * @return ResponseEntity
	 */
	@PostMapping(value="/messenger/c/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FailureConnectorMessenger> createMessengerUser(@RequestParam String identifierUser , @RequestParam String account_sid,
			@RequestParam String auth_token){
		
		boolean result = this.messengerService.createConnectorMessenger(identifierUser, account_sid, auth_token);
		if(!result) {
			FailureConnectorMessenger msg_f = new FailureConnectorMessenger("There is already a connector message associated with this user.");
			
			return ResponseEntity
					.status(HttpServletResponse.SC_FORBIDDEN).body(msg_f);
		}else {
			FailureConnectorMessenger msg_a = new FailureConnectorMessenger("connector messagem successfully created");
			
			return ResponseEntity
					.status(HttpServletResponse.SC_CREATED).body(msg_a);
		}
		
	}
	
	@PostMapping(value="/messenger/schedule/c/" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreatedDtoSchdule> createSchedule(@RequestParam String time, @RequestParam String identifier,HttpServletResponse response, HttpServletRequest request){
		boolean result = this.scheduleService.createSchedule(time,identifier);
		if(result) {
			CreatedDtoSchdule creat_sh = new CreatedDtoSchdule("object team scheduler successfully created!");
			
			return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(creat_sh);
		}else {
			CreatedDtoSchdule creat_f = new CreatedDtoSchdule("Enter the time field correctly can not be null or different from zero"
					+ "    Example input: 500  "
					+ "Enter identifier of messenger_user correctly");
			
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(creat_f);
		}
	}
	
	
	@PostMapping(value = "/messenger/contact/c/" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateContactMessenger> createContactMessengerConnection(@RequestParam String message,
			@RequestParam String phone_number, @RequestParam String email, @RequestParam String identifier,HttpServletRequest request, HttpServletResponse response) {
	
			boolean resul = this.contactService.createNewContact(email, phone_number, identifier, message);
			if(resul) {
				CreateContactMessenger message_sul = new CreateContactMessenger("successfully create",
						"new contact added to user messenger connector.",null);
				return ResponseEntity.status(HttpServletResponse.SC_CREATED).body(message_sul);
			}
			CreateContactMessenger message_fail = new CreateContactMessenger("unsuccessful creation"
					,"it was not possible to create a new contact for the specified messenger connector, "
							+ "check the identifier, email, phone_number and message fields.",null);
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(message_fail);
		
		
	}

	/**
	 * GET THIS ID FOR OBJECT CONTACT BY EMAIL PARAM
	 * @param request
	 * @param email
	 * @return
	 */
	@GetMapping(value="/messenger/contact/id/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateContactMessenger> getContactId(HttpServletRequest request, @RequestParam String email,@RequestParam String phone_number, @RequestParam String identifier){
		String result = this.messengerService.getContactId(email,phone_number,identifier);
		if(result != null){
			CreateContactMessenger	c = new CreateContactMessenger("Acceept"
			,null,result);
			return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(c);
		}
		else{
			CreateContactMessenger	c_error = new CreateContactMessenger("Forbbiden"
					,null,result);
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(c_error);
		}
	}

	/**
	 * update contact by id;
	 * @param request
	 * @param email
	 * @param message
	 * @param phone_number
	 * @return
	 */
	// Update Contact
	@PutMapping(value="/messenger/contact/u/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateContactMessenger> updateContact(HttpServletRequest request, @RequestParam String email,
																@RequestParam String message , @RequestParam String phone_number,
																@RequestParam String id_contact , @RequestParam String identifier ){
		boolean result = this.contactService.updateContact(id_contact,identifier,message,email,phone_number);
		if(result){
			CreateContactMessenger c = new CreateContactMessenger("aceppt"
			,"update contact" , id_contact);
			return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(c);
		}else{
			CreateContactMessenger c = new CreateContactMessenger("forbbiden"
					,"update contact" , id_contact);
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(c);
		}
	}


	// Delete Contact

	@DeleteMapping(value="/messenger/contact/d/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateContactMessenger> deleteContact(@RequestParam String id_contact){

		return null;
	}

	/**
	 * 
	 * @param identifier
	 * @param response
	 * @return
	 */
	@GetMapping(value="/messenger/g/identifier", produces =  MediaType.APPLICATION_JSON_VALUE)
	public Messenger getConnectonMessengerUser(@RequestParam String identifier
			,HttpServletResponse response) {
		boolean result = this.messengerService.getMessengerConnectorUser(identifier);
		if(result) {
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			return this.messengerService.searchConnectorMessengerUser(identifier);
		}else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param identifier
	 * @return
	 * @throws Exception 
	 */
	@GetMapping(value="/messenger/s/email/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendEMailDTO> sendEmailMessengerConnector(HttpServletRequest request , HttpServletResponse response,
			@RequestParam String identifier , @RequestParam boolean activate,
			@RequestParam String email, @RequestParam String password,
			@RequestParam String message , @RequestParam String subject_message) throws Exception {
		
		String result = this.messengerService.activateSendEmailMessengerAll(this.javaMailSender,identifier, activate,email,password, message , subject_message);
		if(result != null) {
			SendEMailDTO sendto = new SendEMailDTO("sending email for all contacts"
				,"this configuration send email for all contacts of the list its enabled."
					,result + " Milliseconds. ",true);
			return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED)
					.body(sendto);
		}else {
			SendEMailDTO sendto = new SendEMailDTO("error for activate send email for all contacts."
					,"this configuration send email for all contacts of the list its not enabled."
						,result,false);
				return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN)
						.body(sendto);
		}
	}

	@PostMapping(value="/messenger/s/email/select/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendEmailSelDTO> sendEmailSelect(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam String identifier, @RequestParam boolean activate,
			@RequestParam String email, @RequestParam String password,@RequestParam String message, @RequestParam String subject_message,
			@RequestParam String emails_contact_select
	) throws Exception{
		String result = this.messengerService.activateSendEmailMessengerSelectedContacts(this.javaMailSender,identifier,activate,email,password,message,subject_message,emails_contact_select);
		if(result != null) {
			SendEmailSelDTO sendEmailSelDTO = new SendEmailSelDTO("sending email for all contacts",
					"this configuration send email for select contacts of the list its enabled."
					, result + "Milliseconds", true);
			return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(sendEmailSelDTO);
		}else{
			SendEmailSelDTO sendto = new SendEmailSelDTO("error for activate send email for all contacts."
					,"this configuration send email for select contacts of the list its not enabled."
					,result,false);
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN)
					.body(sendto);
		}

	}

	@GetMapping(value="/messenger/s/email/all/cancel" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendEMailDTO> cancelSendEmailMessengerConnector(
			@RequestParam("identifier") String identifier , HttpServletRequest request , HttpServletResponse response){
		
		List<String> result = this.messengerService.stopActivatorSch(this.messengerService.ActivatorGetSchedule(identifier));
		
		if(result!=null) {
			SendEMailDTO sendto = new SendEMailDTO(result.get(1),result.get(3), result.get(2),
					Boolean.valueOf(result.get(0)));
		
			return ResponseEntity.status(response.SC_ACCEPTED).body(sendto);
		}
		else {
			SendEMailDTO sendto = new SendEMailDTO("Activator Schdule","Dont Found", "0",
					false);
		
			return ResponseEntity.status(response.SC_BAD_REQUEST).body(sendto);
		}
	}

}
