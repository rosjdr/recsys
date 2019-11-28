package br.edu.ufsj.rodrigocarvalho.system;

import org.apache.log4j.Logger;

public class ImportUsers {

	private static final int BATCH_SIZE = 100;
	private static final String USER_JSON_DATASET = "datasets/yelp_dataset/user.json";

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(ImportUsers.class);
		logger.info("Import program has started!");
		
		ProgressBar progressBar = new ProgressBar();
		
		ImportUsersRunnable importUsers = new ImportUsersRunnable(USER_JSON_DATASET, BATCH_SIZE, progressBar);
		
		Thread threadUsuarios = new Thread(importUsers);
		threadUsuarios.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (importUsers.isRunning()) {
			logger.info("Users import progress: " + progressBar.get("user"));
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("Import program has finished!");
		
		
	}
}
