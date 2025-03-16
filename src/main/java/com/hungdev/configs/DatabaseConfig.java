
package com.hungdev.configs;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfig {
	private static final String URL = "jdbc:mysql://localhost:3306/socialmedia";
	private static final String USER = "root";
	private static final String PASSWORD = "";

	private HikariDataSource hikariDataSource;

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

}