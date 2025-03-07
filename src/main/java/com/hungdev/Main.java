package com.hungdev;

import java.util.List;

import javax.sql.DataSource;

import com.hungdev.configs.DatabaseConfig;
import com.hungdev.entities.Post;
import com.hungdev.entities.User;
import com.hungdev.repositories.PostRepositoryImp;
import com.hungdev.repositories.UserRepositoryImp;
import com.hungdev.services.UserService;

public class Main {

	public static void main(String[] args) {
		DatabaseConfig dbConfig = new DatabaseConfig();
		DataSource dataSource = dbConfig.dataSource();

		UserRepositoryImp userRepository = new UserRepositoryImp(dataSource);
		UserService userService = new UserService(userRepository);

		
		PostRepositoryImp postsRep = new PostRepositoryImp(dataSource);
		List<User> users = userService.findPaged(0, 10);
		for (User user : users) {
			System.out.println(user.getUsername());
		}
		
		/*
		 * List<Post> posts = postsRep.findPagedNewestByFollowings(0, 0, 0);
		 * 
		 * for (Post post: posts) { System.out.println(post.getBody()); }
		 */
		
		dbConfig.closeConnection(null);

	}
}
