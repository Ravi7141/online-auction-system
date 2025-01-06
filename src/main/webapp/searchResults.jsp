<%@ page import="com.auction.model.Auction" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" type="text/css" href="css/searchResult.css">
</head>
<body>
    <h1>Search Results</h1>
    <ul>
        <% List<Auction> auctions = (List<Auction>) request.getAttribute("auctions"); %>
        <% for (Auction auction : auctions) { %>
            <li>
                <a href="auctionDetails.jsp?auctionId=<%= auction.getId() %>"><%= auction.getTitle() %></a>
                <p>Category: <%= auction.getCategory() %></p>
                <p>Current Bid: <%= auction.getCurrentBid() %></p>
                <p>End Time: <%= auction.getEndTime() %></p>
            </li>
        <% } %>
    </ul>
</body>
</html>