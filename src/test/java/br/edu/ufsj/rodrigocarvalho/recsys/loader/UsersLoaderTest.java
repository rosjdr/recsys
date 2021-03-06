package br.edu.ufsj.rodrigocarvalho.recsys.loader;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import br.edu.ufsj.rodrigocarvalho.recsys.model.Users;

public class UsersLoaderTest {

	@Test
	public void verifyUsersImportFromFile() throws FileNotFoundException, IOException, ParseException {

		UsersLoader usersLoader = new UsersLoader(
				"test_datasets/yelp_dataset/user.json");

		List<Users> users;
		users = usersLoader.load();
		assertEquals(10, users.size());
	}

	@Test(expected = FileNotFoundException.class)
	public void verifyInvalidFileNameToImport() throws FileNotFoundException, IOException, ParseException {
		UsersLoader usersLoader = new UsersLoader("nomeInvalidoDeArquivo.json");
		usersLoader.load();
	}
	
	@Test(expected = ParseException.class)
	public void testaConteudoDeArquivoInvalidoParaImportarJson() throws FileNotFoundException, IOException, ParseException {
		UsersLoader usersLoader = new UsersLoader(
				"test_datasets/yelp_dataset/user.jsonInvalido.test");

		usersLoader.load();
	}
	
}
