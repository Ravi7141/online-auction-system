<%@ page import="com.auction.dao.AuctionDAO, com.auction.dao.BidDAO, com.auction.model.Auction, com.auction.model.Bid" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Auction Detail</title>
    <link rel="stylesheet" type="text/css" href="css/auctiondetails.css">
</head>
<body>
    <h1>Auction Detail</h1>
    <%
        int auctionId = Integer.parseInt(request.getParameter("auctionId"));
        AuctionDAO auctionDAO = new AuctionDAO();
        Auction auction = auctionDAO.getAuctionById(auctionId);
        BidDAO bidDAO = new BidDAO();
        List<Bid> bids = bidDAO.getBidsByAuctionId(auctionId);
    %>
    <h2><%= auction.getTitle() %></h2>
    <p><%= auction.getDescription() %></p>
    <p>Starting Bid: <%= auction.getStartingBid() %></p>
    <p>Current Bid: <%= auction.getCurrentBid() %></p>
    <p>End Time: <%= auction.getEndTime() %></p>

    <h3>Place a Bid</h3>
    <form action="placeBid" method="post">
        <input type="hidden" name="auction_id" value="<%= auctionId %>">
        <label for="amount">Bid Amount:</label>
        <input type="number" step="0.01" name="amount" required><br>
        <input type="submit" value="Place Bid">
    </form>

    <h3>Bids</h3>
    <ul>
        <% for (Bid bid : bids) { %>
            <li>
                <p>Bid Amount: <%= bid.getAmount() %></p>
                <p>Bid Time: <%= bid.getBidTime() %></p>
            </li>
        <% } %>
    </ul>
</body>
</html>