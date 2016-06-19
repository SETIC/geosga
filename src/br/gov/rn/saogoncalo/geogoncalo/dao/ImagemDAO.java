package br.gov.rn.saogoncalo.geogoncalo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.gov.rn.saoconcalo.geogoncalo.models.Imagem;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class ImagemDAO {
	protected Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	private Session session = getHibernateSession();
	
	public Long inserirImagem(Imagem imagem){
		Long insertId = -1L;
		
		try{
			insertId = (Long) session.save(imagem);
			
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return insertId;
	}
}
