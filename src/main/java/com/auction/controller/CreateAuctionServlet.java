package com.auction.controller;

import com.auction.dao.AuctionDAO;
import com.auction.model.Auction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

@WebServlet("/createAuction")
public class CreateAuctionServlet extends HttpServlet {
    private AuctionDAO auctionDAO = new AuctionDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp?error=You must be logged in to create an auction");
            return;
        }

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String startingBidStr = request.getParameter("startingBid");
        String endTimeStr = request.getParameter("endTime");

        // Validate input parameters
        if (title == null || title.isEmpty() || description == null || description.isEmpty() ||
            startingBidStr == null || startingBidStr.isEmpty() || endTimeStr == null || endTimeStr.isEmpty()) {
            response.sendRedirect("createAuction.jsp?error=All fields are required");
            return;
        }

        // Convert startingBidStr to BigDecimal
        BigDecimal startingBid;
        try {
            startingBid = new BigDecimal(startingBidStr);
        } catch (NumberFormatException e) {
            response.sendRedirect("createAuction.jsp?error=Invalid starting bid");
            return;
        }

        // Convert endTimeStr to Timestamp
        Timestamp endTime;
        try {
            endTime = Timestamp.valueOf(endTimeStr);
        } catch (IllegalArgumentException e) {
            response.sendRedirect("createAuction.jsp?error=Invalid end time format");
            return;
        }

        Auction auction = new Auction();
        auction.setUserId(userId);
        auction.setTitle(title);
        auction.setDescription(description);
        auction.setStartingBid(startingBid);
        auction.setCurrentBid(startingBid);
        auction.setEndTime(endTime);

        auctionDAO.createAuction(auction);

        response.sendRedirect("index.jsp");
    }
}