package br.edu.ufsj.rodrigocarvalho.system;

import org.apache.log4j.Logger;

public class ProgressBarRunnable implements Runnable {

	private static final String USER_PROGRESS_KEY = "user";
	private static final int PROGRESS_TIME_INTERVAL = 60000;
	
	@Override
	public void run() {
		Logger logger = Logger.getLogger(ImportData.class);
		logger.info("Import program has started!");
		ProgressBar.getInstance().add(USER_PROGRESS_KEY, "0");				
		
		while (ProgressBar.getInstance().isRunning()) {			
			try {
				Thread.sleep(PROGRESS_TIME_INTERVAL);
				logger.info("Users import progress: " + ProgressBar.getInstance().get(USER_PROGRESS_KEY));
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		logger.info("Import program has finished!");
	}

}
