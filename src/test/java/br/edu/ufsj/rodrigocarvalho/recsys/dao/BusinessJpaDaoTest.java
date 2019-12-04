package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Business;

public class BusinessJpaDaoTest {

	private BusinessJpaDao busDao;
	
	@Before
	public void preparaDaoParaTestes() throws IOException {
		busDao = new BusinessJpaDao();
		busDao.startTransaction();
			
		Business b1 = new Business("1", "B1", "SJDR", BigDecimal.valueOf(1.1234567), BigDecimal.valueOf(2.1234567), BigDecimal.valueOf(3.5), 5L, 1L, "C1, C2, C3");		
		busDao.save(b1);			
	}
	
	@After
	public void finalizaDaoAposTestes() {
		busDao.rollback();
		busDao.close();
	}
	
	@Test
	public void testBusinessInsert() {						
		Business b1 = new Business("2", "B2", "SJDR", BigDecimal.valueOf(1.1234567), BigDecimal.valueOf(2.1234567), BigDecimal.valueOf(3.5), 5L, 1L, "C1, C2, C3");		
		busDao.save(b1);

		Business busSaved = busDao.find(b1.getBusinessId());
		assertEquals(b1, busSaved);
	}
}
