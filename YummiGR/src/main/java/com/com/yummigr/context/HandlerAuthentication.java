package com.com.yummigr.context;



import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.com.yummigr.models.User;
@Component
public class HandlerAuthentication {

	
	private Authentication authentication;
	
	public HandlerAuthentication() {
		
	}
	
	
	
	/**
	 * return Session of user authenticate;
	 * @return
	 */
	public User getUserAuthenticate() {
		this.authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
		
		if(this.authentication != null) {
			Object obj = this.authentication.getPrincipal();
			if(obj instanceof User) {
				return (User) obj;
			}
		}
		return null;
	}
	
	
}
