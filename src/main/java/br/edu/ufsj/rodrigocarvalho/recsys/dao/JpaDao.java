package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.io.IOException;

import javax.persistence.EntityManager;

import br.edu.ufsj.rodrigocarvalho.recsys.helper.JpaHelper;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class JpaDao<T> implements AutoCloseable {

	private EntityManager entityManager;
	
	public JpaDao() throws IOException {
		JpaHelper jpaHelper = new JpaHelper();
		this.entityManager = jpaHelper.getEntityManager();		
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	public void save(T object) {
		entityManager.persist(object);		
	}
	
	public void startTransaction() {
		entityManager.getTransaction().begin();
	}
	
	@Override
	public void close() {
		entityManager.close();
	}
	
	public void commit() {
		entityManager.getTransaction().commit();
	}

	public void rollback() {
		entityManager.getTransaction().rollback();
	}

	public void remove(T object) {
		entityManager.remove(object);
	}


}