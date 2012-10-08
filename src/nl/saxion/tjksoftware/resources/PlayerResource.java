package nl.saxion.tjksoftware.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import nl.saxion.tjksoftware.models.Casino;
import nl.saxion.tjksoftware.models.Player;

@Path("/player/{id}")
public class PlayerResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Player getPlayer(@PathParam("id") Integer id) {
		if (id != null) {
			for (Player player : Casino.getInstance().getPlayers()) {
				if (player.getID() == id) {
					return player;
				}
			}
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String loginPlayer(Player player) {
		if (player != null) {
			return player.getAccessToken();
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}

}
