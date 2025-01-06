<%@ page import="com.auction.dao.UserDAO, com.auction.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" type="text/css" href="css/profile.css">
</head>
<body>
    <h1>User Profile</h1>
    <%
        int userId = (Integer) request.getSession().getAttribute("userId");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserById(userId);
    %>
    <form action="updateProfile" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" value="<%= user.getUsername() %>" required><br>
        <label for="password">Password:</label>
        <input type="password" name="password" value="<%= user.getPassword() %>" required><br>
        <label for="email">Email:</label>
        <input type="email" name="email" value="<%= user.getEmail() %>" required><br>
        <input type="submit" value="Update Profile">
    </form>
</body>
</html>