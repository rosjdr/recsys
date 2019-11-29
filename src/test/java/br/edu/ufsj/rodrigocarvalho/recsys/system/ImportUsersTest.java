package br.edu.ufsj.rodrigocarvalho.recsys.system;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.edu.ufsj.rodrigocarvalho.system.ImportData;
import br.edu.ufsj.rodrigocarvalho.system.ImportUsersRunnable;
import br.edu.ufsj.rodrigocarvalho.system.ProgressBar;

public class ImportUsersTest {

	@Test
	public void testProgressBarImportBatch() {
		Logger logger = Logger.getLogger(ImportData.class);
		logger.info("Import program has started!");
		
		ImportUsersRunnable importUsers = new ImportUsersRunnable("test_datasets/user.json", 2);

		
		logger.info("Users import progress: " + ProgressBar.getInstance().get("user"));
		
		Thread threadUsuarios = new Thread(importUsers);
		threadUsuarios.start();
				
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		while (ProgressBar.getInstance().isRunning()) {
			logger.info("Users import progress: " + ProgressBar.getInstance().get("user"));	
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		logger.info("Import program has finished!");
	}
}
