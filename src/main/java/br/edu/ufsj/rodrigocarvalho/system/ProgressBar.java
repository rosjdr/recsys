package br.edu.ufsj.rodrigocarvalho.system;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProgressBar {

	private Map<String, String> map;
	
	public ProgressBar() {
		this.map = new ConcurrentHashMap<String, String>();
	}
	
	public void add(String key, String value) {
		map.put(key, value);
	}
	
	public String get(String key) {
		return map.get(key);
	}
	
}
