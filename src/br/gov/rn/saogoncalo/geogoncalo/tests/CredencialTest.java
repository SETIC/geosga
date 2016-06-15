package br.gov.rn.saogoncalo.geogoncalo.tests;

import br.gov.rn.saoconcalo.geogoncalo.models.Credencial;
import br.gov.rn.saogoncalo.geogoncalo.dao.CredencialDAO;
import junit.framework.TestCase;

public class CredencialTest{
	public static void main(String args[]){
		testeAutenticar();
	}
	
	public static void testeAutenticar(){
		CredencialDAO credencialDAO = new CredencialDAO();
		
		Credencial credencial = new Credencial();
		credencial.setLogin("clayton");
		credencial.setSenha("abc123");
		
		System.out.println(credencialDAO.autenticar(credencial));
	}
	
	public static void testeInserir(){
		CredencialDAO credencialDAO = new CredencialDAO();
		
		Credencial credencial = new Credencial();
		credencial.setLogin("clayton");
		credencial.setSenha("abc123");
		
		System.out.println(credencialDAO.inserir(credencial));
	}
}
