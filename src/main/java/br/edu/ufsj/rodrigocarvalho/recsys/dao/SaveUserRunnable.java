package br.edu.ufsj.rodrigocarvalho.recsys.dao;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class SaveUserRunnable implements Runnable {
	
	private UsersJpaDao userDao;
	private Users u;

	public SaveUserRunnable(UsersJpaDao userDao, Users u) {
		this.userDao = userDao;
		this.u = u;
	}

	@Override
	public void run() {
		try {
			userDao.save(u);				
		} catch (Exception e) {
			Logger log = Logger.getLogger(this.getClass());
			log.error("Fail to import user " + u.getUserId() + ": " + e.getMessage());
		}
	}

}
