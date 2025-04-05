/*
 * package com.hungdev.entities;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.Table;
 * 
 * @Entity
 * 
 * @Table(name = "follows") public class Follow {
 * 
 * private int followerId; private int followingId;
 * 
 * public int getFollowerId() { return followerId; }
 * 
 * public void setFollowerId(int followerId) { this.followerId = followerId; }
 * 
 * public int getFollowingId() { return followingId; }
 * 
 * public void setFollowingId(int followingId) { this.followingId = followingId;
 * }
 * 
 * 
 * }
 */
package com.hungdev.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;




@Entity
@Table(name = "follows")
@IdClass(FollowId.class)
public class Follow {

    @Id
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private User follower;

    @Id
    @ManyToOne
    @JoinColumn(name = "following_id")
    private User following;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getter/Setter...

    public User getFollower() {
        return follower;
    }

    public void setFollower(User follower) {
        this.follower = follower;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
