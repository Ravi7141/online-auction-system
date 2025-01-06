package com.auction.controller;

import com.auction.dao.AuctionDAO;
import com.auction.dao.BidDAO;
import com.auction.dao.NotificationDAO;
import com.auction.model.Bid;
import com.auction.model.Notification;
import com.auction.model.Auction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;

@WebServlet("/placeBid")
public class PlaceBidServlet extends HttpServlet {
    private BidDAO bidDAO = new BidDAO();
    private NotificationDAO notificationDAO = new NotificationDAO();
    private AuctionDAO auctionDAO = new AuctionDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int auctionId = Integer.parseInt(request.getParameter("auction_id"));
        BigDecimal amount = new BigDecimal(request.getParameter("amount"));
        int userId = (Integer) request.getSession().getAttribute("userId");

        Auction auction = auctionDAO.getAuctionById(auctionId);
        if (auction == null || auction.getEndTime().before(new Timestamp(System.currentTimeMillis()))) {
            response.sendRedirect("auctionDetails.jsp?auctionId=" + auctionId + "&error=Auction has ended or does not exist");
            return;
        }

        if (amount.compareTo(auction.getCurrentBid()) <= 0) {
            response.sendRedirect("auctionDetails.jsp?auctionId=" + auctionId + "&error=Bid failed, must be higher than current bid");
            return;
        }

        Bid bid = new Bid();
        bid.setAuctionId(auctionId);
        bid.setUserId(userId);
        bid.setAmount(amount);

        if (bidDAO.placeBid(bid)) {
            // Update the auction's current bid
            auction.setCurrentBid(amount);
            auctionDAO.updateAuction(auction);

            // Notify the previous highest bidder
            Bid previousHighestBid = bidDAO.getHighestBidByAuctionId(auctionId);
            if (previousHighestBid != null && previousHighestBid.getUserId() != userId) {
                Notification notification = new Notification();
                notification.setUserId(previousHighestBid.getUserId());
                notification.setMessage("You have been outbid on auction ID: " + auctionId);
                notificationDAO.createNotification(notification);
            }

            response.sendRedirect("auctionDetails.jsp?auctionId=" + auctionId);
        } else {
            response.sendRedirect("auctionDetails.jsp?auctionId=" + auctionId + "&error=Bid failed");
        }
    }
}