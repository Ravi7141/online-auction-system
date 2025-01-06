<!DOCTYPE html>
<html>
<head>
    <title>Search Auctions</title>
    <link rel="stylesheet" type="text/css" href="css/search.css">
</head>
<body>
    <h1>Search Auctions</h1>
    <form action="searchAuctions" method="get">
        <label for="keyword">Keyword:</label>
        <input type="text" name="keyword"><br>
        <label for="category">Category:</label>
        <input type="text" name="category"><br>
        <label for="minPrice">Min Price:</label>
        <input type="number" step="0.01" name="minPrice"><br>
        <label for="maxPrice">Max Price:</label>
        <input type="number" step="0.01" name="maxPrice"><br>
        <label for="endTime">End Time (YYYY-MM-DD HH:MM:SS):</label>
        <input type="text" name="endTime"><br>
        <input type="submit" value="Search">
    </form>
</body>
</html>