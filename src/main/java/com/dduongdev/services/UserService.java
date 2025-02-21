package com.dduongdev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dduongdev.entities.User;
import com.dduongdev.repositories.UserRepository;

@Service
public class UserService {
	private UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> findPaged(int pageIndex, int pageSize) {
		return userRepository.findPaged(pageIndex, pageSize);
	}
	
	
}
