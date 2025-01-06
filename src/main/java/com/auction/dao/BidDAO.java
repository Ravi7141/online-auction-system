package com.auction.dao;

import com.auction.model.Bid;
import com.auction.util.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BidDAO {
	public boolean placeBid(Bid bid) {
	    String sqlSelect = "SELECT current_bid FROM auctions WHERE id = ?";
	    String sqlUpdate = "UPDATE auctions SET current_bid = ? WHERE id = ?";
	    String sqlInsert = "INSERT INTO bids (auction_id, user_id, amount) VALUES (?, ?, ?)";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmtSelect = conn.prepareStatement(sqlSelect);
	         PreparedStatement stmtUpdate = conn.prepareStatement(sqlUpdate);
	         PreparedStatement stmtInsert = conn.prepareStatement(sqlInsert)) {
	        // Check current highest bid
	        stmtSelect.setInt(1, bid.getAuctionId());
	        ResultSet rs = stmtSelect.executeQuery();
	        if (rs.next()) {
	            BigDecimal currentBid = rs.getBigDecimal("current_bid");
	            if (bid.getAmount().compareTo(currentBid) <= 0) {
	                return false; // New bid must be higher than the current bid
	            }
	        }
	        // Place new bid and update current bid
	        conn.setAutoCommit(false); // Start transaction
	        stmtInsert.setInt(1, bid.getAuctionId());
	        stmtInsert.setInt(2, bid.getUserId());
	        stmtInsert.setBigDecimal(3, bid.getAmount());
	        stmtInsert.executeUpdate();

	        stmtUpdate.setBigDecimal(1, bid.getAmount());
	        stmtUpdate.setInt(2, bid.getAuctionId());
	        stmtUpdate.executeUpdate();
	        conn.commit(); // Commit transaction
	        return true;
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public List<Bid> getBidsByUserId(int userId) {
	    List<Bid> bids = new ArrayList<>();
	    String sql = "SELECT * FROM bids WHERE user_id = ?";
	    try (Connection conn = DBConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, userId);
	        try (ResultSet rs = stmt.executeQuery()) {
	            while (rs.next()) {
	                Bid bid = new Bid();
	                bid.setId(rs.getInt("id"));
	                bid.setAuctionId(rs.getInt("auction_id"));
	                bid.setUserId(rs.getInt("user_id"));
	                bid.setAmount(rs.getBigDecimal("amount"));
	                bid.setBidTime(rs.getTimestamp("bid_time"));
	                bids.add(bid);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return bids;
	}
    
    public List<Bid> getBidsByAuctionId(int auctionId) {
        List<Bid> bids = new ArrayList<>();
        String sql = "SELECT * FROM bids WHERE auction_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, auctionId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Bid bid = new Bid();
                    bid.setId(rs.getInt("id"));
                    bid.setAuctionId(rs.getInt("auction_id"));
                    bid.setUserId(rs.getInt("user_id"));
                    bid.setAmount(rs.getBigDecimal("amount"));
                    bid.setBidTime(rs.getTimestamp("bid_time"));
                    bids.add(bid);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bids;
    }
    
    public Bid getHighestBidByAuctionId(int auctionId) {
        String sql = "SELECT * FROM bids WHERE auction_id = ? ORDER BY amount DESC LIMIT 1";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, auctionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Bid bid = new Bid();
                    bid.setId(rs.getInt("id"));
                    bid.setAuctionId(rs.getInt("auction_id"));
                    bid.setUserId(rs.getInt("user_id"));
                    bid.setAmount(rs.getBigDecimal("amount"));
                    bid.setBidTime(rs.getTimestamp("bid_time"));
                    return bid;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}