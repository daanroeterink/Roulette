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

import nl.saxion.tjksoftware.models.Casino;
import nl.saxion.tjksoftware.models.Log;
import nl.saxion.tjksoftware.models.Player;

@Path("/player/{accessToken}")
public class PlayerResource
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Player getPlayer(@PathParam("accessToken") String accessToken)
	{
		if (accessToken != null)
		{
			for (Player player : Casino.getInstance().getPlayers())
			{
				if (player.getAccessToken() != null)
				{
					if (player.getAccessToken().equals(accessToken))
					{
						return player;
					}
				}
			}
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void addMoney(@PathParam("accessToken") String accessToken, Double amount)
	{
		if (accessToken != null && amount != null)
		{
			Player player = Casino.getInstance().getPlayerWithAccessToken(accessToken);
			player.addMoney(amount);
			Log.I("Free money: " + player.getUsername() + " - Current balance: " + player.getMoney());
		}
	}
}
