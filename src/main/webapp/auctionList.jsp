<%@ page import="com.auction.dao.AuctionDAO, com.auction.model.Auction" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Auction Listings</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h1>Auction Listings</h1>
    <%
        AuctionDAO auctionDAO = new AuctionDAO();
        List<Auction> auctions = auctionDAO.getAllAuctions();
    %>
    <ul>
        <% for (Auction auction : auctions) { %>
            <li>
                <h2><%= auction.getTitle() %></h2>
                <p><%= auction.getDescription() %></p>
                <p>Starting Bid: <%= auction.getStartingBid() %></p>
                <p>Current Bid: <%= auction.getCurrentBid() %></p>
                <p>End Time: <%= auction.getEndTime() %></p>
            </li>
        <% } %>
    </ul>
</body>
</html>