package br.gov.rn.saogoncalo.geogoncalo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.gov.rn.saoconcalo.geogoncalo.models.Tipo;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class TipoDAO {
	protected static Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	private Session session = getHibernateSession();
	
	public List<Tipo> listar(){
		List<Tipo> tipos = new ArrayList<Tipo>();
		
		try{
			Query query = session.createQuery("FROM Tipo");
			tipos = query.list();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return tipos;
	}
}
