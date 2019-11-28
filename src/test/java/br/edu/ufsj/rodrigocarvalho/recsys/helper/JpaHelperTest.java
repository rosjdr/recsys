package br.edu.ufsj.rodrigocarvalho.recsys.helper;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import javax.persistence.EntityManager;

import org.junit.Test;

public class JpaHelperTest {

	@Test
	public void testJpaDataBaseConnection() throws IOException {
		JpaHelper jpaHelper = new JpaHelper();
		EntityManager entityManager = jpaHelper.getEntityManager();
		
		assertTrue(entityManager.isOpen());
		
		entityManager.close();
	}
	
//	<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost/recsys_yelp" /> <!-- BD Mane -->
//	<property name="javax.persistence.jdbc.user" value="postgres" /> <!-- DB User -->
//	<property name="javax.persistence.jdbc.password" value="postgres" /> <!-- DB Password -->


}
