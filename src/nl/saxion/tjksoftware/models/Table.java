package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

import nl.saxion.tjksoftware.models.Bet.BetLocation;

@XmlRootElement
public class Table
{

	private List<Player> players;

	private List<Bet> bets;

	private int ID;

	public Table(int ID)
	{
		players = new ArrayList<Player>();
		bets = new ArrayList<Bet>();
		this.ID = ID;
	}

	public Table()
	{

	}

	public Player getPlayer(int id)
	{
		Player player = players.get(0);
		return player;
	}

	public List<Player> getPlayers()
	{
		return players;
	}

	public void setPlayers(List<Player> players)
	{
		this.players = players;
	}

	public List<Bet> getBets()
	{
		return bets;
	}

	public void setBets(List<Bet> bets)
	{
		this.bets = bets;
	}

	public int getID()
	{
		return ID;
	}

	public void setID(int iD)
	{
		ID = iD;
	}

	public boolean addPlayer(Player newPlayer)
	{

		if (players != null && newPlayer != null)
		{
			if (players.size() < 10)
			{
				players.add(newPlayer);
				return true;
			}
			else
			{
				throw new WebApplicationException(Status.NOT_ACCEPTABLE);
			}
		}
		return false;
	}

	public void removePlayer(Player player)
	{
		int t = 0;
		for (Player p : players)
		{
			if (p.equals(player))
			{
				players.remove(t);
			}
			t++;
		}
	}

	public boolean placeBet(Player player, int ammount, BetLocation betLocation)
	{
		if (players != null && betLocation != null && player != null)
		{
			Bet bet = player.createBet(ammount, betLocation);
			bets.add(bet);
		}
		return false;
	}

}