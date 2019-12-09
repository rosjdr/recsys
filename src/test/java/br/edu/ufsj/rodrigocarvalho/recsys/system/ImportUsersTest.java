package br.edu.ufsj.rodrigocarvalho.recsys.system;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.edu.ufsj.rodrigocarvalho.recsys.dao.UsersJpaDao;
import br.edu.ufsj.rodrigocarvalho.recsys.loader.UsersLoader;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImportUsersTest {

	@Before
	public void test01UsersCleanTable() throws Exception {
		UsersJpaDao userDao = new UsersJpaDao();
		userDao.startTransaction();		
		userDao.removeAll();		
		userDao.commit();
	}	
	
	@Test
	public void test01UsersImportBatchToBD() throws Exception {
		UsersLoader usersLoader = new UsersLoader("test_datasets/yelp_dataset/user.json");
		ProgressBar progressBar = new ProgressBar();
		int countUsersImported = usersLoader.importDataBatch(5,progressBar);		
		assertEquals(10, countUsersImported);
	}
	
	@Test
	public void test02UsersCleanTable() throws Exception {
		UsersJpaDao userDao = new UsersJpaDao();
		userDao.startTransaction();		
		userDao.removeAll();		
		userDao.commit();
		
		List<Users> users = userDao.findAll(); 
		
		assertEquals(0, users.size());
		
	}
}
