/*
 * package com.hungdev.configs;
 * 
 * import java.sql.Connection; import java.sql.DriverManager; import
 * java.sql.SQLException;
 * 
 * import javax.sql.DataSource;
 * 
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.jdbc.datasource.embedded.DataSourceFactory;
 * 
 * import com.hungdev.entities.User; import
 * com.hungdev.repositories.UserRepositoryImp; import
 * com.zaxxer.hikari.HikariConfig; import com.zaxxer.hikari.HikariDataSource;
 * import org.springframework.beans.factory.annotation.Autowired;
 * 
 * @Configuration public class DatabaseConfig { private static final String URL
 * = "jdbc:mysql://localhost:3306/socialmedia?useSSL=false&serverTimezone=UTC";
 * private static final String USER = "root"; private static final String
 * PASSWORD = "";
 * 
 * private HikariDataSource hikariDataSource;
 * 
 * @Bean public DataSource dataSource() { HikariConfig config = new
 * HikariConfig(); config.setJdbcUrl(URL); config.setUsername(USER);
 * config.setPassword(PASSWORD); config.setMaximumPoolSize(10);
 * config.setMinimumIdle(2); config.setIdleTimeout(30000);
 * config.setMaxLifetime(1800000);
 * 
 * hikariDataSource = new HikariDataSource(config); return hikariDataSource; }
 * 
 * public Connection getConnection() throws SQLException { return
 * hikariDataSource.getConnection(); }
 * 
 * 
 * public void closeDataSource() { if (hikariDataSource != null) {
 * hikariDataSource.close(); } } }
 */

package com.hungdev.configs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.hungdev.entities.User;
import com.hungdev.repositories.UserRepositoryImp;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {
	private static final String URL = "jdbc:mysql://localhost:3306/socialmedia?useSSL=false&serverTimezone=UTC";
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