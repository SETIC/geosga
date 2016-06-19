package br.gov.rn.saogoncalo.geogoncalo.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.gov.rn.saoconcalo.geogoncalo.models.Bairro;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class BairroDAO {
	protected static Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	private Session session = getHibernateSession();
	
	public Long inserir(Bairro bairro){
		Long insertId = -1L;
		
		bairro.setNome(bairro.getNome().toUpperCase());
		try{
			insertId = (Long) session.save(bairro);
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
			session.flush();
		}
		
		return insertId;
	}
	
	public Bairro selecionar(String nome){
		Bairro bairro = new Bairro();
		
		try{
			Query query = session.createQuery("FROM Bairro b WHERE b.nome = :nome");
			query.setParameter("nome", nome.toUpperCase());
			bairro = (Bairro) query.uniqueResult();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return bairro;
	}
	
	public Bairro selecionar(Long id){
		Bairro bairro = new Bairro();
		
		try{
			Query query = session.createQuery("FROM Bairro b WHERE b.id = :id");
			query.setParameter("id", id);
			bairro = (Bairro) query.uniqueResult();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return bairro;
	}
}
