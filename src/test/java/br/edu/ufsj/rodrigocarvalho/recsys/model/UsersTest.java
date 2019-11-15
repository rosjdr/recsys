package br.edu.ufsj.rodrigocarvalho.recsys.model;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;

public class UsersTest {

	@Test
	public void testUsersEquals() {
		
		Users u1 = new Users("Ha3iJu77CxlrFm-vQRs_8g", "u1", 10L, 0.0);
		Users u2 = new Users("Ha3iJu77CxlrFm-vQRs_8g", "u2", 10L, 0.0);
		
		assertEquals(u1, u2);
		
		Logger log = Logger.getLogger(UsersTest.class.getName());
		log.info("TEste");
	}
}
