package br.edu.ufsj.rodrigocarvalho.recsys.system;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProgressBar {

	private Map<String, String> map;
	private static ProgressBar instance;
	private boolean running;
	
	public boolean isRunning() {
		return running;
	}
	
	private ProgressBar() {
		
	}
	
	public static ProgressBar getInstance() {
		if (instance == null) {
			instance = new ProgressBar();
			instance.map = new ConcurrentHashMap<String, String>();
			instance.running = false;
		}
		return instance;
	}
	
	public void add(String key, String value) {
		map.put(key, value);
	}
	
	public String get(String key) {
		return map.get(key);
	}

	public void setRunning(boolean running) {
		this.running = running;
		
	}
	
}

