package br.edu.ufsj.rodrigocarvalho.recsys.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.edu.ufsj.rodrigocarvalho.recsys.dao.UsersJdbcDao;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersLoader {

	private String fileName;

	public UsersLoader(String fileName) {
		this.fileName = fileName;

		startQueueConsumers();
	}

	private void startQueueConsumers() {

	}

	public List<Users> load() throws FileNotFoundException, IOException, ParseException {
		List<Users> users = new ArrayList<Users>();
		readJsonFile(users);
		return users.size() > 0 ? users : null;
	}

	private void readJsonFile(List<Users> users) throws FileNotFoundException, IOException, ParseException {
		try (FileReader fileReader = new FileReader(this.fileName)) {
			Scanner scanner = new Scanner(fileReader);
			while (scanner.hasNext()) {
				users.add(createUserFromJson(scanner.nextLine()));
			}
			scanner.close();
		}
	}

	private Users createUserFromJson(String linha) throws ParseException {
		JSONParser jsonParser = new JSONParser();
		Object objUser = jsonParser.parse(linha);
		JSONObject jsonObject = (JSONObject) objUser;
		return parseJSonObject(jsonObject);
	}

	private Users parseJSonObject(JSONObject jsonObject) {
		String id = (String) jsonObject.get("user_id");
		String name = (String) jsonObject.get("name");
		Long fans = (Long) jsonObject.get("fans");
		Double averageStars = (Double) jsonObject.get("average_stars");

		Users user = new Users(id, name, fans, averageStars);
		user.setFriendsStr((String) jsonObject.get("friends"));

		return user;
	}

	public int importDataBatch(int batchSize) throws Exception {
		int contImportedUsers = 0;
		List<Users> users = load();

		try (UsersJdbcDao userDao = new UsersJdbcDao()) {
			contImportedUsers = importUsersBatch(users, userDao, batchSize);
		}

		return contImportedUsers;
	}

	private int importUsersBatch(List<Users> users, UsersJdbcDao userDao, int batchSize) throws SQLException {
		int contImportedUsers = 0;
		int[] importeds;
		
		List<Users> batchToImport = new ArrayList<Users>();
		
		for (Users u : users) {
			batchToImport.add(u);
			if (batchToImport.size() == batchSize) {
				importeds = userDao.executeBatch(batchToImport);
				batchToImport.clear();
			}
			contImportedUsers++;				
		}
		
		if (batchToImport.size() > 0) {
			importeds = userDao.executeBatch(batchToImport);
		}
		
		userDao.insertUserFriendsBatch(users);
		
		return contImportedUsers;
	}	

}
