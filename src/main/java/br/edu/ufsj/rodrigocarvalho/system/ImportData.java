package br.edu.ufsj.rodrigocarvalho.system;

public class ImportData {

	
	private static final int BATCH_SIZE = 100;
	private static final String USER_JSON_DATASET = "datasets/yelp_dataset/user.json";

	public static void main(String[] args) {
		ProgressBar.getInstance().setRunning(true);
		
		ProgressBarRunnable progressBarRunnable = new ProgressBarRunnable();
		Thread threadProgressBar = new Thread(progressBarRunnable);
		threadProgressBar.start();

		ImportUsersRunnable importUsers = new ImportUsersRunnable(USER_JSON_DATASET, BATCH_SIZE);
		Thread threadUsuarios = new Thread(importUsers);
		threadUsuarios.start();
	}
}
