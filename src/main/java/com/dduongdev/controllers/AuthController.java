package com.dduongdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dduongdev.dtos.AuthenticateRequest;
import com.dduongdev.dtos.AuthenticateResponse;
import com.dduongdev.services.JwtService;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
	private AuthenticationManager authenticationManager;
	private JwtService jwtService;
	
	@Autowired
	public AuthController(AuthenticationManager authenticationManager,
			JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping(value = "/authenticate")
	public ResponseEntity<AuthenticateResponse> authenticate(@RequestBody AuthenticateRequest authenticateRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authenticateRequest.getUsername(), 
						authenticateRequest.getPassword())
				);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String jwtToken = jwtService.generateToken(userDetails);
		AuthenticateResponse authenticateResponse = new AuthenticateResponse(jwtToken);
		return new ResponseEntity<AuthenticateResponse>(authenticateResponse, HttpStatus.OK);
	}
}