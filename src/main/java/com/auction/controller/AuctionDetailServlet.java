package com.auction.controller;

import com.auction.dao.AuctionDAO;
import com.auction.dao.BidDAO;
import com.auction.model.Auction;
import com.auction.model.Bid;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/auctionDetail")
public class AuctionDetailServlet extends HttpServlet {
    private AuctionDAO auctionDAO = new AuctionDAO();
    private BidDAO bidDAO = new BidDAO();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int auctionId = Integer.parseInt(request.getParameter("auctionId"));
        Auction auction = auctionDAO.getAuctionById(auctionId);
        List<Bid> bids = bidDAO.getBidsByAuctionId(auctionId);

        request.setAttribute("auction", auction);
        request.setAttribute("bids", bids);
        request.getRequestDispatcher("auctionDetails.jsp").forward(request, response);
    }
}