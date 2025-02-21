package com.dduongdev.repositories;

import java.util.List;
import java.util.Optional;

import com.dduongdev.entities.Post;

public interface PostRepository {
	List<Post> findPagedNewestByFollowings(int userId, int pageIndex, int pageSize);
	List<Post> findAllByUserId(int userId);
	Optional<Post> findById(int id);
	void add(Post post);
	void update(Post post);
	void delete(int id);
}
