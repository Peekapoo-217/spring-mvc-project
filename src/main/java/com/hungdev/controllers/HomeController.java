
package com.hungdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hungdev.entities.Post;
import com.hungdev.entities.User;
import com.hungdev.services.FollowService;
import com.hungdev.services.PostService;
import com.hungdev.services.UserService;

@Controller
public class HomeController {
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@Autowired
	private FollowService followService;

	@GetMapping("/home")
	public String home(Model model) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return "redirect:/auth/login";
		}
		int userId = -1;

		Object principal = authentication.getPrincipal();

		if (principal instanceof UserDetails) {
			String username = ((UserDetails) principal).getUsername();
			User user = userService.findByUsername(username).orElse(null);
			if (user != null) {
				userId = user.getId();
			}
		}

		if (userId == -1) {
			return "redirect:/auth/login";
		}

		int pageIndex = 0;
		int pageSize = 10;

		List<Post> posts = postService.findPagedNewestByFollowings(userId, pageIndex, pageSize);
		List<User> users = userService.findPaged(pageIndex, pageSize, userId);
		List<Integer> followingIds = followService.getFollowingIds(userId);

		model.addAttribute("posts", posts);
		model.addAttribute("users", users);
		model.addAttribute("followingIds", followingIds);
		return "home";
	}

	@GetMapping("/")
	public String redirectToLogin() {
		return "redirect:/auth/login"; // Khi chạy ứng dụng, chuyển hướng đến trang login
	}

	@GetMapping("/auth")
	public String login() {
		return "redirect:/auth";
	}
}
