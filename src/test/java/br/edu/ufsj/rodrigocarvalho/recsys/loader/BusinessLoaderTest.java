package br.edu.ufsj.rodrigocarvalho.recsys.loader;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Business;

public class BusinessLoaderTest {

	@Test
	public void testBusinessImportFromFile() throws FileNotFoundException, IOException, ParseException {

		BusinessLoader loader = new BusinessLoader("test_datasets/yelp_dataset/business.json");

		List<Business> business;
		business = loader.load();
		assertEquals(10, business.size());
	}
}
