package com.com.yummigr.endpoints;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
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
			@RequestParam String phone_number, @RequestParam String subject_message,@RequestParam String email, @RequestParam String identifier,HttpServletRequest request, HttpServletResponse response) {
	
			boolean resul = this.contactService.createNewContact(email,subject_message, phone_number, identifier, message);
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
	@PostMapping(value="/messenger/contact/id/", produces = MediaType.APPLICATION_JSON_VALUE)
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
																@RequestParam String message , @RequestParam String subject_message ,@RequestParam String phone_number,
																@RequestParam String id_contact , @RequestParam String identifier ){
		boolean result = this.contactService.updateContact(id_contact,identifier,message,subject_message,email,phone_number);
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
	@DeleteMapping(value="/messenger/contact/d/" , consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CreateContactMessenger> deleteContact(HttpServletRequest request, HttpServletResponse response,@RequestParam String identifier, @RequestParam String email, @RequestParam String phone_number){

		boolean result = this.messengerService.actionDeleteContact(identifier,email,phone_number);
		if(result){
			CreateContactMessenger e = new CreateContactMessenger("delete contact"
			,"delete contact sucess",null);
			return ResponseEntity.status(HttpServletResponse.SC_OK).body(e);
		}else{
			CreateContactMessenger e = new CreateContactMessenger("delete contact"
					,"forbbiden delete contact",null);
			return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(e);
		}

	}
	/**
	 * 
	 * @param identifier
	 * @param response
	 * @return
	 */
	@GetMapping(value="/messenger/g/identifier/", produces =  MediaType.APPLICATION_JSON_VALUE)
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
	
	@PostMapping(value="/messenger/s/email/all/p/", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendEMailDTO> sendEmailMessengerConnectorPredetermined(HttpServletRequest request , HttpServletResponse response,
			@RequestParam String identifier , @RequestParam boolean activate,
			@RequestParam String email, @RequestParam String password
			) throws Exception{
		
		
		String result = this.messengerService.activateSendEmailMessengerAllPredetermined(this.javaMailSender, identifier, activate, email, password);
		
		if(result != null) {
			SendEMailDTO sendto = new SendEMailDTO("sending email for all contacts"
				,"this configuration send email and message pre-defined for all contacts of the list its enabled."
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
	/**
	 * 
	 * @param request
	 * @param response
	 * @param identifier
	 * @return
	 * @throws Exception 
	 */
	@PostMapping(value="/messenger/s/email/all/" , produces = MediaType.APPLICATION_JSON_VALUE)
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

	
	@PostMapping(value="/messenger/s/email/select/p/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendEmailSelDTO> sendEmailSelectPredetermined(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam String identifier, @RequestParam boolean activate,
			@RequestParam String email, @RequestParam String password,
			@RequestParam String emails_contact_select
	) throws Exception{
		String result = this.messengerService.activateSendEmailMessengerSelectedContactsPredetermined(this.javaMailSender,identifier,activate,email,password,emails_contact_select);
		if(result != null) {
			SendEmailSelDTO sendEmailSelDTO = new SendEmailSelDTO("sending email for all contacts",
					"this configuration send email and message predetermined for select contacts of the list its enabled."
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

	@GetMapping(value="/messenger/s/email/all/cancel/" , produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@GetMapping(value="/messenger/s/sms/all/cancel/" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendEMailDTO> cancelSendSMSMessengerConnector(
			@RequestParam("identifier") String identifier , HttpServletRequest request , HttpServletResponse response){
		
		List<String> result = this.messengerService.stopActivatorSMS(this.messengerService.ActivatorGetSMSSch(identifier));
		
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


	//SMS SENDER;
	@PostMapping(value="/messenger/s/sms/all/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendEMailDTO> senderSmsAll(@RequestParam String identifier,
													 @RequestParam String username,
													 @RequestParam String password,
													 @RequestParam boolean activate,
													 @RequestParam String subject_message,
													 @RequestParam String message) throws Exception {
		String result = this.messengerService.activateSendSMSMessengerAllContacts(identifier,username,password,activate,subject_message,message);
		if(result != null){
			SendEMailDTO dt = new SendEMailDTO("sending sms for all contacts","broadcast of sender sms activate",result,true);

			return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(dt);
		}
		SendEMailDTO dt = new SendEMailDTO("error for activate send sms for all contacts.","this configuration send sms for all contacts of the list its not enabled.",result,true);
		return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(dt);
	}

	
	@PostMapping(value="/messenger/s/sms/all/p/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SendEMailDTO> senderSmsAllPredetermined(@RequestParam String identifier,
													 @RequestParam String username,
													 @RequestParam String password,
													 @RequestParam boolean activate
													 ) throws Exception {
		String result = this.messengerService.activateSendSMSMessengerAllContactsPreDetermined(identifier,username,password,activate);
		if(result != null){
			SendEMailDTO dt = new SendEMailDTO("sending sms message predetermined for all contacts","broadcast of sender sms activate",result,true);
			return ResponseEntity.status(HttpServletResponse.SC_ACCEPTED).body(dt);
		}
		SendEMailDTO dt = new SendEMailDTO("error for activate send sms for all contacts.","this configuration send sms for all contacts of the list its not enabled.",result,true);
		return ResponseEntity.status(HttpServletResponse.SC_FORBIDDEN).body(dt);
	}

}
