package com.com.yummigr.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.com.yummigr.services.TokenAuthenticationService;
import com.com.yummigr.models.User;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private TokenAuthenticationService tokenAuthenticationService;
	
	
	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		
		//User credentials = new ObjectMapper().readValue(request.getInputStream(), User.class);
		
		return getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						request.getParameter("username"), 
						request.getParameter("password"), 
						Collections.emptyList()
						)
				);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain,
			Authentication auth) throws IOException, ServletException {
		System.err.println(auth.getName());
		
		this.tokenAuthenticationService = new TokenAuthenticationService(response,auth.getName());
		this.tokenAuthenticationService.addAuthentication();
}

	
}
