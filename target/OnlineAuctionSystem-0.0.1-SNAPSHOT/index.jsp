<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.auction.model.Auction" %>
<%@ page import="com.auction.dao.AuctionDAO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home - Online Auction System</title>
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    <nav>
        <header>
            <h1>Online Auction System</h1>
        </header>
        <ul>
            <li><a href="createAuction.jsp">Create Auction</a></li>
            <li><a href="userAuctions.jsp">Auctions</a></li>
            <li><a href="userBids.jsp">Bids</a></li>
            <li>
                <div class="paste-button">
                    <button class="button">Menu &nbsp; ▼</button>
                    <div class="dropdown-content">
                        <a id="top" href="profile.jsp">Profile</a>
                        <a id="middle" href="dashboard">Dashboard</a>
                        <a id="bottom" href="logout">Logout</a>
                    </div>
                </div>
            </li>
        </ul>
    </nav>
    <div class="container">
        <%
            AuctionDAO auctionDAO = new AuctionDAO();
            List<Auction> auctions = auctionDAO.getAllAuctions();
        %>

        <h2>Available Auctions</h2>
        <div class="cards-container">
            <% for (Auction auction : auctions) { %>
                <div class="card">
                    <a id="AuctionName" class="small-desc" href="auctionDetails.jsp?auctionId=<%= auction.getId() %>"><%= auction.getTitle() %></a>
                    <p class="small-desc">
                        Current Bid: <%= auction.getCurrentBid() %><br>
                        End Time: <%= auction.getEndTime() %>
                    </p>
                    <div class="go-corner">
                        <div class="go-arrow">→</div>
                    </div>
                    <a href="auctionDetails.jsp?auctionId=<%= auction.getId() %>"></a>
                </div>
            <% } %>
        </div>

        <div class="search-button-container">
        <a href="search.jsp" class="search-button">Search Auctions</a>
    </div>
    </div>
</body>
</html>