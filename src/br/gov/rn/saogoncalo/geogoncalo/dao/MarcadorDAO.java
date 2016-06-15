package br.gov.rn.saogoncalo.geogoncalo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.gov.rn.saoconcalo.geogoncalo.models.Marcador;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class MarcadorDAO {
	protected Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	Session session = null;
	
	public List<Marcador> listarMarcadores(){
		session = getHibernateSession();
		List<Marcador> marcadores = new ArrayList<Marcador>();
		
		try{
			Query query = session.createQuery("FROM Marcador");
			marcadores = query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return marcadores;
	}
	
	public List<Marcador> listarMarcadorPorCategoria(Long categoriaId){
		session = getHibernateSession();
		List<Marcador> marcadores = new ArrayList<Marcador>();
		try{
			Query query = session.createQuery("FROM Marcador m WHERE m.obra.categoria.id = :id");
			query.setParameter("id", categoriaId);
			marcadores = query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return marcadores;
	}
	
	public Marcador selecionarMarcador(Long id){
		session = getHibernateSession();
		Marcador marcador = new Marcador();
		
		try{
			Query query = session.createQuery("FROM Marcador m WHERE m.id = :id");
			query.setParameter("id", id);
			
			marcador = (Marcador) query.uniqueResult();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return marcador;
	}
	
	public Marcador inserirMarcador(Marcador marcador){
		session = getHibernateSession();
		
		Long insertId = -1L;
		
		ObraDAO obraDAO = new ObraDAO();
		
		try{
			marcador.setObra(obraDAO.inserirObra(marcador.getObra()));
			insertId = (Long) session.save(marcador);
			
			marcador.setId(insertId);
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
		
		return marcador;
	}

	public void atualizarMarcador(Marcador marcador){
		session = getHibernateSession();
		
		try{
			session.update(marcador);
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
	}

	public void excluirMarcador(Long id){
		session = getHibernateSession();
		ObraDAO obraDAO = new ObraDAO();
		Marcador marcador = selecionarMarcador(id);
		
		try{			
			session.delete(marcador);
			obraDAO.deletarObra(marcador.getObra().getId());
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
	}
}