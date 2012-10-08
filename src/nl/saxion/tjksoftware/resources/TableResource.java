package nl.saxion.tjksoftware.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import nl.saxion.tjksoftware.models.Bet;
import nl.saxion.tjksoftware.models.Casino;
import nl.saxion.tjksoftware.models.Player;
import nl.saxion.tjksoftware.models.Table;

@Path("/table/{id}")
public class TableResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Table getTable(@PathParam("id")Integer id) {
		if(id != null)
		for(Table table : Casino.getInstance().getTables()) {
			if(table.getID() == id) {
				return table;
			}
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}
	
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON) 
	public void AddBet(@PathParam("id")Integer id, Bet bet) {
		if(id != null) {
			Player player = Casino.getInstance().getPlayerWithID(id);
			if(player != null)
			player.createBet(bet.getBetAmmount(), bet.getBetLocation());
			Casino.getInstance().getTableWithID(id);
			
		}
	}
}
