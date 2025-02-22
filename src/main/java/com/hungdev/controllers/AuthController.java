/*
 * package com.hungdev.controllers;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity; import
 * org.springframework.security.authentication.AuthenticationManager; import
 * org.springframework.security.authentication.
 * UsernamePasswordAuthenticationToken; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestBody; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RestController;
 * 
 * import com.hungdev.dtos.AuthenticateRequest; import
 * com.hungdev.dtos.AuthenticateResponse; import
 * com.hungdev.services.JwtService;
 * 
 * 
 * 
 * @RestController
 * 
 * @RequestMapping(value = "/api/auth") public class AuthController { private
 * AuthenticationManager authenticationManager; private JwtService jwtService;
 * 
 * @Autowired public AuthController(AuthenticationManager authenticationManager,
 * JwtService jwtService) { this.authenticationManager = authenticationManager;
 * this.jwtService = jwtService; }
 * 
 * @PostMapping(value = "/authenticate") public
 * ResponseEntity<AuthenticateResponse> authenticate(@RequestBody
 * AuthenticateRequest authenticateRequest) { Authentication authentication =
 * authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
 * authenticateRequest.getUsername(), authenticateRequest.getPassword()) );
 * UserDetails userDetails = (UserDetails) authentication.getPrincipal(); String
 * jwtToken = jwtService.generateToken(userDetails); AuthenticateResponse
 * authenticateResponse = new AuthenticateResponse(jwtToken); return new
 * ResponseEntity<AuthenticateResponse>(authenticateResponse, HttpStatus.OK); }
 * }
 */

package com.hungdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import com.hungdev.services.JwtService;

@Controller

@RequestMapping(value = "/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/auth/login";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@PostMapping("/login")
	public String login(@RequestParam String username,

			@RequestParam String password, HttpServletResponse response, Model model) {
		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			UserDetails userDetails = (UserDetails) authentication.getPrincipal();
			String token = jwtService.generateToken(userDetails);

			Cookie jwtCookie = new Cookie("JWT_TOKEN", token);
			jwtCookie.setHttpOnly(true);
			jwtCookie.setMaxAge(60);
			jwtCookie.setPath("/");
			response.addCookie(jwtCookie);

			return "redirect:/dashboard";
		} catch (Exception e) {
			model.addAttribute("error", "Sai tài khoản hoặc mật khẩu!");
			return "login";
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletResponse response) {
		Cookie jwtCookie = new Cookie("JWT_TOKEN", null);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setMaxAge(0);
		jwtCookie.setPath("/");
		response.addCookie(jwtCookie);
		return "redirect:/auth/login";
	}
}
