

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Đăng nhập</title>
<script>
        async function login(event) {
            event.preventDefault();

            const username = document.getElementById("username").value;
            const password = document.getElementById("password").value;

            const response = await fetch("/simple-social-network/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                window.location.href = "/simple-social-network/home";
            } else {
                alert("Đăng nhập thất bại. Vui lòng kiểm tra lại tài khoản.");
            }
        }
    </script>
</head>
<body>
	<h2>Log in</h2>
	<form onsubmit="login(event)">
		<label for="username">Username:</label> <input type="text"
			id="username" required><br> <br> <label
			for="password">Password:</label> <input type="password" id="password"
			required><br> <br>

		<button type="submit">Login</button>
	</form>
</body>
</html>