package com.hungdev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.hungdev.entities.User;
import com.hungdev.repositories.UserRepository;

@Component
public class AuthenticatedUserProvider {

	@Autowired
	private UserRepository userRepository;

	public User getAuthenticatedUser() {
		/*
		 * Object principal =
		 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		 * 
		 * 
		 * if (principal instanceof UserDetails) { String username = ((UserDetails)
		 * principal).getUsername(); Optional<User> user =
		 * userRepository.findByUsername(username); return user.orElseThrow(() -> new
		 * RuntimeException("User not found")); }
		 * 
		 * throw new RuntimeException("User is not authenticated");
		 */return null;
	}
}
