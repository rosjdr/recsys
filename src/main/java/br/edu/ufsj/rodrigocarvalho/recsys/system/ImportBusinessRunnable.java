package br.edu.ufsj.rodrigocarvalho.recsys.system;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.loader.BusinessLoader;

public class ImportBusinessRunnable implements Runnable {

	private String fileName;
	private int batchSize;

	public ImportBusinessRunnable(String fileName, int batchSize) {
		super();
		this.fileName = fileName;
		this.batchSize = batchSize;
	}
	
	@Override
	public void run() {
		Logger logger = Logger.getLogger(ImportBusinessRunnable.class);
		BusinessLoader loader = new BusinessLoader(fileName);
		ProgressBar.getInstance().setRunning(true);
		
		try {			
			loader.importDataBatch(batchSize);
			ProgressBar.getInstance().setRunning(false);
		} catch (Exception e) {
			logger.error("Fail to import business from json to data base: ", e);
			e.printStackTrace();
		}

	}

}
