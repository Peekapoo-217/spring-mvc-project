package com.hungdev.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hungdev.entities.Post;
import com.hungdev.entities.UserRole;
import com.hungdev.repositories.PostRepository;

@Service
public class PostService {
	private PostRepository postRepository;

	public PostService(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	public List<Post> findPagedNewestByFollowings(int userId, int pageIndex, int pageSize) {
		return postRepository.findPagedNewestByFollowings(userId, pageIndex, pageSize);
	}

	public List<Post> findAllByUserId(int userId) {
		return postRepository.findAllByUserId(userId);
	}
	
	/*
	 * public List<Post> searchPosts(String keyword) { Authentication auth =
	 * SecurityContextHolder.getContext().getAuthentication(); List<String> roles =
	 * auth.getAuthorities().stream() .map(GrantedAuthority::getAuthority)
	 * .collect(Collectors.toList());
	 * 
	 * List<PostStatus> allowedStatuses; if (roles.contains("ROLE_ADMIN")) {
	 * allowedStatuses = List.of(PostStatus.POSTED, PostStatus.DRAFTED); } else {
	 * allowedStatuses = List.of(PostStatus.POSTED); }
	 * 
	 * return postRepository.searchPostsByStatus(keyword, allowedStatuses); }
	 */
	
	public List<Post> searchByRole(UserRole role, String query) {
		return postRepository.search(role, query);
	}
}
