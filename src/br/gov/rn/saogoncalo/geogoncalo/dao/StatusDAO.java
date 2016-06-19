package br.gov.rn.saogoncalo.geogoncalo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.gov.rn.saoconcalo.geogoncalo.models.Status;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class StatusDAO {
	protected Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	private Session session = getHibernateSession();
	
	public List<Status> listarStatus(){
		List<Status> status = new ArrayList<Status>();
		
		try{
			Query query = session.createQuery("FROM Status");
			status = query.list();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
			return null;
		}
		
		return status;
	}
}
