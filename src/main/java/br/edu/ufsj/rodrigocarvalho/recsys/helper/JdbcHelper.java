package br.edu.ufsj.rodrigocarvalho.recsys.helper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class JdbcHelper {

	private Connection connection;
	
	public JdbcHelper() throws SQLException, IOException {
		
		Properties props = new Properties();
        InputStream fileStream = this.getClass().getResourceAsStream("/dataBase.properties");

        props.load(fileStream);
        fileStream.close();
		
        String url      =  props.getProperty("javax.persistence.jdbc.url");
		String user     = props.getProperty("javax.persistence.jdbc.user");
		String password = props.getProperty("javax.persistence.jdbc.password");
		
		connection = DriverManager.getConnection(url, user, password);
	}
	
	public Connection getConnection() {
		return connection;
	}

	public void closeConnection() throws SQLException {
		connection.close();		
	}
}


