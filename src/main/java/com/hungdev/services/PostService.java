package com.hungdev.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hungdev.entities.Post;
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
}
