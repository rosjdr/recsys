package br.edu.ufsj.rodrigocarvalho.recsys.loader;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import br.edu.ufsj.rodrigocarvalho.recsys.dao.UsersJdbcDao;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;
import br.edu.ufsj.rodrigocarvalho.recsys.system.ImportData;
import br.edu.ufsj.rodrigocarvalho.recsys.system.ProgressBar;

public class UsersLoader extends JsonLoader<Users> {

	public UsersLoader(String fileName) {
		super(fileName);
	}

	@Override
	protected Users parseJSonObject(JSONObject jsonObject) {
		String id = (String) jsonObject.get("user_id");
		String name = (String) jsonObject.get("name");
		Long fans = (Long) jsonObject.get("fans");
		Double averageStars = (Double) jsonObject.get("average_stars");

		Users user = new Users(id, name, fans, averageStars);
		user.setFriendsStr((String) jsonObject.get("friends"));

		return user;
	}

	public int importDataBatch(int batchSize, ProgressBar progressBar) throws Exception {
		Logger logger = Logger.getLogger(ImportData.class);
		int contImportedUsers = 0;
		
		logger.info("Lendo arquivo: " + getFileName());
		List<Users> users = load();	
		logger.info("Fim da leitura do arquivo: " + getFileName() + ". Iniciando importação...");
		try (UsersJdbcDao userDao = new UsersJdbcDao()) {
			contImportedUsers = importUsersBatch(users, userDao, batchSize, progressBar);	
		}

		return contImportedUsers;
	}

	private int importUsersBatch(List<Users> users, UsersJdbcDao userDao, int batchSize, ProgressBar progressBar) throws SQLException {
		int contImportedUsers = 0;
		int[] importeds;
		
		List<Users> batchToImport = new ArrayList<Users>();
		
		userDao.setAutoCommit(false);
		for (Users u : users) {
			batchToImport.add(u);
			if (batchToImport.size() == batchSize) {
				importeds = userDao.executeBatch(batchToImport);
				batchToImport.clear();				
				userDao.commit();
			}
			contImportedUsers++;
			progressBar.add("user", String.valueOf(contImportedUsers*100/users.size())+"%");
		}
		
		if (batchToImport.size() > 0) {
			importeds = userDao.executeBatch(batchToImport);
		}
		
		return contImportedUsers;
	}

}
