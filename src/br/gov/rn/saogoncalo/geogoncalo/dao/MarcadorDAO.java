package br.gov.rn.saogoncalo.geogoncalo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.gov.rn.saoconcalo.geogoncalo.models.Marcador;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class MarcadorDAO {
	
	protected Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	private Session session = getHibernateSession();
	
	public List<Marcador> listarMarcadores(){
		List<Marcador> marcadores = new ArrayList<Marcador>();
		
		try{
			session.clear();
			Query query = session.createQuery("FROM Marcador");
			marcadores = query.list();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return marcadores;
	}
	
	public List<Marcador> listarMarcadorPorCategoria(Long categoriaId){
		List<Marcador> marcadores = new ArrayList<Marcador>();
		
		try{			
			Query query = session.createQuery("FROM Marcador m WHERE m.obra.categoria.id = :id");
			query.setParameter("id", categoriaId);
			marcadores = query.list();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return marcadores;
	}
	
	public List<Marcador> filtrarPorCategorias(List<String> categorias){
		List<Marcador> marcadores = new ArrayList<Marcador>();
		
		try{
			List<Long> ids = new ArrayList<Long>();
			
			for(String categoria: categorias){
				System.out.println(categoria);
				ids.add(Long.parseLong(categoria));
			}
			
			System.out.println(ids.size());
			
			Query query = session.createQuery("FROM Marcador m WHERE m.obra.categoria.id IN (:ids)");
			query.setParameter("ids", ids);
			
			marcadores = query.list();
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return marcadores;
	}
	
	public Marcador selecionarMarcador(Long id){
		Marcador marcador = new Marcador();
		
		try{			
			Query query = session.createQuery("FROM Marcador m WHERE m.id = :id");
			query.setParameter("id", id);
			
			marcador = (Marcador) query.uniqueResult();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return marcador;
	}
	
	public Marcador inserirMarcador(Marcador marcador){		
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
			session.flush();
		}
		
		return marcador;
	}

	public void atualizarMarcador(Marcador marcador){		
		try{
			System.out.println(marcador);
			session.update(marcador);
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
			session.flush();
		}
	}

	public void excluirMarcador(Long id){
		ObraDAO obraDAO = new ObraDAO();
		Marcador marcador = selecionarMarcador(id);
		
		try{						
			session.delete(marcador);
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
			session.flush();
		}
	}
}