<!DOCTYPE html>
<html>
<head>
    <title>Register - Online Auction System</title>
    <link rel="stylesheet" type="text/css" href="css/register.css">
</head>
<body>
    
    <form action="register" method="post">
    	<h1>Create Account</h1>
        <label for="username">Username</label>
        <input type="text" name="username" placeholder="Enter your username" required>
        <label for="password">Password</label>
        <input type="password" name="password" placeholder="Enter your password" required>
        <label for="email">Email</label>
        <input type="email" name="email" placeholder="Enter your email" required>
        <input type="submit" value="Register">
        <p class="signup-link">
            Already have an account?
            <a href="login.jsp">Sign In</a>
        </p>
    </form>
</body>
</html>