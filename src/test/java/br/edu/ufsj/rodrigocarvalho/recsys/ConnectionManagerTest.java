package br.edu.ufsj.rodrigocarvalho.recsys;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.edu.ufsj.rodrigocarvalho.recsys.db.ConnectionManager;

public class ConnectionManagerTest {

	@Test
	public void testaConexaoComPostgres() {
		
		ConnectionManager conn = new ConnectionManager();				
		assertTrue(conn.conectar());
	}
}
