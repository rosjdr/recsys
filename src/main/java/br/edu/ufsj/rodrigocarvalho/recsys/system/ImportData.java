package br.edu.ufsj.rodrigocarvalho.recsys.system;

import java.io.IOException;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.dao.UsersJpaDao;

public class ImportData {

	
	private static final int BATCH_SIZE = 100;
	private static final String USER_JSON_DATASET = "datasets/yelp_dataset/user.json";

	public static void main(String[] args) {
		
		Logger logger = Logger.getLogger(ImportData.class);					
		createProgressBar();
		
		try {
			logger.info("Import program is cleaning users table...");
			cleanUsersTable();
			logger.info("Cleaning users table has finished...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An unknown error has ocurred while importing users table...");
		}
		
		importUsers();
	}

	private static void createProgressBar() {
		ProgressBar.getInstance().setRunning(true);	
		ProgressBarRunnable progressBarRunnable = new ProgressBarRunnable();
		Thread threadProgressBar = new Thread(progressBarRunnable);
		threadProgressBar.start();
	}

	private static void importUsers() {
		ImportUsersRunnable importUsers = new ImportUsersRunnable(USER_JSON_DATASET, BATCH_SIZE);
		Thread threadUsuarios = new Thread(importUsers);
		threadUsuarios.start();
	}

	private static void cleanUsersTable() throws IOException, Exception {
		UsersJpaDao userDao = new UsersJpaDao();
		userDao.startTransaction();		
		userDao.removeAll();		
		userDao.commit();
	}
}
