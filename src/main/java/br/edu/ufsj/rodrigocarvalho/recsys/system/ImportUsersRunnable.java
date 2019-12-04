package br.edu.ufsj.rodrigocarvalho.recsys.system;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.loader.UsersLoader;

public class ImportUsersRunnable implements Runnable {

	private String fileName;
	private int batchSize;

	public ImportUsersRunnable(String fileName, int batchSize) {
		super();
		this.fileName = fileName;
		this.batchSize = batchSize;
	}
	
	@Override
	public void run() {
		Logger logger = Logger.getLogger(ImportData.class);
		UsersLoader usersLoader = new UsersLoader(fileName);
		ProgressBar.getInstance().setRunning(true);
		
		try {			
			usersLoader.importDataBatch(batchSize);
			ProgressBar.getInstance().setRunning(false);
		} catch (Exception e) {
			logger.error("Fail to import users from json to data base: ", e);
			e.printStackTrace();
		}
	}

}
