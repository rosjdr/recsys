package br.edu.ufsj.rodrigocarvalho.recsys;

public class GenresTest {

	public void deveCarregarCategorias() {
		
		CategoriesLoader categoriesLoader = new CategoriesLoader();
		categoriesLoader.load();
	}
}
