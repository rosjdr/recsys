package br.edu.ufsj.rodrigocarvalho.recsys.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaHelper {

	private EntityManagerFactory entityManagerFactory;

	public JpaHelper() throws IOException {
		Properties props = new Properties();
        InputStream fileStream = this.getClass().getResourceAsStream("/dataBase.properties");

        props.load(fileStream);
        fileStream.close();
        
        entityManagerFactory = Persistence.createEntityManagerFactory("recsys_yelp", props);
	}
	
	
	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}
}
