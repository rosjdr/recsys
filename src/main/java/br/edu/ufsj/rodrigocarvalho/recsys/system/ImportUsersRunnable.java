package br.edu.ufsj.rodrigocarvalho.recsys.system;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.loader.UsersLoader;

public class ImportUsersRunnable implements Runnable {

	private String fileName;
	private int batchSize;
	private ProgressBar progressBar;

	public ImportUsersRunnable(String fileName, int batchSize, ProgressBar progressBar) {
		super();
		this.fileName = fileName;
		this.batchSize = batchSize;
		this.progressBar = progressBar;
	}
	
	@Override
	public void run() {
		Logger logger = Logger.getLogger(ImportUsersRunnable.class);
		UsersLoader usersLoader = new UsersLoader(fileName);
		
		try {			
			usersLoader.importDataBatch(batchSize, progressBar);
		} catch (Exception e) {
			logger.error("Fail to import users from json to data base: ", e);
			e.printStackTrace();
		}
	}

}
