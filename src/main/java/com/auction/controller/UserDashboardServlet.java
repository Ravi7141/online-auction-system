package com.auction.controller;

import com.auction.dao.AuctionDAO;
import com.auction.dao.BidDAO;
import com.auction.dao.NotificationDAO;
import com.auction.model.Auction;
import com.auction.model.Bid;
import com.auction.model.Notification;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/dashboard")
public class UserDashboardServlet extends HttpServlet {
    private AuctionDAO auctionDAO = new AuctionDAO();
    private BidDAO bidDAO = new BidDAO();
    private NotificationDAO notificationDAO = new NotificationDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp?error=You must be logged in to view the dashboard");
            return;
        }

        List<Auction> userAuctions = auctionDAO.getAuctionsByUserId(userId);
        List<Bid> userBids = bidDAO.getBidsByUserId(userId);
        List<Notification> notifications = notificationDAO.getUserNotifications(userId);

        request.setAttribute("userAuctions", userAuctions);
        request.setAttribute("userBids", userBids);
        request.setAttribute("notifications", notifications);
        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }
}