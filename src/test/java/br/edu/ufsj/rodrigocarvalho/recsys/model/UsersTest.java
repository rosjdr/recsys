package br.edu.ufsj.rodrigocarvalho.recsys.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UsersTest {

	@Test
	public void testUsersEquals() {
		
		Users u1 = new Users("Ha3iJu77CxlrFm-vQRs_8g", "u1", 10L);
		Users u2 = new Users("Ha3iJu77CxlrFm-vQRs_8g", "u2", 10L);
		
		assertEquals(u1, u2);		
	}
}
