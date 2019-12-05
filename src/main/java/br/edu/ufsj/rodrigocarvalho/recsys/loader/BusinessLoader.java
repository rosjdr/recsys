package br.edu.ufsj.rodrigocarvalho.recsys.loader;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import br.edu.ufsj.rodrigocarvalho.recsys.dao.BusinessJdbcDao;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Business;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;
import br.edu.ufsj.rodrigocarvalho.recsys.system.ProgressBar;

public class BusinessLoader extends JsonLoader<Business> {

	public BusinessLoader(String fileName) {
		super(fileName);
	}

	@Override
	protected Business parseJSonObject(JSONObject jsonObject) {
		String id            = (String) jsonObject.get("business_id");
		String categories    = (String) jsonObject.get("categories");
		String city          = (String) jsonObject.get("city");
		Long isOpen          = (Long) jsonObject.get("is_open");
		Double latitude      = (Double) jsonObject.get("latitude");
		Double longitude     = (Double) jsonObject.get("longitude");
		String name          = (String) jsonObject.get("name");
		Long reviewCount     = (Long) jsonObject.get("review_count");
		Double stars         = (Double) jsonObject.get("stars");
		
		Business b = new Business(id, name, city, BigDecimal.valueOf(latitude), BigDecimal.valueOf(longitude), BigDecimal.valueOf(stars), reviewCount, isOpen, categories);
		return b;
	}
	
	public int importDataBatch(int batchSize) throws Exception {
		int contImportedUsers = 0;
		List<Business> business = load();
		
		try (BusinessJdbcDao businessDao = new BusinessJdbcDao()) {
			contImportedUsers = importBusinessBatch(business, businessDao, batchSize);	
		}

		return contImportedUsers;
	}

	private int importBusinessBatch(List<Business> business, BusinessJdbcDao businessDao, int batchSize) throws SQLException {
		int contImported = 0;
		int[] importeds;
		
		List<Business> batchToImport = new ArrayList<Business>();
		
		businessDao.setAutoCommit(false);
		for (Business b : business) {
			batchToImport.add(b);
			if (batchToImport.size() == batchSize) {
				importeds = businessDao.executeBatch(batchToImport);
				batchToImport.clear();				
				businessDao.commit();
				
			}
			contImported++;				
			ProgressBar.getInstance().add("business", String.valueOf(contImported)+"/"+business.size());
			
		}
		
		if (batchToImport.size() > 0) {
			importeds = businessDao.executeBatch(batchToImport);
		}
		
		return contImported;
	}
	

	
}
