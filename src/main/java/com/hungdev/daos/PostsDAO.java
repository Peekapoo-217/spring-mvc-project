/*
 * package com.hungdev.daos;
 * 
 * import com.hungdev.configs.DatabaseConfig; import java.sql.*; import
 * java.util.ArrayList; import java.util.List;
 * 
 * public class PostsDAO { public List<String> getAllPosts() { List<String>
 * posts = new ArrayList<>(); String sql = "SELECT content FROM posts"; try
 * (Connection conn = DatabaseConfig.getConnection(); Statement stmt =
 * conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) { while
 * (rs.next()) { posts.add(rs.getString("content")); } } catch (SQLException e)
 * { e.printStackTrace(); } return posts; } }
 */