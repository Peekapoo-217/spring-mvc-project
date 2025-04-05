/*
 * package com.hungdev.entities;
 * 
 * import java.time.LocalDateTime;
 * 
 * public class Relationship { private int followingUserId; private int
 * followedUserId; private LocalDateTime createdAt;
 * 
 * public Relationship() { }
 * 
 * public Relationship(int followingUserId, int followedUserId) { super();
 * this.followingUserId = followingUserId; this.followedUserId = followedUserId;
 * this.createdAt = LocalDateTime.now(); }
 * 
 * public int getFollowingUserId() { return followingUserId; }
 * 
 * public void setFollowingUserId(int followingUserId) { this.followingUserId =
 * followingUserId; }
 * 
 * public int getFollowedUserId() { return followedUserId; }
 * 
 * public void setFollowedUserId(int followedUserId) { this.followedUserId =
 * followedUserId; }
 * 
 * public LocalDateTime getCreatedAt() { return createdAt; }
 * 
 * public void setCreatedAt(LocalDateTime createdAt) { this.createdAt =
 * createdAt; } }
 */
package com.hungdev.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relationships")
public class Relationship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    @ManyToOne
    @JoinColumn(name = "followed_user_id")
    private User followedUser;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Getter/Setter...

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getFollowingUser() {
        return followingUser;
    }

    public void setFollowingUser(User followingUser) {
        this.followingUser = followingUser;
    }

    public User getFollowedUser() {
        return followedUser;
    }

    public void setFollowedUser(User followedUser) {
        this.followedUser = followedUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
