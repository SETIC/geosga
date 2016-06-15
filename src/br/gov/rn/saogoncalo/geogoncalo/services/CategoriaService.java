package br.gov.rn.saogoncalo.geogoncalo.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.gov.rn.saogoncalo.geogoncalo.dao.CategoriaDAO;

@Path("")
public class CategoriaService {

	@GET
	@Path("/categorias")
	@Produces("application/json")
	public Response listar(){
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		return Response.ok().entity(categoriaDAO.listar()).build();
	}
	
	@GET
	@Path("/categoria/tipo/{id}")
	@Produces("application/json")
	public Response listarPorTipo(@PathParam("id") Long id){
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		
		return Response.ok().entity(categoriaDAO.listarPorTipo(id)).build();
	}
}
