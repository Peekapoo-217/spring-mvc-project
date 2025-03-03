package com.hungdev.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;

import com.hungdev.entities.User;
import com.hungdev.repositories.UserRepositoryImp;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {
	private static final String URL = "jdbc:mysql://localhost:3306/socialmedia?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	@Bean
	public DataSource dataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl(URL);
		config.setUsername(USER);
		config.setPassword(PASSWORD);
		config.setMaximumPoolSize(10);
		config.setMinimumIdle(2);
		config.setIdleTimeout(30000);
		config.setMaxLifetime(1800000);

		return new HikariDataSource(config);
	}

	public Connection getConnection() throws SQLException {
		return dataSource().getConnection();
	}

	public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}
	
	/*
	 * public static void main(String[] args) { DatabaseConfig dbConfig = new
	 * DatabaseConfig(); try (Connection conn = dbConfig.getConnection()) { if (conn
	 * != null) { System.out.println(" Kết nối thành công đến MySQL!"); } else {
	 * System.out.println("❌ Kết nối thất bại!"); } } catch (SQLException e) {
	 * e.printStackTrace(); } }
	 */
	
}
