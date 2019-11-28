package br.edu.ufsj.rodrigocarvalho.system;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.loader.UsersLoader;

public class ImportUsersRunnable implements Runnable {

	private String fileName;
	private int batchSize;
	private ProgressBar progressBar;
	private boolean running;

	public ImportUsersRunnable(String fileName, int batchSize, ProgressBar progressBar) {
		super();
		this.fileName = fileName;
		this.batchSize = batchSize;
		this.progressBar = progressBar;
		this.running = false;
	}

	public boolean isRunning() {
		return running;
	}
	
	@Override
	public void run() {
		Logger logger = Logger.getLogger(ImportUsers.class);

		UsersLoader usersLoader = new UsersLoader(fileName);
		usersLoader.setProgressBar(progressBar);
		
		this.running = true;
		
		try {			
			usersLoader.importDataBatch(batchSize);
			this.running = false;
		} catch (Exception e) {
			logger.error("Fail to import users from json to data base: ", e);
			e.printStackTrace();
		}
	}

}
