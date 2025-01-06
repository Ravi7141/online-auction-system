<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.auction.dao.NotificationDAO, com.auction.model.Notification" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Notifications - Online Auction System</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Notifications</h1>
    <%
        int userId = (int) session.getAttribute("userId");
        NotificationDAO notificationDAO = new NotificationDAO();
        List<Notification> notifications = notificationDAO.getUserNotifications(userId);
    %>
    <ul>
        <% for (Notification notification : notifications) { %>
            <li><%= notification.getMessage() %></li>
        <% } %>
    </ul>
</body>
</html>