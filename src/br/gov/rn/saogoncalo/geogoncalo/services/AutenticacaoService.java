package br.gov.rn.saogoncalo.geogoncalo.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import br.gov.rn.saoconcalo.geogoncalo.models.Credencial;
import br.gov.rn.saogoncalo.geogoncalo.dao.CredencialDAO;

@Path("/autenticar")
public class AutenticacaoService {
	
	@GET
	@Path("/")
	@Produces("application/json")
	public Response autenticar(@Context HttpServletRequest req){
		CredencialDAO credencialDAO = new CredencialDAO();
		
		HttpSession session = req.getSession(true);
		
		Credencial credencial = null;
		
		if(session.getAttribute("login") != null)
			credencial = credencialDAO.selecionarPorLogin(session.getAttribute("login").toString());
		
		System.out.println(credencial);
		
		if(credencial != null){
			return Response.ok().entity(credencial).build();
		}
		
		return Response.status(401).build();
	}
	
	@POST
	@Path("/login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response login(@Context HttpServletRequest req, Credencial credencial){
		CredencialDAO credencialDAO = new CredencialDAO();
		
		HttpSession session = req.getSession(true);
		
		System.out.println(credencialDAO.autenticar(credencial));
		
		if(credencialDAO.autenticar(credencial)){
			session.setAttribute("login", credencial.getLogin());
			return Response.ok().entity(credencial).build();
		} else {
			return Response.status(401).build();
		}
	}
	
	@GET
	@Path("/logout")
	@Produces("application/json")
	public Response logout(@Context HttpServletRequest req){
		HttpSession session = req.getSession(true);
		System.out.println("AE");
		session.invalidate();
		
		return Response.ok().build();
	}
}
