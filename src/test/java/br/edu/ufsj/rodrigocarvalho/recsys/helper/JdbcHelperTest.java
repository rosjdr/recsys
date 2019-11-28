package br.edu.ufsj.rodrigocarvalho.recsys.helper;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

public class JdbcHelperTest {

	@Test
	public void testJdbcDatabaseConnection() throws SQLException, IOException {
		JdbcHelper jdbcHelper = new JdbcHelper();
		Connection conn  = jdbcHelper.getConnection();
		
		assertTrue(conn.isValid(1000));
		
		jdbcHelper.closeConnection();
						
	}
}
