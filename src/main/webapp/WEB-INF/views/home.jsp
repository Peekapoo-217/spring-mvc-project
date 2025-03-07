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
<!-- Thêm Bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
</head>
<body>

	<div class="container mt-4">
		<div class="row">
			<!-- Cột trái: Danh sách người dùng -->
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
						<!-- Nút theo dõi -->
						<form
							action="<%= (followingIds.contains(user.getId())) ? "/simple-social-network/follow/unfollow/" : "/simple-social-network/follow/" %>"
							method="post">
							<button type="submit"
								class="btn btn-sm <%= (followingIds.contains(user.getId())) ? "btn-outline-danger" : "btn-outline-primary" %>">
								<i
									class="bi <%= (followingIds.contains(user.getId())) ? "bi-person-dash" : "bi-person-plus" %>"></i>
							</button>
							<input name = "userId" value = "<%=(user.getId())%>"hidden/>

							<span><%= (user.getUsername()) %></span>
						</form>
					</div>



					<%
                        }
                    }
                %>
				</div>
			</div>

			<!-- Cột phải: Danh sách bài đăng -->
			<div class="col-md-9">
				<h2 class="text-center mb-4">📝 Bài đăng mới nhất</h2>
				<div class="row">
					<c:forEach var="post" items="${posts}">
						<div class="col-md-12">
							<div class="card mb-3">
								<div class="card-body">
									<h5 class="card-title">${post.title}</h5>
									<p class="card-text">${post.body}</p>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

	<!-- Debug: In danh sách user ra console -->
	<script>
<%
    if (userList != null) {
        for (User user : userList) {
%>
    console.log("User: { id: <%= user.getId() %>, username: '<%= user.getUsername() %>' }");
<%
        }
    }
%>
</script>

</body>
</html>
