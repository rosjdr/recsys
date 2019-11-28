package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ufsj.rodrigocarvalho.recsys.helper.JdbcHelper;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersJdbcDao implements AutoCloseable {

	private Connection connection;
	private PreparedStatement preparedStatement;
	
	public UsersJdbcDao() throws IOException {
		try {
			connection = new JdbcHelper().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void close() throws Exception {
		connection.close();
	}

	public int[] executeBatch(List<Users> batchToImport) throws SQLException {
		
		connection.setAutoCommit(true);
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
				
		preparedStatement.close();
		return inserted;
	}

	public void insertUserFriendsBatch(List<Users> batchToImport) throws SQLException {
		String queryStr = "INSERT INTO recsys.friends(userid, friendid) VALUES (?, ?)";
		PreparedStatement psAux = connection.prepareStatement(queryStr);
		boolean temAmigos = false;
				
		for (Users u : batchToImport) {
			String friendsStr = u.getFriendsStr();
			List<String> friendsIds = new ArrayList<String>(Arrays.asList(friendsStr.split(",", 0)));
			
			for (String friend : friendsIds) {
				psAux.setString(1, u.getUserId());
				psAux.setString(2, friend.trim());
				psAux.addBatch();	
				temAmigos = true;
			}						
			if (temAmigos) {
				int[] inserted = psAux.executeBatch();
			}
			psAux.clearBatch();
			psAux.clearParameters();
			temAmigos = false;			
		}	
		
		psAux.close();
	}		

	
}
