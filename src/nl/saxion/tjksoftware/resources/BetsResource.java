package nl.saxion.tjksoftware.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.saxion.tjksoftware.models.Bet;
import nl.saxion.tjksoftware.models.Casino;

@Path("/bets")
public class BetsResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Bet> getBets() {
		return Casino.getInstance().getBets();
	}
	
}
