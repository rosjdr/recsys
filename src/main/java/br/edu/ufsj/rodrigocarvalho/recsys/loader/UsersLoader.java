package br.edu.ufsj.rodrigocarvalho.recsys.loader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import br.edu.ufsj.rodrigocarvalho.recsys.dao.SaveUserFriendsRunnable;
import br.edu.ufsj.rodrigocarvalho.recsys.dao.SaveUserRunnable;
import br.edu.ufsj.rodrigocarvalho.recsys.dao.UsersDao;
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

	private void setFriendsByString(Users user, List<Users> users) {
		String friendsStr = user.getFriendsStr();
		List<String> friendsIds = new ArrayList<String>(Arrays.asList(friendsStr.split(",", 0)));
		
		for (String userId : friendsIds) {
			Users userToFind = new Users(userId.trim());
			Users userToAdd = users.get(users.indexOf(userToFind));
			user.addFriend(userToAdd);
		}
		
	}

	private int importusers(List<Users> users, UsersDao userDao) {
		int contImportedUsers = 0;
		int i = 0;
		userDao.startTransaction();

		for (Users u : users) {
			try {
				setFriendsByString(u,users);
				userDao.save(u);
				contImportedUsers++;				
			} catch (Exception e) {
				userDao.rollback();
				Logger log = Logger.getLogger(this.getClass());
				log.error("Fail to import user: " + u.getUserId() + ": " + e.getMessage());
			}
		}
		
		userDao.commit();
		return contImportedUsers;
	}
	
	public int importData() throws FileNotFoundException, IOException, ParseException {
		int contImportedUsers = 0;

		List<Users> users = load();

		try (UsersDao userDao = new UsersDao()) {
			contImportedUsers = importusers(users, userDao);
		}

		return contImportedUsers;
	}	

}
