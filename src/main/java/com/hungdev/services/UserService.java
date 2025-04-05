package com.hungdev.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hungdev.entities.User;
import com.hungdev.repositories.UserRepository;

/*
 * @Service public class UserService { private UserRepository userRepository;
 * 
 * @Autowired public UserService(UserRepository userRepository) {
 * this.userRepository = userRepository; }
 * 
 * 
 * public List<User> findPaged(int pageIndex, int pageSize, int userId) { return
 * userRepository.findPaged(pageIndex, pageSize, userId); }
 * 
 * 
 * public Optional<User> findByUsername(String username) { return
 * userRepository.findByUsername(username); }
 * 
 * public void saveUser(User user) { userRepository.save(user); }
 * 
 * public void updateUser(User user) { userRepository.update(user); }
 * 
 * public void deleteUser(String username) { userRepository.delete(username); }
 * 
 * }
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findPaged(int pageIndex, int pageSize, int userId) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        return userRepository.findPaged(userId, pageable);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user); // save cũng update được nếu user đã có id
    }

    public void deleteUser(String username) {
        userRepository.findByUsername(username).ifPresent(userRepository::delete);
    }
}

