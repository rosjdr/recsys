package br.edu.ufsj.rodrigocarvalho.recsys.system;

import java.util.HashMap;
import java.util.Map;

public class ProgressBar {

	private Map<String, String> map = new HashMap<String, String>();	
	
	public void add(String key, String value) {
		map.put(key, value);
	}
	
	public String get(String key) {
		return map.get(key);
	}

}

