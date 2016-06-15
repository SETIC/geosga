package br.gov.rn.saogoncalo.geogoncalo.tests;

import br.gov.rn.saogoncalo.geogoncalo.dao.MarcadorDAO;

public class MarcadorTest {
	public static void main(String args[]){
		MarcadorDAO marcadorDAO = new MarcadorDAO();
		
//		System.out.println(marcadorDAO.listarMarcadorPorCategoria(1L));
//		marcadorDAO.excluirMarcador(12L);
		
		System.out.println(marcadorDAO.listarMarcadores());
	}
}
