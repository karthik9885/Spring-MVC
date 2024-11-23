<html>
	<head>
		<title> Login Page</title>
	</head>
	<body>
		Welcome to the login page!
		<div class="container">
			<h1>Login</h1>
		<form method="post" action="/login">
			Name: <input type="text" name="userName">
			Password: <input type="password" name="password">
			<input type="submit">
		</form>
		 <p style="color: red;">${errorMessage}</p>
		<a href="/signin">Sign In</a>
		</div>
	</body>
</html>