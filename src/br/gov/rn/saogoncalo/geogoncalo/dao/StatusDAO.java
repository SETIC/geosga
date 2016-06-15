package br.gov.rn.saogoncalo.geogoncalo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.gov.rn.saoconcalo.geogoncalo.models.Status;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class StatusDAO {
	protected static Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	public List<Status> listarStatus(){
		List<Status> status = new ArrayList<Status>();
		Session session = getHibernateSession();
		
		try{
			Query query = session.createQuery("FROM Status");
			status = query.list();
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
		return status;
	}
}
