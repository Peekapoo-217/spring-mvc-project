/*
 * package com.hungdev.daos;
 * 
 * import com.hungdev.configs.DatabaseConfig; import com.hungdev.entities.User;
 * import com.hungdev.entities.UserRole; import java.sql.*; import
 * java.util.ArrayList; import java.util.List; import java.util.Optional;
 * 
 * public class UserDAO { public List<User> findAll() { List<User> users = new
 * ArrayList<>(); String sql = "SELECT * FROM users"; try (Connection conn =
 * DatabaseConfig.getConnection(); Statement stmt = conn.createStatement();
 * ResultSet rs = stmt.executeQuery(sql)) { while (rs.next()) {
 * users.add(mapUser(rs)); } } catch (SQLException e) { e.printStackTrace(); }
 * return users; }
 * 
 * public Optional<User> findByUsername(String username) { String sql =
 * "SELECT * FROM users WHERE username = ?"; try (Connection conn =
 * DatabaseConfig.getConnection(); PreparedStatement pstmt =
 * conn.prepareStatement(sql)) { pstmt.setString(1, username); try (ResultSet rs
 * = pstmt.executeQuery()) { if (rs.next()) { return Optional.of(mapUser(rs)); }
 * } } catch (SQLException e) { e.printStackTrace(); } return Optional.empty();
 * }
 * 
 * private User mapUser(ResultSet rs) throws SQLException { return new
 * User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
 * UserRole.valueOf(rs.getString("role"))); } }
 */