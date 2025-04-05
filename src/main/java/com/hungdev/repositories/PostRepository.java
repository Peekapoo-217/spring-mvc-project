package com.hungdev.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hungdev.entities.Post;
import com.hungdev.entities.UserRole;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    // Tìm tất cả bài viết của user theo thời gian mới nhất
    List<Post> findByUser_IdOrderByCreatedAtDesc(int userId);

    // Tìm bài viết của các người đang follow
    @Query("SELECT p FROM Post p WHERE p.user.id IN (" +
           "SELECT f.following.id FROM Follow f WHERE f.follower.id = :userId) " +
           "AND p.status <> 'DRAFTED' ORDER BY p.createdAt DESC")
    List<Post> findPagedNewestByFollowings(@Param("userId") int userId, Pageable pageable);

    // Tìm kiếm bài viết theo query và role
    @Query("SELECT p FROM Post p WHERE p.title LIKE %:query% " +
           "AND (:role <> 'USER' OR p.status <> 'DRAFTED')")
    List<Post> search(@Param("role") UserRole role, @Param("query") String query);
}
