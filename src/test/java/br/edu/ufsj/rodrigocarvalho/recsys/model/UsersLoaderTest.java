package br.edu.ufsj.rodrigocarvalho.recsys.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.json.simple.parser.ParseException;
import org.junit.Test;

import br.edu.ufsj.rodrigocarvalho.recsys.loaders.UsersLoader;

public class UsersLoaderTest {

	@Test
	public void verifyUsersImportFromFile() throws FileNotFoundException, IOException, ParseException {

		UsersLoader usersLoader = new UsersLoader(
				"/home/rodrigo/sistemas_java/recsys/datasets/yelp_dataset/user.json.test");

		List<Users> users;
		users = usersLoader.load();
		assertNotEquals(0, users.size());
	}

	@Test(expected = FileNotFoundException.class)
	public void verifyInvalidFileNameToImport() throws FileNotFoundException, IOException, ParseException {
		UsersLoader usersLoader = new UsersLoader("nomeInvalidoDeArquivo.json");
		List<Users> users = usersLoader.load();
	}
	
	@Test(expected = ParseException.class)
	public void testaConteudoDeArquivoInvalidoParaImportarJson() throws FileNotFoundException, IOException, ParseException {
		UsersLoader usersLoader = new UsersLoader(
				"/home/rodrigo/sistemas_java/recsys/datasets/yelp_dataset/user.jsonInvalido.test");

		List<Users> users = usersLoader.load();
	}
}
