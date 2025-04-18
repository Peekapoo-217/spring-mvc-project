package com.hungdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.hungdev.dtos.AuthenticateRequest;
import com.hungdev.dtos.AuthenticateResponse;
import com.hungdev.dtos.SignUpRequest;
import com.hungdev.services.AuthService;
import com.hungdev.services.JwtService;

@Controller

@RequestMapping(value = "/auth")
public class AuthController {

	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	private AuthService authService;

	@Autowired
	public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, AuthService authService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
		this.authService = authService;
	}

	@GetMapping("/signup")
	public String showSignupPage() {
		return "signup";
	}

	@GetMapping("/")
	public String home() {
		return "redirect:/auth/login";
	}

	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String handleSignUp(@ModelAttribute SignUpRequest signUpRequestDTO, RedirectAttributes redirectAttributes) {
		try {
			authService.signUp(signUpRequestDTO);
			redirectAttributes.addFlashAttribute("message", "User registered successfully!");
			return "redirect:/auth/login";
		} catch (ResponseStatusException ex) {
			ex.printStackTrace();
			redirectAttributes.addFlashAttribute("message", ex.getReason());
			return "redirect:/auth/sign-up";
		} catch (Exception ex) {
			ex.printStackTrace();
			redirectAttributes.addFlashAttribute("message", "Internal server error");
			return "redirect:/auth/sign-up";
		}
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthenticateRequest loginRequest, HttpServletResponse response) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String token = jwtService.generateToken(userDetails);

		Cookie jwtCookie = new Cookie("jwt_token", token);
		jwtCookie.setPath("/");
		jwtCookie.setHttpOnly(true);
		jwtCookie.setSecure(true);
		jwtCookie.setMaxAge(24 * 60 * 60);
		jwtCookie.setAttribute("SameSite", "None");
		response.addCookie(jwtCookie);
		return ResponseEntity.ok().header("Location", "/home").body(new AuthenticateResponse(token));
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Cookie jwtCookie = new Cookie("jwt_token", null);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setMaxAge(0);
		jwtCookie.setPath("/");
		response.addCookie(jwtCookie);

		request.getSession().invalidate();
		return "redirect:/auth/login";
	}

}
