package com.hungdev.repositories;

import java.util.List;
import java.util.Optional;

import com.hungdev.entities.User;

public interface UserRepository {
	List<User> findPaged(int pageIndex, int pageSize);
	Optional<User> findByUsername(String username);
}
