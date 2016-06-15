package br.gov.rn.saogoncalo.geogoncalo.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import br.gov.rn.saogoncalo.geogoncalo.dao.StatusDAO;

@Path("")
public class StatusService {
	@GET
	@Path("/status")
	@Produces("application/json")
	public Response listarStatus(){
		StatusDAO statusDAO = new StatusDAO();
		
		return Response.ok().entity(statusDAO.listarStatus()).build();
	}
}
