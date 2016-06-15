package br.gov.rn.saogoncalo.geogoncalo.tests;

import br.gov.rn.saoconcalo.geogoncalo.models.Bairro;
import br.gov.rn.saoconcalo.geogoncalo.models.Categoria;
import br.gov.rn.saoconcalo.geogoncalo.models.Obra;
import br.gov.rn.saoconcalo.geogoncalo.models.Tipo;
import br.gov.rn.saogoncalo.geogoncalo.dao.ObraDAO;

public class ObraTest {
	public static void main(String args[]){
		ObraDAO obraDAO = new ObraDAO();
		
		Obra obra = new Obra();
		Categoria categoria = new Categoria();
		categoria.setId(1L);
		
		Tipo tipo = new Tipo();
		tipo.setId(1L);
		
		Bairro bairro = new Bairro();
		bairro.setNome("Teste");
		
		obra.setRua("Teste");
		obra.setTitulo("Teste");
		obra.setCategoria(categoria);
		obra.setTipo(tipo);
		obra.setBairro(bairro);
		obra.setDetalhes("Teste");
		obra.setDescricao("Teste");
		
//		System.out.println(obraDAO.inserirObra(obra));
		
		System.out.println(obraDAO.selecionarObra(3L));
	}
}
