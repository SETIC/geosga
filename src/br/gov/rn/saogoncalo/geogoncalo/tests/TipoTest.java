package br.gov.rn.saogoncalo.geogoncalo.tests;

import br.gov.rn.saogoncalo.geogoncalo.dao.TipoDAO;

public class TipoTest {
	public static void main(String args[]){
		TipoDAO tipoDAO = new TipoDAO();
		
		System.out.println(tipoDAO.listar());
	}
}
