package br.edu.ufsj.rodrigocarvalho.recsys.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcHelper {

	private Connection connection;
	
	public JdbcHelper() throws SQLException {
		connection = DriverManager.getConnection("jdbc:postgresql://localhost/recsys_yelp", "postgres", "postgres");
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() throws SQLException {
		connection.close();		
	}
}


