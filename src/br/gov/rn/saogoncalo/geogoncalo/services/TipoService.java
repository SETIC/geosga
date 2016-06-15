package br.gov.rn.saogoncalo.geogoncalo.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.gov.rn.saogoncalo.geogoncalo.dao.TipoDAO;

@Path("")
public class TipoService {
	
	@GET
	@Path("/tipos")
	@Produces("application/json")
	public Response listar(){
		TipoDAO tipoDAO = new TipoDAO();
		
		return Response.status(200).entity(tipoDAO.listar()).build();
	}
}
