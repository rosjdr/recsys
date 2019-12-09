package br.edu.ufsj.rodrigocarvalho.recsys.system;

import org.apache.log4j.Logger;

import br.edu.ufsj.rodrigocarvalho.recsys.loader.BusinessLoader;

public class ImportBusinessRunnable implements Runnable {

	private String fileName;
	private int batchSize;
	private ProgressBar progressBar;

	public ImportBusinessRunnable(String fileName, int batchSize, ProgressBar progressBar) {
		super();
		this.fileName = fileName;
		this.batchSize = batchSize;
		this.progressBar = progressBar;
	}
	
	@Override
	public void run() {
		Logger logger = Logger.getLogger(ImportBusinessRunnable.class);
		BusinessLoader loader = new BusinessLoader(fileName);
		
		try {			
			loader.importDataBatch(batchSize, progressBar);
		} catch (Exception e) {
			logger.error("Fail to import business from json to data base: ", e);
			e.printStackTrace();
		}

	}

}
