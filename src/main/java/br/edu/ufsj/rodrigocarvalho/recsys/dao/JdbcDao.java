package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.edu.ufsj.rodrigocarvalho.recsys.helper.JdbcHelper;

public class JdbcDao implements AutoCloseable {

	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public Connection getConnection() {
		return connection;
	}
	
	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}
	
	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public JdbcDao() throws IOException, SQLException {
		connection = new JdbcHelper().getConnection();
	}

	@Override
	public void close() throws Exception {
		connection.close();
	}

	public void commit() throws SQLException {
		connection.commit();
	}

	public void rollBack() throws SQLException {
		connection.rollback();
	}

	public void setAutoCommit(boolean autoCommit) throws SQLException {
		connection.setAutoCommit(autoCommit);
	}

}