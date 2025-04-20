package com.hungdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hungdev.entities.Post;
import com.hungdev.entities.PostStatus;
import com.hungdev.entities.User;
import com.hungdev.services.PostService;

@Controller
@RequestMapping("/post")
public class PostController {
	  @Autowired
	    private PostService postService;

	    @PostMapping("/create")
	    public String createPost(@RequestParam("title") String title,
	                             @RequestParam("content") String content,
	                             @AuthenticationPrincipal UserDetails userDetails) {

	        if (userDetails == null) {
	            return "redirect:/auth/login";
	        }

	        User currentUser = (User) userDetails;
	        Post newPost = new Post();
	        newPost.setTitle(title);
	        newPost.setBody(content);
	        newPost.setUserId(currentUser.getId());
	        newPost.setStatus(PostStatus.POSTED);

	        postService.add(newPost);

	        return "redirect:/home";
	    }
}
