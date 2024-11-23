<html>
	<head>
		<title> SignIn Page</title>
	</head>
	<body>
		Welcome to the Sign In page!
		<div class="container">
			<h1>SignIn</h1>
		<form method="post" action="/signin">
			UserName: <input type="text" name="userName">
			Password: <input type="password" name="password">
			<input type="submit">
		</form>
		 <p style="color: red;">${errorMessage}</p>
		<a href="/login">Log In</a>
		</div>
	</body>
</html>