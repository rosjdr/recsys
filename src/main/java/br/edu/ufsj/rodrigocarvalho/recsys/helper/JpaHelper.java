package br.edu.ufsj.rodrigocarvalho.recsys.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaHelper {

	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("recsys_yelp");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
