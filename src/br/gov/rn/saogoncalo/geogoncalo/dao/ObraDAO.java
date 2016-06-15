package br.gov.rn.saogoncalo.geogoncalo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import br.gov.rn.saoconcalo.geogoncalo.models.Obra;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class ObraDAO {
	protected static Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	public List<Obra> listarObras(){
		Session session = getHibernateSession();
		List<Obra> obras = new ArrayList<Obra>();
		
		try{
			Query query = session.createQuery("FROM Obra");
			obras = query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return obras;
	}
	
	public List<Obra> listarObrasRelacionadas(Obra obra){
		List<Obra> obras = new ArrayList<Obra>();
		Session session = getHibernateSession();
		
		try{
			Query query = session.createQuery("From Obra o WHERE o.categoria.id = :categoriaId AND o.id != :obraId");
			query.setParameter("categoriaId", obra.getCategoria().getId());
			query.setParameter("obraId", obra.getId());
			
			obras = query.list();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return obras;
	}
	
	public Obra selecionarObra(Long id){
		Session session = getHibernateSession();
		Obra obra = new Obra();

		try{
			Query query = session.createQuery("FROM Obra o WHERE o.id = :id");
			query.setParameter("id", id);
			obra = (Obra) query.uniqueResult();
		} catch(Exception e){
			e.printStackTrace();
		}
		
		return obra;
	}
	
	public Obra inserirObra(Obra obra){
		BairroDAO bairroDAO = new BairroDAO();
		
		Session session = getHibernateSession();
	
		try{
			if(bairroDAO.selecionar(obra.getBairro().getNome()) != null){
				obra.getBairro().setId(
						bairroDAO.selecionar(obra.getBairro().getNome().toUpperCase()).getId()
						);
			} else {
				obra.getBairro().setId(bairroDAO.inserir(obra.getBairro()));
			}
			
			obra.setId((Long) session.save(obra));
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
		
		return obra;
	}

	public void deletarObra(Long id){
		Session session = getHibernateSession();
		Obra obra = new Obra();
		obra.setId(id);
		
		try{
			session.delete(obra);
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
	}
	
	public void atualizarObra(Obra obra){
		Session session = getHibernateSession();
		
		try{
			session.update(session);
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
		}
	}
}
