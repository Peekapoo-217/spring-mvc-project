/*
 * package com.hungdev.daos;
 * 
 * import com.hungdev.configs.DatabaseConfig; import java.sql.*;
 * 
 * public class FollowsDAO { public void followUser(int followerId, int
 * followeeId) { String sql =
 * "INSERT INTO follows (follower_id, followee_id) VALUES (?, ?)"; try
 * (Connection conn = DatabaseConfig.getConnection(); PreparedStatement pstmt =
 * conn.prepareStatement(sql)) { pstmt.setInt(1, followerId); pstmt.setInt(2,
 * followeeId); pstmt.executeUpdate(); } catch (SQLException e) {
 * e.printStackTrace(); } } }
 */