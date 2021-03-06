package nl.saxion.tjksoftware.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import nl.saxion.tjksoftware.models.Casino;
import nl.saxion.tjksoftware.models.Player;

@Path("/login")
public class LoginResource
{
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Player loginPlayer(Player player)
	{
		if (player != null)
		{
			if (Casino.getInstance().checkPlayerExistence(player))
			{
				Player Retplayer = Casino.getInstance().getPlayerWithUsername(player.getUsername());
				return Retplayer;
			}
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}
}
