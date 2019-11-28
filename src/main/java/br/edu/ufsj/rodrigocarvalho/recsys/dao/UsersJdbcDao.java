package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.helper.JdbcHelper;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersJdbcDao implements AutoCloseable {

	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public UsersJdbcDao() throws IOException, SQLException {
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

	public int[] executeBatch(List<Users> batchToImport) throws SQLException {
		
		String queryStr = "INSERT INTO recsys.users(userid, averagestars, fans, name) VALUES (?, ?, ?, ?)";
		
		this.preparedStatement = connection.prepareStatement(queryStr);
		
		for (Users u : batchToImport) {
			preparedStatement.setString(1, u.getUserId());
			preparedStatement.setDouble(2, u.getAverageStars());
			preparedStatement.setLong(  3, u.getFans());
			preparedStatement.setString(4, u.getName());
			
			preparedStatement.addBatch();
		}				
		int[] inserted = preparedStatement.executeBatch();
				
		for (Users u : batchToImport) {
			insertUserFriendsBatch(u);
		}
		
		preparedStatement.close();
		return inserted;
	}

	private void insertUserFriendsBatch(Users user) throws SQLException {
		String queryStr = "INSERT INTO recsys.friends(userid, friendid) VALUES (?, ?)";
		PreparedStatement psAux = connection.prepareStatement(queryStr);
		boolean temAmigos = false;
		
		String friendsStr = user.getFriendsStr();
		List<String> friendsIds = new ArrayList<String>(Arrays.asList(friendsStr.split(",", 0)));
		
		for (String friend : friendsIds) {
			psAux.setString(1, user.getUserId());
			psAux.setString(2, friend.trim());
			psAux.addBatch();	
			temAmigos = true;
		}						
		if (temAmigos) {
			psAux.executeBatch();
		}
		
		psAux.close();
	}		

	
}
