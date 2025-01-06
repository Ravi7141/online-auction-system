<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.auction.model.Bid" %>
<%@ page import="com.auction.dao.BidDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Bids - Online Auction System</title>
    <link rel="stylesheet" type="text/css" href="css/userBids.css">
</head>
<body>
    <h1>My Bids</h1>

    <%
        int userId = (int) session.getAttribute("userId");
        BidDAO bidDAO = new BidDAO();
        List<Bid> userBids = bidDAO.getBidsByUserId(userId);
    %>

    <ul>
        <% for (Bid bid : userBids) { %>
            <li>
                <a href="auctionDetails.jsp?auctionId=<%= bid.getAuctionId() %>">Auction ID: <%= bid.getAuctionId() %></a>
                <p>Bid Amount: <%= bid.getAmount() %></p>
                <p>Bid Time: <%= bid.getBidTime() %></p>
            </li>
        <% } %>
    </ul>
</body>
</html>