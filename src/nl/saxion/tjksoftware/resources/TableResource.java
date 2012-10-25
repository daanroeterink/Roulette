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

import nl.saxion.tjksoftware.models.Bet;
import nl.saxion.tjksoftware.models.Casino;
import nl.saxion.tjksoftware.models.Player;
import nl.saxion.tjksoftware.models.Table;

@Path("/table/{id}")
public class TableResource
{

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Table getTable(@PathParam("id") Integer id)
	{
		if (id != null)
			for (Table table : Casino.getInstance().getTables())
			{
				if (table.getID() == id)
				{
					return table;
				}
			}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addPlayer(@PathParam("id") Integer id, Player player)
	{
		if (player != null && id != null)
		{
			Table table = Casino.getInstance().getTableWithID(id);
			Casino.getInstance().removePlayerFromTables(player);
			if (table.getPlayers().size() < 10)
			{
				table.addPlayer(Casino.getInstance().getPlayerWithAccessToken(player.getAccessToken()));
			}
			else
			{
				throw new WebApplicationException(Status.BAD_REQUEST);
			}
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/player/{access_token}")
	public void addBet(@PathParam("id") Integer id, @PathParam("access_token") String accessToken, Bet bet)
	{
		if (accessToken != null)
		{
			Player player = Casino.getInstance().getPlayerWithAccessToken(accessToken);
			if (player != null && id != null && bet != null)
			{
				Table table = Casino.getInstance().getTableWithID(id);
				table.placeBet(player, bet.getBetAmmount(), bet.getBetLocation());
			}
		}
	}
}
