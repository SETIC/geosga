package br.gov.rn.saogoncalo.geogoncalo.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.gov.rn.saoconcalo.geogoncalo.models.Obra;
import br.gov.rn.saogoncalo.geogoncalo.config.Hibernate;

public class ObraDAO {
	protected Session getHibernateSession(){
		return Hibernate.getInstance().getSession();
	}
	
	private Session session = getHibernateSession();
	
	public List<Obra> listarObras(){
		List<Obra> obras = new ArrayList<Obra>();
		
		try{
			Query query = session.createQuery("FROM Obra");
			obras = query.list();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return obras;
	}
	
	public List<Obra> listarObrasRelacionadas(Long id, Long categoriaId){
		List<Obra> obras = new ArrayList<Obra>();
		
		try{			
			Query query = session.createQuery("FROM Obra o WHERE o.categoria.id = :categoriaId AND o.id != :obraId")
					.setMaxResults(4);
			query.setParameter("categoriaId", categoriaId);
			query.setParameter("obraId", id);
			
			if(query.list().size() == 0){
				query = session.createQuery("FROM Obra o WHERE o.id != :obraId")
						.setMaxResults(4);
				query.setParameter("obraId", id);
				
				obras = query.list();
			} else {
				obras = query.list();
			}
			session.flush();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return obras;
	}
	
	public Obra selecionarObra(Long id){
		Obra obra = new Obra();

		try{
			Query query = session.createQuery("FROM Obra o WHERE o.id = :id");
			query.setParameter("id", id);
			obra = (Obra) query.uniqueResult();
			session.flush();
		} catch(Exception e){
			e.printStackTrace();
			session.flush();
		}
		
		return obra;
	}
	
	public Obra inserirObra(Obra obra){
		BairroDAO bairroDAO = new BairroDAO();

		try{
			if(bairroDAO.selecionar(obra.getBairro().getNome()) != null){
				obra.getBairro().setId(
						bairroDAO.selecionar(obra.getBairro().getNome().toUpperCase()).getId()
						);
			} else {
				obra.getBairro().setId(bairroDAO.inserir(obra.getBairro()));
			}
			
			session = getHibernateSession();
			obra.setId((Long) session.save(obra));
			session.beginTransaction().commit();
			session.flush();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
			session.flush();
		}
		
		return obra;
	}

	public void deletarObra(Long id){
		Obra obra = new Obra();
		obra.setId(id);
		
		try{
			session.delete(obra);
			session.beginTransaction().commit();
			session.flush();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
			session.flush();
		}
	}
	
	public void atualizarObra(Obra obra){
		
		try{
			session.clear();
			session.update(obra);
			session.beginTransaction().commit();
			session.flush();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
			session.flush();
		}
	}

	public boolean atualizarStatusObra(Long obraId, Integer statusId){
		boolean _return = false;
		try{
			Query query = session.createQuery("UPDATE Obra o SET o.status.id = :statusId WHERE o.id = :id");
			query.setParameter("statusId", statusId);
			query.setParameter("id", obraId);
			query.executeUpdate();
			_return = true;
			
			session.beginTransaction().commit();
		} catch(Exception e){
			e.printStackTrace();
			session.beginTransaction().rollback();
			session.flush();
			_return = false;
		}
		
		return _return;
	}
}
