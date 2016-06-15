package br.gov.rn.saogoncalo.geogoncalo.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import br.gov.rn.saoconcalo.geogoncalo.models.Credencial;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;
import br.gov.rn.saogoncalo.geogoncalo.util.StringUtil;

public class CredencialDAO {
	protected static Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	Session session = null;
	
	public Credencial inserir(Credencial credencial){
		session = getHibernateSession();
		
		try{
			credencial.setSenha(StringUtil.md5(credencial.getSenha()));
			credencial.setId((Long) session.save(credencial));
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
			return null;
		}
		
		session.close();
		return credencial;
	}
	
	public boolean autenticar(Credencial credencial){
		session = getHibernateSession();
		
		try{
			Query query = session.createQuery("FROM Credencial c WHERE c.login = :login AND c.senha = :senha");
			
			query.setParameter("login", credencial.getLogin());
			query.setParameter("senha", StringUtil.md5(credencial.getSenha()));
			
			Credencial _credencial = (Credencial) query.uniqueResult();
			
			System.out.println(_credencial);
			
			if(_credencial != null)
				return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
		session.close();
		return false;
	}
	
	public Credencial selecionarPorLogin(String login){
		session = getHibernateSession();
		
		try{
			Query query = session.createQuery("FROM Credencial c WHERE c.login = :login");
			query.setParameter("login", login);
			
			Credencial credencial = (Credencial) query.uniqueResult();
			
			session.close();
			return credencial;
		} catch(Exception e){
			e.printStackTrace();
			session.close();
			return null;
		}
	}
}
