package br.gov.rn.saogoncalo.geogoncalo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.gov.rn.saoconcalo.geogoncalo.models.Categoria;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class CategoriaDAO {
	protected static Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	private Session session = getHibernateSession();
	
	public List<Categoria> listar(){
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		try{
			Query query = session.createQuery("FROM Categoria");
			categorias = query.list();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return categorias;
	}
	
	public List<Categoria> listarPorTipo(Long tipoId){
		List<Categoria> categorias = new ArrayList<Categoria>();
		
		try{
			Query query = session.createQuery("FROM Categoria c WHERE c.tipo.id = :tipoId");
			query.setParameter("tipoId", tipoId);
			
			categorias = query.list();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return categorias;
	}
}
