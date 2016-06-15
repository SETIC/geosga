package br.gov.rn.saogoncalo.geogoncalo.tests;

import br.gov.rn.saogoncalo.geogoncalo.dao.CategoriaDAO;

public class CategoriaTest {
	public static void main(String args[]){
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		System.out.println(categoriaDAO.listar());
	}
}
