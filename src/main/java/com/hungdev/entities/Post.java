package com.hungdev.entities;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;



@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false, length = 255)
	private String title;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String body;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "user_id") private int userId;
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PostStatus status = PostStatus.POSTED;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Transient
	private String authorName;

	public Post() {
		super();
	}

	/*
	 * public Post(int id, String title, String body, int userId, PostStatus status,
	 * LocalDateTime createdAt) { this.id = id; this.title = title; this.body =
	 * body; this.userId = userId; this.status = status; this.createdAt = createdAt;
	 * }
	 */
	public Post(int id, String title, String body, User user, PostStatus status, LocalDateTime createdAt) {
		this.id = id;
		this.title = title;
		this.body = body;
		this.user = user;
		this.status = status;
		this.createdAt = createdAt;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	/*
	 * public int getUserId() { return userId; }
	 * 
	 * public void setUserId(int userId) { this.userId = userId; }
	 */
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PostStatus getStatus() {
		return status;
	}

	public void setStatus(PostStatus status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
}
