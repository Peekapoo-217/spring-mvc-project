
package com.hungdev;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.hungdev.configs.JpaConfig;
import com.hungdev.entities.Post;
import com.hungdev.entities.User;
import com.hungdev.repositories.PostRepository;
import com.hungdev.repositories.UserRepository;


public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);

		try {
			PostRepository postRepository = context.getBean(PostRepository.class);
			UserRepository userRepository = context.getBean(UserRepository.class);

			List<Post> posts = postRepository.findAll();
			for (Post post : posts) {
				System.out.println("Post: " + post.getTitle() + " by userId=" + post.getUser().getId());
			}

			List<User> users = userRepository.findAll();
			System.out.println("Danh sách người dùng:");
			for (User user : users) {
				System.out.printf("ID: %d | Username: %s | Role: %s\n",
						user.getId(), user.getUsername(), user.getRole());
			}
		} finally {
			context.close();
		}
	}
}