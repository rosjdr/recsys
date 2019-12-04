package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ufsj.rodrigocarvalho.recsys.helper.JpaHelper;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersJpaDao extends JpaDao<Users>{

	public UsersJpaDao() throws IOException {

	}

	public Users find(String userId) {
		return getEntityManager().find(Users.class, userId);
	}

	public List<Users> findAll(){
		TypedQuery<Users> query = getEntityManager().createQuery("from Users", Users.class);
		return query.getResultList();
	}

	public void removeAll() throws Exception {
		Query qFriends = getEntityManager().createNativeQuery("DELETE FROM recsys.friends");
	    Query qUsers   = getEntityManager().createQuery("DELETE FROM Users");
		
	    qFriends.executeUpdate();
	    qUsers.executeUpdate();
	}

}
