package br.edu.ufsj.rodrigocarvalho.recsys.system;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.edu.ufsj.rodrigocarvalho.recsys.dao.BusinessJpaDao;
import br.edu.ufsj.rodrigocarvalho.recsys.loader.BusinessLoader;
import br.edu.ufsj.rodrigocarvalho.recsys.model.Business;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ImportBusinessTest {

	private BusinessJpaDao businessDao;

	@Before
	public void test01BusinessCleanTable() throws Exception {
		businessDao = new BusinessJpaDao();
		businessDao.startTransaction();		
		businessDao.removeAll();		
		businessDao.commit();
	}	
	
	@Test
	public void test01BusinessImportBatchToBD() throws Exception {
		BusinessLoader BusinessLoader = new BusinessLoader("test_datasets/business.json");
		int countBusinessImported = BusinessLoader.importDataBatch(5);		
		assertEquals(10, countBusinessImported);
	}
	
	@Test
	public void test02BusinessCleanTable() throws Exception {
		businessDao = new BusinessJpaDao();
		businessDao.startTransaction();		
		businessDao.removeAll();		
		businessDao.commit();
		
		List<Business> Business = businessDao.findAll(); 
		
		assertEquals(0, Business.size());
		
	}
}
