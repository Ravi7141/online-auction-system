package com.auction.controller;

import com.auction.dao.AuctionDAO;
import com.auction.model.Auction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/searchAuctions")
public class SearchAuctionServlet extends HttpServlet {
    private AuctionDAO auctionDAO = new AuctionDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        String category = request.getParameter("category");
        BigDecimal minPrice = request.getParameter("minPrice") != null ? new BigDecimal(request.getParameter("minPrice")) : null;
        BigDecimal maxPrice = request.getParameter("maxPrice") != null ? new BigDecimal(request.getParameter("maxPrice")) : null;
        Timestamp endTime = request.getParameter("endTime") != null ? Timestamp.valueOf(request.getParameter("endTime")) : null;

        List<Auction> auctions = auctionDAO.searchAuctions(keyword, category, minPrice, maxPrice, endTime);
        request.setAttribute("auctions", auctions);
        request.getRequestDispatcher("searchResults.jsp").forward(request, response);
    }
}