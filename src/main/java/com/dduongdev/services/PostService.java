package com.dduongdev.services;

import java.util.List;

import com.dduongdev.entities.Post;
import com.dduongdev.repositories.PostRepository;

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
}
