package br.edu.ufsj.rodrigocarvalho.recsys.system;

import org.apache.log4j.Logger;

public class ProgressBarRunnable implements Runnable {

	private static final String USER_PROGRESS_KEY     = "user";
	private static final String BUSINESS_PROGRESS_KEY = "business";
	private static final int PROGRESS_TIME_INTERVAL = 15000;
	
	private ProgressBar progressBar;
	
	public ProgressBarRunnable(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	@Override
	public void run() {
		Logger logger = Logger.getLogger(ImportData.class);
		
		progressBar.add(USER_PROGRESS_KEY, "0%");
		progressBar.add(BUSINESS_PROGRESS_KEY, "0%");
		
		while (true) {			
			try {
				Thread.sleep(PROGRESS_TIME_INTERVAL);
				
				logger.info("Importing Users:    " + progressBar.get(USER_PROGRESS_KEY) + " done!");
				logger.info("Importing Business: " + progressBar.get(BUSINESS_PROGRESS_KEY) + " done!");
				
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
			
	}

}
