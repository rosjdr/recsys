package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersDaoTest {

	@Test	
	public void testaSeUsuarioEGravadoNoBancoPostgres() {						
		Users user = new Users("123", "João Francisco", 100L, 4.5);		
		
		UsersDao userDao = new UsersDao();
		userDao.startTransaction();
		userDao.save(user);
		
		Users userSaved = userDao.find(user);
		assertEquals(user, userSaved);
		userDao.rollback();
		
	}
}
