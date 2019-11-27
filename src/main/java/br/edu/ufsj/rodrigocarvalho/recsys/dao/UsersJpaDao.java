package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.edu.ufsj.rodrigocarvalho.recsys.helper.JpaHelper;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersJpaDao implements AutoCloseable{

	private EntityManager entityManager;

	public UsersJpaDao() {
		JpaHelper jpaHelper = new JpaHelper();
		this.entityManager = jpaHelper.getEntityManager();		
	}

	public void save(Users user) {
		entityManager.persist(user);		
	}

	public Users find(String userId) {
		return entityManager.find(Users.class, userId);
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

	public void close() {
		entityManager.close();
		
	}

	public List<Users> getFriendsByUser(Users userFind) {		
		return null;
	}
	
	public void remove(Users user) {
		entityManager.remove(user);
	}
	
	public List<Users> findAll(){
		TypedQuery<Users> query = entityManager.createQuery("from Users", Users.class);
		return query.getResultList();
	}

	public void setFriendsByString(Users user) {
		String friendsStr = user.getFriendsStr();
		List<String> friendsIds = new ArrayList<String>(Arrays.asList(friendsStr.split(",", 0)));
		
		friendsIds.forEach(uId -> user.addFriend(this.find(uId.trim())));
	}

	public void flush() {
		entityManager.flush();
	}

	public void clear() {
		entityManager.clear();
	}

}