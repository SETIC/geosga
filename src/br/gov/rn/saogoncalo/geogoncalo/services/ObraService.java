package br.gov.rn.saogoncalo.geogoncalo.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.gov.rn.saoconcalo.geogoncalo.models.Obra;
import br.gov.rn.saogoncalo.geogoncalo.dao.ObraDAO;

@Path("")
public class ObraService {
	@GET
	@Path("/obras")
	@Produces("application/json")
	public Response listarObras(){
		ObraDAO obraDAO = new ObraDAO();
		
		return Response.ok().entity(obraDAO.listarObras()).build();
	}
	
	@DELETE
	@Path("/obra/{id}")
	@Produces("application/json")
	public Response deletarObra(@PathParam("id") Long id){
		ObraDAO obraDAO = new ObraDAO();
		obraDAO.deletarObra(id);
		
		return Response.ok().build();
	}
	
	@PUT
	@Path("/obra/{id}")
	@Consumes("application/json")
	public Response atualizarObra(@PathParam("id") Long id, Obra obra){
		ObraDAO obraDAO = new ObraDAO();
		System.out.println(obra);
		obraDAO.atualizarObra(obra);
		
		return Response.ok().build();
	}
}