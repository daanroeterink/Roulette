package nl.saxion.tjksoftware.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import nl.saxion.tjksoftware.models.Casino;
import nl.saxion.tjksoftware.models.Player;

@Path("/players")
public class PlayersResource
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Player> getPlayers()
	{
		return Casino.getInstance().getPlayers();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void RegisterPlayer(Player player)
	{
		if (player != null)
		{
			if (!Casino.getInstance().checkPlayerExistence(player))
			{
				player.calculateHashCode();
				Casino.getInstance().addUniquePlayer(player);
				return;
			}
		}

		throw new WebApplicationException(Status.BAD_REQUEST);
	}
}
