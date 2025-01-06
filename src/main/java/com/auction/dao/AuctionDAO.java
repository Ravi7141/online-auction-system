package com.auction.dao;

import com.auction.model.Auction;
import com.auction.util.DBConnection;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AuctionDAO {
    // Method to create an auction
    public boolean createAuction(Auction auction) {
        String sql = "INSERT INTO auctions (title, description, starting_bid, current_bid, end_time, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, auction.getTitle());
            stmt.setString(2, auction.getDescription());
            stmt.setBigDecimal(3, auction.getStartingBid());
            stmt.setBigDecimal(4, auction.getCurrentBid());
            stmt.setTimestamp(5, auction.getEndTime());
            stmt.setInt(6, auction.getUserId());
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to get all auctions
    public List<Auction> getAllAuctions() {
        List<Auction> auctions = new ArrayList<>();
        String sql = "SELECT * FROM auctions";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Auction auction = new Auction();
                auction.setId(rs.getInt("id"));
                auction.setTitle(rs.getString("title"));
                auction.setDescription(rs.getString("description"));
                auction.setStartingBid(rs.getBigDecimal("starting_bid"));
                auction.setCurrentBid(rs.getBigDecimal("current_bid"));
                auction.setEndTime(rs.getTimestamp("end_time"));
                auction.setUserId(rs.getInt("user_id"));
                auctions.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }
    
    public Auction getAuctionById(int auctionId) {
        Auction auction = null;
        String sql = "SELECT * FROM auctions WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, auctionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    auction = new Auction();
                    auction.setId(rs.getInt("id"));
                    auction.setTitle(rs.getString("title"));
                    auction.setDescription(rs.getString("description"));
                    auction.setStartingBid(rs.getBigDecimal("starting_bid"));
                    auction.setCurrentBid(rs.getBigDecimal("current_bid"));
                    auction.setEndTime(rs.getTimestamp("end_time"));
                    auction.setUserId(rs.getInt("user_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auction;
    }
    
    public List<Auction> getAuctionsByUserId(int userId) {
        List<Auction> auctions = new ArrayList<>();
        String sql = "SELECT * FROM auctions WHERE user_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Auction auction = new Auction();
                    auction.setId(rs.getInt("id"));
                    auction.setTitle(rs.getString("title"));
                    auction.setDescription(rs.getString("description"));
                    auction.setStartingBid(rs.getBigDecimal("starting_bid"));
                    auction.setCurrentBid(rs.getBigDecimal("current_bid"));
                    auction.setEndTime(rs.getTimestamp("end_time"));
                    auction.setUserId(rs.getInt("user_id"));
                    auction.setCategory(rs.getString("category"));
                    auctions.add(auction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }
    
    public boolean updateAuction(Auction auction) {
        String sql = "UPDATE auctions SET current_bid = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, auction.getCurrentBid());
            stmt.setInt(2, auction.getId());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Auction> searchAuctions(String keyword, String category, BigDecimal minPrice, BigDecimal maxPrice, Timestamp endTime) {
        List<Auction> auctions = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM auctions WHERE 1=1");

        if (keyword != null && !keyword.isEmpty()) {
            sql.append(" AND (title LIKE ? OR description LIKE ?)");
        }
        if (category != null && !category.isEmpty()) {
            sql.append(" AND category = ?");
        }
        if (minPrice != null) {
            sql.append(" AND current_bid >= ?");
        }
        if (maxPrice != null) {
            sql.append(" AND current_bid <= ?");
        }
        if (endTime != null) {
            sql.append(" AND end_time <= ?");
        }

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;
            if (keyword != null && !keyword.isEmpty()) {
                stmt.setString(paramIndex++, "%" + keyword + "%");
                stmt.setString(paramIndex++, "%" + keyword + "%");
            }
            if (category != null && !category.isEmpty()) {
                stmt.setString(paramIndex++, category);
            }
            if (minPrice != null) {
                stmt.setBigDecimal(paramIndex++, minPrice);
            }
            if (maxPrice != null) {
                stmt.setBigDecimal(paramIndex++, maxPrice);
            }
            if (endTime != null) {
                stmt.setTimestamp(paramIndex++, endTime);
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Auction auction = new Auction();
                    auction.setId(rs.getInt("id"));
                    auction.setTitle(rs.getString("title"));
                    auction.setDescription(rs.getString("description"));
                    auction.setStartingBid(rs.getBigDecimal("starting_bid"));
                    auction.setCurrentBid(rs.getBigDecimal("current_bid"));
                    auction.setEndTime(rs.getTimestamp("end_time"));
                    auction.setUserId(rs.getInt("user_id"));
                    auction.setCategory(rs.getString("category"));
                    auctions.add(auction);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }
}