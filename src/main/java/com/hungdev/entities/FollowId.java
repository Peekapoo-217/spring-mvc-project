package com.hungdev.entities;

import java.io.Serializable;
import java.util.Objects;

public class FollowId implements Serializable {
    private User follower;
    private User following;

    public FollowId() {}

    public FollowId(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowId)) return false;
        FollowId that = (FollowId) o;
        return follower == that.follower && following == that.following;
    }

    @Override
    public int hashCode() {
        return Objects.hash(follower, following);
    }
}
