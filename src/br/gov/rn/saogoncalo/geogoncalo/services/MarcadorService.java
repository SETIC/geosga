package br.gov.rn.saogoncalo.geogoncalo.services;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import br.gov.rn.saoconcalo.geogoncalo.models.Marcador;
import br.gov.rn.saogoncalo.geogoncalo.dao.MarcadorDAO;

@Path("/")
public class MarcadorService {
	MarcadorDAO marcadorDAO = new MarcadorDAO();
	
	@GET
	@Path("/marcadores")
	@Produces("application/json")
	public Response listarMarcadores(@QueryParam("ids") List<Long> categorias){		
		return Response.ok().entity(marcadorDAO.listarMarcadores()).encoding("UTF-8").build();
	}
	
	@GET
	@Path("/marcador/categoria/{id}")
	@Produces("application/json")
	public Response listarMarcadorPorCategoria(@PathParam("id") Long id){
		return Response.ok().entity(marcadorDAO.listarMarcadorPorCategoria(id)).build();
	}
	
	@GET
	@Path("/marcador/{id}")
	@Produces("application/json")
	public Response selecionarMarcador(@PathParam("id") Long id){
		return Response.ok().entity(marcadorDAO.selecionarMarcador(id)).build();
	}
	
	@POST
	@Path("/marcadores")
	@Produces("application/json")
	public Response inserirMarcador(Marcador marcador){
		return Response.status(201).entity(marcadorDAO.inserirMarcador(marcador)).build();
	}
	
	@PUT
	@Path("/marcador/{id}")
	@Produces("application/json")
	@Consumes("application/json")
	public Response atualizarMarcador(Marcador marcador, @PathParam("id") Long id){
		marcador.setId(id);
		
		marcadorDAO.atualizarMarcador(marcador);
		
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/marcador/{id}")
	@Produces("application/json")
	public Response deletarMarcador(@PathParam("id") Long id){
		marcadorDAO.excluirMarcador(id);
		
		return Response.ok().build();
	}
	
}
