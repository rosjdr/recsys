package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Business;

public class BusinessJpaDao extends JpaDao<Business> {

	public BusinessJpaDao() throws IOException {

	}
	
	public Business find(String businessId) {
		return getEntityManager().find(Business.class, businessId);
	}
	
	public List<Business> findAll(){
		TypedQuery<Business> query = getEntityManager().createQuery("from Business", Business.class);
		return query.getResultList();
	}

	public void removeAll() throws Exception {
	    Query qBusiness   = getEntityManager().createQuery("DELETE FROM Business");
	    qBusiness.executeUpdate();
	}
	

}
