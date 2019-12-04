package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersJdbcDao extends JdbcDao{	
	
	public UsersJdbcDao() throws IOException, SQLException {

	}

	public int[] executeBatch(List<Users> batchToImport) throws SQLException {
		
		String queryStr = "INSERT INTO recsys.users(userid, averagestars, fans, name) VALUES (?, ?, ?, ?)";
		
		setPreparedStatement(getConnection().prepareStatement(queryStr));
		
		for (Users u : batchToImport) {
			getPreparedStatement().setString(1, u.getUserId());
			getPreparedStatement().setDouble(2, u.getAverageStars());
			getPreparedStatement().setLong(  3, u.getFans());
			getPreparedStatement().setString(4, u.getName());
			
			getPreparedStatement().addBatch();
		}				
		int[] inserted = getPreparedStatement().executeBatch();
				
		for (Users u : batchToImport) {
			insertUserFriendsBatch(u);
		}
		
		getPreparedStatement().close();
		return inserted;
	}

	private void insertUserFriendsBatch(Users user) throws SQLException {
		String queryStr = "INSERT INTO recsys.friends(userid, friendid) VALUES (?, ?)";
		PreparedStatement psAux = getConnection().prepareStatement(queryStr);
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
