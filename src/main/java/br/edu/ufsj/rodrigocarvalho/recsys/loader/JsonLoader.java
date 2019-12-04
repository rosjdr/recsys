package br.edu.ufsj.rodrigocarvalho.recsys.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class JsonLoader<T> {

	private String fileName;
	
	public String getFileName() {
		return fileName;
	}

	public JsonLoader(String fileName) {
		this.fileName = fileName;
	}
	
	protected abstract T parseJSonObject(JSONObject jsonObject);
	
	public List<T> load() throws FileNotFoundException, IOException, ParseException {
		List<T> list = new ArrayList<T>();
		readJsonFile(list);
		return list.size() > 0 ? list : null;
	}
	
	private void readJsonFile(List<T> list) throws FileNotFoundException, IOException, ParseException {
		try (FileReader fileReader = new FileReader(getFileName())) {
			Scanner scanner = new Scanner(fileReader);
			while (scanner.hasNext()) {
				list.add(createObjectFromJson(scanner.nextLine()));
			}
			scanner.close();
		}
	}
	
	private T createObjectFromJson(String linha) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		Object obj = jsonParser.parse(linha);
		JSONObject jsonObject = (JSONObject) obj;
		return parseJSonObject(jsonObject);
	}
	
}