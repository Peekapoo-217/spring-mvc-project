<%-- 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hungdev.entities.Post"%>
<%@ page import="com.hungdev.entities.User"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<style>
.post-card {
	border-radius: 10px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
	transition: transform 0.2s;
}

.post-card:hover {
	transform: scale(1.02);
}

.avatar {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	object-fit: cover;
}
</style>
</head>
<body>
	<div class="container mt-4">
		<div class="row">
			<!-- Danh sách người dùng -->
			<div class="col-md-3">
				<h4 class="mb-3 text-primary">Người dùng</h4>
				<div class="list-group">
					<%
					List<User> userList = (List<User>) request.getAttribute("users");
					List<Integer> followingIds = (List<Integer>) request.getAttribute("followingIds");
					if (userList != null) {
						for (User user : userList) {
					%>
					<div
						class="list-group-item d-flex align-items-center justify-content-between">
						<form
							action="<%=(followingIds.contains(user.getId())) ? "/simple-social-network/follow/unfollow/"
		: "/simple-social-network/follow/"%>"
							method="post">

							<input name="userId" value="<%=(user.getId())%>" hidden /> <span><%=(user.getUsername())%></span>
							<button type="submit"
								class="btn btn-sm <%=(followingIds.contains(user.getId())) ? "btn-outline-danger" : "btn-outline-primary"%>">
								<i
									class="bi <%=(followingIds.contains(user.getId())) ? "bi-person-dash" : "bi-person-plus"%>"></i>
							</button>
						</form>
					</div>
					<%
					}
					}
					%>
				</div>
			</div>

			<div class="col-md-9">
				<h2 class="text-center mb-4">Bài đăng mới nhất</h2>
				<div class="row">
					<%
					List<Post> postList = (List<Post>) request.getAttribute("posts");
					if (postList != null) {
						for (Post post : postList) {
					%>
					<div class="col-md-12">
						<div class="card post-card mb-3">
							<div class="card-body">
								<!-- Thông tin người đăng -->
								<div class="d-flex align-items-center mb-2">
									<img src="<%=request.getContextPath()%>/resources/th.jpg"
										class="avatar me-2" alt="Avatar">
									<div>
										<h6 class="mb-0"><% %></h6>
										<small class="text-muted"><%= post.getCreatedAt() %></small>
									</div>
								</div>
								<!-- Nội dung bài đăng -->
								<h5 class="card-title"><%=post.getTitle()%></h5>
								<p class="card-text"><%=post.getBody()%></p>

								<!-- Nút tương tác -->
								<div class="d-flex justify-content-between">
									<button class="btn btn-outline-primary btn-sm">
										<i class="bi bi-hand-thumbs-up"></i> Thích
									</button>
									<button class="btn btn-outline-secondary btn-sm">
										<i class="bi bi-chat-dots"></i> Bình luận
									</button>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
 --%>
 <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.hungdev.entities.Post"%>
<%@ page import="com.hungdev.entities.User"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home Page</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
<style>
.post-card {
	border-radius: 10px;
	box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
	transition: transform 0.2s;
}

.post-card:hover {
	transform: scale(1.02);
}

.avatar {
	width: 40px;
	height: 40px;
	border-radius: 50%;
	object-fit: cover;
}
</style>
</head>
<body>
	<div class="container mt-4">
		<div class="row">
			<!-- Danh sách người dùng -->
			<div class="col-md-3">
				<h4 class="mb-3 text-primary">Người dùng</h4>
				<div class="list-group">
					<%
					List<User> userList = (List<User>) request.getAttribute("users");
					List<Integer> followingIds = (List<Integer>) request.getAttribute("followingIds");
					if (userList != null) {
						for (User user : userList) {
					%>
					<div
						class="list-group-item d-flex align-items-center justify-content-between">
						<form
							action="<%=(followingIds.contains(user.getId())) ? "/simple-social-network/follow/unfollow/"
				: "/simple-social-network/follow/"%>"
							method="post">

							<input name="userId" value="<%=(user.getId())%>" hidden /> <span><%=(user.getUsername())%></span>
							<button type="submit"
								class="btn btn-sm <%=(followingIds.contains(user.getId())) ? "btn-outline-danger" : "btn-outline-primary"%>">
								<i
									class="bi <%=(followingIds.contains(user.getId())) ? "bi-person-dash" : "bi-person-plus"%>"></i>
							</button>
						</form>
					</div>
					<%
					}
					}
					%>
				</div>
			</div>

			<!-- Cột bên phải: Danh sách bài đăng -->
			<div class="col-md-9">
				<h2 class="text-center mb-4">Bài đăng mới nhất</h2>
				<div class="row">
					<%
					List<Post> postList = (List<Post>) request.getAttribute("posts");
					if (postList != null) {
						for (Post post : postList) {
					%>
					<div class="col-md-12">
						<div class="card post-card mb-3">
							<div class="card-body">
								<!-- Thông tin người đăng -->
								<div class="d-flex align-items-center mb-2">
									<img src="<%= request.getContextPath()%>/resources/th.jpg"
										class="avatar me-2" alt="Avatar">
<%-- 									<div>
										<h6 class="mb-0"><%= post.getUser().getUsername() %></h6>
										<small class="text-muted"><%= post.getCreatedAt() %></small>
									</div> --%>
								</div>
								<!-- Nội dung bài đăng -->
								<h5 class="card-title"><%= post.getTitle() %></h5>
								<p class="card-text"><%= post.getBody() %></p>

								<!-- Nút tương tác -->
								<div class="d-flex justify-content-between">
									<button class="btn btn-outline-primary btn-sm">
										<i class="bi bi-hand-thumbs-up"></i> Thích
									</button>
									<button class="btn btn-outline-secondary btn-sm">
										<i class="bi bi-chat-dots"></i> Bình luận
									</button>
								</div>
							</div>
						</div>
					</div>
					<%
					}
					}
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
 