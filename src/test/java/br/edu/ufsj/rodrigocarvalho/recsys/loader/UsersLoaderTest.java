package br.edu.ufsj.rodrigocarvalho.recsys.loader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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
				"test_datasets/user.json.test");

		List<Users> users;
		users = usersLoader.load();
		assertNotEquals(0, users.size());
	}

	@Test(expected = FileNotFoundException.class)
	public void verifyInvalidFileNameToImport() throws FileNotFoundException, IOException, ParseException {
		UsersLoader usersLoader = new UsersLoader("nomeInvalidoDeArquivo.json");
		usersLoader.load();
	}
	
	@Test(expected = ParseException.class)
	public void testaConteudoDeArquivoInvalidoParaImportarJson() throws FileNotFoundException, IOException, ParseException {
		UsersLoader usersLoader = new UsersLoader(
				"test_datasets/user.jsonInvalido.test");

		usersLoader.load();
	}
	
	@Test
	public void testUsersImportPoolToBD() {
		UsersLoader usersLoader = new UsersLoader("test_datasets/user.json.test");
		int countUsersImported = usersLoader.importData();
		assertEquals(10, countUsersImported);
		
	}
}
