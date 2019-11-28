package br.edu.ufsj.rodrigocarvalho.recsys.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UsersTest {

	@Test
	public void testUsersEquals() {
		
		Users u1 = new Users("Ha3iJu77CxlrFm-vQRs_8g", "u1", 10L, 0.0);
		Users u2 = new Users("Ha3iJu77CxlrFm-vQRs_8g", "u2", 10L, 0.0);
		
		assertEquals(u1, u2);
	}
	
	@Test
	public void testUserSetsToEntity() {
		Users user = new Users();
		user.setAverageStars(0.0);
		user.setFans(10L);
		user.setName("uTest");
		user.setUserId("Ha3iJu77CxlrFm-vQRs_8g");
		
		Users u1 = new Users("Ha3iJu77CxlrFm-vQRs_8g", "u1", 10L, 0.0);
		
		assertEquals(u1, user);
	}
	
	@Test
	public void testUserToString() {
		
		String expectedString = "[" + "Ha3iJu77CxlrFm-vQRs_8g" + ", " + "u1" + ", " + 10L + ", " + 0.0 + "]";
		
		Users u1 = new Users("Ha3iJu77CxlrFm-vQRs_8g", "u1", 10L, 0.0);
		
		String userToString = u1.toString();
		
		assertEquals(expectedString, userToString);
	}
}
