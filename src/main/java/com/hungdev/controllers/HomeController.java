/*
 * package com.hungdev.controllers;
 * 
 * import org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping;
 * 
 * @Controller public class HomeController {
 * 
 * @GetMapping("/") public String home() { return "home"; }
 * 
 * @GetMapping("/auth") public String login() { return "redirect:/auth"; } }
 */
package com.hungdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hungdev.entities.Post;
import com.hungdev.entities.User;
import com.hungdev.repositories.PostRepositoryImp;
import com.hungdev.services.AuthenticatedUserProvider;
import com.hungdev.services.JwtService;
import com.hungdev.services.PostService;
import com.hungdev.services.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
	@Autowired
	private PostService postService;

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticatedUserProvider authenticatedUserProvider;

	/*
	 * @GetMapping("/") public String home(HttpServletRequest request, Model model)
	 * { String token = request.getHeader("Authorization");
	 * 
	 * if (token != null && token.startsWith("Bearer ")) { token =
	 * token.substring(7); }
	 * 
	 * if (token != null && jwtService.validateToken(token)) {
	 * 
	 * String username = jwtService.extractUsernameFromToken(token);
	 * 
	 * int userId = Integer.parseInt(username);
	 * 
	 * List<Post> posts = postRepositoryImp.findPagedNewestByFollowings(userId, 0,
	 * 10); model.addAttribute("posts", posts); }
	 * 
	 * return "home"; }
	 */

	@GetMapping("/home")
	public String home(Model model) {
		User currentUser = authenticatedUserProvider.getAuthenticatedUser();
		int pageIndex = 0;
		int pageSize = 10;

		if (currentUser == null) {
			return "redirect:/auth/login";
		}

		List<Post> posts = postService.findPagedNewestByFollowings(currentUser.getId(), pageIndex, pageSize);

		List<User> users = userService.findPaged(pageIndex, pageSize);

		model.addAttribute("posts", posts);
		model.addAttribute("users", users);
		return "home";
	}

	 @GetMapping("/")
	    public String homeRedirect() {
	        return "redirect:/home";
	    }
	 
	@GetMapping("/auth")
	public String login() {
		return "redirect:/auth";
	}
}
