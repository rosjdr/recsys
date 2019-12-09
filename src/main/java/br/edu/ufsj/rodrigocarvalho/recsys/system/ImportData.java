package br.edu.ufsj.rodrigocarvalho.recsys.system;

import java.io.IOException;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.dao.BusinessJpaDao;
import br.edu.ufsj.rodrigocarvalho.recsys.dao.UsersJpaDao;

public class ImportData {

	
	private static final int BATCH_SIZE = 2;
	private static final String USER_JSON_DATASET = "datasets/yelp_dataset/user.json";
	private static final String BUSINESS_JSON_DATASET = "datasets/yelp_dataset/business.json";

	public static void main(String[] args) {
		
		ProgressBar progressBar = new ProgressBar();
		
		Logger logger = Logger.getLogger(ImportData.class);					
		
		logger.info("Import program has been starten!");
		
		try {
			logger.info("Program is cleaning users table...");
			cleanUsersTable();
			logger.info("Cleaning users table has finished...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An unknown error has ocurred while cleaning users table...");
		}
		
		try {
			logger.info("Program is cleaning business table...");
			cleanBusinessTable();
			logger.info("Cleaning business table has finished...");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An unknown error has ocurred while cleaning business table...");
		}
		
		importUsers(progressBar);
		importBusiness(progressBar);
		createProgressBar(progressBar);
		
		logger.info("Import program has finished!");
		
	}

	private static void importBusiness(ProgressBar progressBar) {
		ImportBusinessRunnable importBusiness = new ImportBusinessRunnable(BUSINESS_JSON_DATASET, BATCH_SIZE, progressBar);
		Thread threadBusiness = new Thread(importBusiness,"importBusiness");
		threadBusiness.start();
	}

	private static void cleanBusinessTable() throws Exception {
		BusinessJpaDao dao = new BusinessJpaDao();
		dao.startTransaction();		
		dao.removeAll();		
		dao.commit();
	}

	private static void createProgressBar(ProgressBar progressBar) {
		ProgressBarRunnable progressBarRunnable = new ProgressBarRunnable(progressBar);
		Thread threadProgressBar = new Thread(progressBarRunnable,"progressBar");
		threadProgressBar.setDaemon(true);
		threadProgressBar.start();
	}

	private static void importUsers(ProgressBar progressBar) {
		ImportUsersRunnable importUsers = new ImportUsersRunnable(USER_JSON_DATASET, BATCH_SIZE, progressBar);
		Thread threadUsuarios = new Thread(importUsers, "importUsers");
		threadUsuarios.start();
	}

	private static void cleanUsersTable() throws IOException, Exception {
		UsersJpaDao userDao = new UsersJpaDao();
		userDao.startTransaction();		
		userDao.removeAll();		
		userDao.commit();
	}
}
