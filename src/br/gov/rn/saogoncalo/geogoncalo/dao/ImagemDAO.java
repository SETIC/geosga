package br.gov.rn.saogoncalo.geogoncalo.dao;

import org.hibernate.Session;

import br.gov.rn.saoconcalo.geogoncalo.models.Imagem;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class ImagemDAO {
	protected static Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	public Long inserirImagem(Imagem imagem){
		Long insertId = -1L;
		Session session = getHibernateSession();
		
		try{
			insertId = (Long) session.save(imagem);
			
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return insertId;
	}
}
