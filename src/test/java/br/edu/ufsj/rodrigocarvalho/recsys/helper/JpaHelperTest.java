package br.edu.ufsj.rodrigocarvalho.recsys.helper;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;

import org.junit.Test;

public class JpaHelperTest {

	@Test
	public void testDataBaseConnection() {
		JpaHelper jpaHelper = new JpaHelper();
		EntityManager entityManager = jpaHelper.getEntityManager();
		
		assertTrue(entityManager.isOpen());
		
		entityManager.close();
	}
}
