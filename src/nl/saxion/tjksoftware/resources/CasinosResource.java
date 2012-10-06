package nl.saxion.tjksoftware.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.saxion.tjksoftware.models.Casino;

@Path("/casinos")
public class CasinosResource {
	public CasinosResource() {
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Casino getCasino() {
		return Casino.getInstance();
	}
}
