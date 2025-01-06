<!DOCTYPE html>
<html>
<head>
    <title>Create Auction - Online Auction System</title>
    <link rel="stylesheet" type="text/css" href="css/createAuction.css">
</head>
<body>
   
    <form action="createAuction" method="post">
     <h1>Create New Auction</h1>
        <label for="title">Title:</label>
        <input type="text" name="title" required><br>
        
        <label for="description">Description:</label>
        <textarea name="description" required></textarea><br>
        
        <label for="startingBid">Starting Bid:</label>
        <input type="number" name="startingBid" step="0.01" required><br>
        
        <label for="endTime">End Time (YYYY-MM-DD HH:MM:SS):</label>
        <input type="text" name="endTime" required><br>
        
        <input type="submit" value="Create Auction">
    </form>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: red;"><%= request.getParameter("error") %></p>
    <% } %>
</body>
</html>