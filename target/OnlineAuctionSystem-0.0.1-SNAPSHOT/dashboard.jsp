<%@ page import="com.auction.model.Auction, com.auction.model.Bid, com.auction.model.Notification" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <link rel="stylesheet" type="text/css" href="css/dashboard.css">
</head>
<body>
    <header>
        <h1>User Dashboard</h1>
    </header>
    <nav>
        <a href="index.jsp">Home</a>
        <a href="createAuction.jsp">Create New Auction</a>
        <a href="userAuctions.jsp">My Auctions</a>
        <a href="userBids.jsp">My Bids</a>
        <a href="dashboard">Dashboard</a>
        <a href="logout">Logout</a>
    </nav>
    <div class="container">
        <h2>Your Auctions</h2>
        <ul>
            <% 
                List<Auction> userAuctions = (List<Auction>) request.getAttribute("userAuctions");
                if (userAuctions != null && !userAuctions.isEmpty()) {
                    for (Auction auction : userAuctions) { 
            %>
                <li>
                    <a href="auctionDetails.jsp?auctionId=<%= auction.getId() %>"><%= auction.getTitle() %></a>
                    <p>Current Bid: <%= auction.getCurrentBid() %></p>
                    <p>End Time: <%= auction.getEndTime() %></p>
                </li>
            <% 
                    }
                } else { 
            %>
                <li>No auctions found.</li>
            <% 
                } 
            %>
        </ul>

        <h2>Your Bids</h2>
        <ul>
            <% 
                List<Bid> userBids = (List<Bid>) request.getAttribute("userBids");
                if (userBids != null && !userBids.isEmpty()) {
                    for (Bid bid : userBids) { 
            %>
                <li>
                    <a href="auctionDetails.jsp?auctionId=<%= bid.getAuctionId() %>">Auction: <%= bid.getAuctionId() %></a>
                    <p>Bid Amount: <%= bid.getAmount() %></p>
                    <p>Bid Time: <%= bid.getBidTime() %></p>
                </li>
            <% 
                    }
                } else { 
            %>
                <li>No bids found.</li>
            <% 
                } 
            %>
        </ul>

        <h2>Notifications</h2>
        <ul>
            <% 
                List<Notification> notifications = (List<Notification>) request.getAttribute("notifications");
                if (notifications != null && !notifications.isEmpty()) {
                    for (Notification notification : notifications) { 
            %>
                <li>
                    <p><%= notification.getMessage() %></p>
                    <p><%= notification.getTimestamp() %></p>
                </li>
            <% 
                    }
                } else { 
            %>
                <li>No notifications found.</li>
            <% 
                } 
            %>
        </ul>
    </div>
</body>
</html>