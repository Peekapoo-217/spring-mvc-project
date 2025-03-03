package com.hungdev;

import java.util.List;

import javax.sql.DataSource;

import com.hungdev.configs.DatabaseConfig;
import com.hungdev.entities.User;
import com.hungdev.repositories.UserRepositoryImp;

public class Main {

	public static void main(String[] args) {
		DatabaseConfig dbConfig = new DatabaseConfig();
		DataSource dataSource = dbConfig.dataSource();

		UserRepositoryImp userRepository = new UserRepositoryImp(dataSource);

		List<User> users = userRepository.findPaged(0, 10);
		for (User user : users) {
			System.out.println(user.getUsername());
		}
		dbConfig.closeConnection(null);

	}
}
