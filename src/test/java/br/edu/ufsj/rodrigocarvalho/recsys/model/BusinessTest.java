package br.edu.ufsj.rodrigocarvalho.recsys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class BusinessTest {
	
	private Business defaultBusiness;
	
	@Before
	public void createNewBusiness() {
		defaultBusiness = new Business("1", "My Business Test", "São João del Rei", BigDecimal.valueOf(36.2), BigDecimal.valueOf(45.432123), BigDecimal.valueOf(3.5), 4L, 1L, "Category1, Category2");
	}
	
	@Test
	public void testBusinessSetsToEntity() {
		
		Business business = new Business();
		
		business.setBusinessId("1");
		business.setCategories("Category1, Category2");
		business.setCity("São João del Rei");
		business.setIsOpen(1L);
		business.setLatitude(BigDecimal.valueOf(36.2));
		business.setLongitude(BigDecimal.valueOf(45.432123));
		business.setName("My Business Test");
		business.setReviewCount(4L);
		business.setStars(BigDecimal.valueOf(3.5));
		
		assertEquals(defaultBusiness, business);
	}
	
	@Test
	public void testBusinessEquals() {
		Business b1 = new Business();
		b1.setBusinessId("1");
		b1.setName("B1");
		
		Business b2 = new Business();
		b2.setBusinessId("1");
		b2.setName("B2");
		
		assertTrue(b1.equals(b2));
	}

}
