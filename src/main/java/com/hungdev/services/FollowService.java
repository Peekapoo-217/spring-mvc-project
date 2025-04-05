package com.hungdev.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hungdev.entities.Follow;
import com.hungdev.entities.FollowId;
import com.hungdev.entities.User;
import com.hungdev.repositories.FollowRepository;

/*
 * @Service public class FollowService {
 * 
 * @Autowired private FollowRepository followRepository;
 * 
 * public void followUser(int followerId, int followingId) { if
 * (!followRepository.isFollowing(followerId, followingId)) {
 * followRepository.followUser(followerId, followingId); } }
 * 
 * public void unfollowUser(int followerId, int followingId) { if
 * (followRepository.isFollowing(followerId, followingId)) {
 * followRepository.unfollowUser(followerId, followingId); } }
 * 
 * public boolean isFollowing(int followerId, int followingId) { return
 * followRepository.isFollowing(followerId, followingId); }
 * 
 * public List<Integer> getFollowingIds(int userId) { List<Integer> following =
 * followRepository.getFollowingIds(userId); following.removeIf(id -> id ==
 * userId); // Xóa chính mình khỏi danh sác return
 * followRepository.getFollowingIds(userId); } }
 */
@Service
public class FollowService {

    @Autowired
    private FollowRepository followRepository;

    public void followUser(int followerId, int followingId) {
        if (!isFollowing(followerId, followingId)) {
            Follow follow = new Follow();
            follow.setFollower(new User(followerId));
            follow.setFollowing(new User(followingId));
            followRepository.save(follow);
        }
    }

    public void unfollowUser(int followerId, int followingId) {
        if (isFollowing(followerId, followingId)) {
        	followRepository.deleteById(new FollowId(new User(followerId), new User(followingId)));
        }
    }

    public boolean isFollowing(int followerId, int followingId) {
        return followRepository.existsByFollowerIdAndFollowingId(followerId, followingId);
    }

    public List<Integer> getFollowingIds(int userId) {
        return followRepository.getFollowingIds(userId);
    }
}