package br.gov.rn.saogoncalo.geogoncalo.tests;

import br.gov.rn.saoconcalo.geogoncalo.models.Bairro;
import br.gov.rn.saogoncalo.geogoncalo.dao.BairroDAO;

public class BairroTest {
	public static void main(String args[]){
		BairroDAO bairroDAO = new BairroDAO();
		
		Bairro bairro = new Bairro();
		bairro.setNome("Bairro Teste 2 " + Math.random());
		
//		System.out.println(bairroDAO.inserir(bairro));
//		System.out.println(bairroDAO.selecionar("BAIRRO TESTE 2 0.3459161075538989"));
//		System.out.println(bairroDAO.selecionar(1L));
	}
}
