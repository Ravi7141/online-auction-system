package com.auction.controller;

import com.auction.dao.NotificationDAO;
import com.auction.model.Notification;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {
    private NotificationDAO notificationDAO = new NotificationDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = (Integer) request.getSession().getAttribute("userId");
        List<Notification> notifications = notificationDAO.getUserNotifications(userId);
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("notifications.jsp").forward(request, response);
    }
}