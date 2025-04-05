/*
 * package com.hungdev.repositories;
 * 
 * import java.util.List;
 * 
 * public interface FollowRepository { void followUser(int followerId, int
 * followingId); void unfollowUser(int followerId, int followingId); boolean
 * isFollowing(int followerId, int followingId); List<Integer>
 * getFollowingIds(int userId); }
 */
package com.hungdev.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hungdev.entities.Follow;
import com.hungdev.entities.FollowId;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {

    boolean existsByFollowerIdAndFollowingId(int followerId, int followingId);

    void deleteByFollowerIdAndFollowingId(int followerId, int followingId);

    @Query("SELECT f.following.id FROM Follow f WHERE f.follower.id = :userId")
    List<Integer> getFollowingIds(int userId);
}
