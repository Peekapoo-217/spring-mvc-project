package com.hungdev.services;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		Pageable pageable = PageRequest.of(pageIndex, pageSize); 
		return postRepository.findPagedNewestByFollowings(userId, pageable);
	}

	public List<Post> findAllByUserId(int userId) {
		return postRepository.findByUser_IdOrderByCreatedAtDesc(userId);
	}

	public List<Post> searchByRole(UserRole role, String query) {
		return postRepository.search(role, query);
	}
}
