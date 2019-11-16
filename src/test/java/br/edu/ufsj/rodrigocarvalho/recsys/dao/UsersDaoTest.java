package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersDaoTest {
	
	private static final double JOAO_FRANCISCO_STARS = 4.5;
	private static final long JOAO_FRANCISCO_FANS = 100L;
	private static final String JOÃO_FRANCISCO_NAME = "João Francisco";
	private static final String JOAO_FRANCISCO_ID = "123";
	private UsersDao userDao;

	@Before
	public void preparaDaoParaTestes() {
		userDao = new UsersDao();
		userDao.startTransaction();
			
		Users user = userDao.find(JOAO_FRANCISCO_ID);
		if (user == null) {
			user = new Users(JOAO_FRANCISCO_ID, JOÃO_FRANCISCO_NAME, JOAO_FRANCISCO_FANS, JOAO_FRANCISCO_STARS);		
			userDao.save(user);			
		}
	}

	@After
	public void finalizaDaoAposTestes() {
		userDao.rollback();
		userDao.close();
	}
	
	@Test
	public void testUsersInsertIntegrationWithPostgres() {						
		Users user = getRosanyUser();		
		userDao.save(user);

		Users userSaved = userDao.find(user.getUserId());
		assertEquals(user, userSaved);
	}
	
	@Test(expected = EntityExistsException.class)	
	public void testTwoEqualUsersFailIntegrationWithPostgres() {						
		Users user1 = new Users(JOAO_FRANCISCO_ID, JOÃO_FRANCISCO_NAME, JOAO_FRANCISCO_FANS, JOAO_FRANCISCO_STARS);		
		userDao.save(user1);
	}	
	
	@Test
	public void testFindUserIntegrationWithPostgres() {
		Users userFind = userDao.find(JOAO_FRANCISCO_ID);
		assertEquals(JOÃO_FRANCISCO_NAME, userFind.getName());
		assertEquals(Long.valueOf(JOAO_FRANCISCO_FANS), userFind.getFans());
		assertEquals(JOAO_FRANCISCO_STARS, userFind.getAverageStars(), 0.0000001);
	}
		
	@Test
	public void testUsersAndFriendsIntegrationWithPostgres() {
		Users userFind = userDao.find(JOAO_FRANCISCO_ID);
		
		Users friend1 = getGuiguiUser();		
		userDao.save(friend1);
		Users friend2 = getAryaUser();		
		userDao.save(friend2);
		
		userFind.addFriend(friend1);
		userFind.addFriend(friend2);		
		userDao.save(userFind);
		
		Users userInserted = userDao.find(JOAO_FRANCISCO_ID);
		
		Collection<Users> friendsSaved = userInserted.getFriends();
		
		assertEquals(2, friendsSaved.size());
		assertTrue(friendsSaved.contains(friend1));
		assertTrue(friendsSaved.contains(friend2));
	}

	private Users getAryaUser() {
		return new Users("789", "Arya", 10L, 3.5);
	}

	private Users getGuiguiUser() {
		return new Users("456", "Guigui", 90L, 3.0);
	}
	
	private Users getRosanyUser() {
		return new Users("111", "Rosany", 100L, 4.5);
	}
	
	
}
