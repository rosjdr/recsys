package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Business;

public class BusinessJdbcDao extends JdbcDao{

	public BusinessJdbcDao() throws IOException, SQLException {

	}
	
	public int[] executeBatch(List<Business> batchToImport) throws SQLException {
		
		String queryStr = "INSERT INTO recsys.business(businessid, categories, city, isopen, latitude, longitude, name, reviewcount, stars) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		setPreparedStatement(getConnection().prepareStatement(queryStr));
		
		for (Business b : batchToImport) {
			getPreparedStatement().setString(    1, b.getBusinessId());
			getPreparedStatement().setString(    2, b.getCategories());
			getPreparedStatement().setString(    3, b.getCity());
			getPreparedStatement().setLong(      4, b.getIsOpen());
			getPreparedStatement().setBigDecimal(5, b.getLatitude());
			getPreparedStatement().setBigDecimal(6, b.getLongitude());
			getPreparedStatement().setString(    7, b.getName());
			getPreparedStatement().setLong(      8, b.getReviewCount());
			getPreparedStatement().setBigDecimal(9, b.getStars() );
			
			getPreparedStatement().addBatch();
		}				
		int[] inserted = getPreparedStatement().executeBatch();				
		
		getPreparedStatement().close();
		return inserted;
	}

}
