<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.auction.model.Auction" %>
<%@ page import="com.auction.dao.AuctionDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Auctions - Online Auction System</title>
    <link rel="stylesheet" type="text/css" href="css/userAuctions.css">
</head>
<body>
    <h1>My Auctions</h1>

    <%
        int userId = (int) session.getAttribute("userId");
        AuctionDAO auctionDAO = new AuctionDAO();
        List<Auction> userAuctions = auctionDAO.getAuctionsByUserId(userId);
    %>

    <ul>
        <% for (Auction auction : userAuctions) { %>
            <li>
                <a href="auctionDetails.jsp?auctionId=<%= auction.getId() %>"><%= auction.getTitle() %></a>
                <p>Current Bid: <%= auction.getCurrentBid() %></p>
                <p>End Time: <%= auction.getEndTime() %></p>
            </li>
        <% } %>
    </ul>
</body>
</html>