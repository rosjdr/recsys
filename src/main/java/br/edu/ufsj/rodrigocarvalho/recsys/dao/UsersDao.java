package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import javax.persistence.EntityManager;

import br.edu.ufsj.rodrigocarvalho.recsys.helper.JpaHelper;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersDao {

	private EntityManager entityManager;

	public UsersDao() {
		JpaHelper jpaHelper = new JpaHelper();
		this.entityManager = jpaHelper.getEntityManager();		
	}

	public void save(Users user) {
		entityManager.persist(user);		
	}

	public Users find(Users user) {
		return entityManager.find(Users.class, user.getUserId());
	}

	public void startTransaction() {
		entityManager.getTransaction().begin();
	}

	public void commit() {
		entityManager.getTransaction().commit();
	}

	public void rollback() {
		entityManager.getTransaction().rollback();
	}

}
