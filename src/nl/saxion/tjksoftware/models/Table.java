package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

	private int winningNumber;

	private boolean blockTable;

	public Table(int ID)
	{
		players = new ArrayList<Player>();
		bets = new ArrayList<Bet>();
		this.ID = ID;
		startTableThread();
	}

	public Table()
	{
		startTableThread();
	}

	private void startTableThread()
	{
		Timer timer = new Timer("Table: " + ID);
		TableThread t = new TableThread(ID);
		timer.schedule(t, 0, 10000);
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
			if (p.getID() == player.getID())
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

	public class TableThread extends TimerTask
	{
		/** Het id van de table */
		private int Id;

		private String logPrefix;

		public TableThread(int Id)
		{
			this.Id = Id;
			logPrefix = "Table " + Id + ": ";
			Log.I(logPrefix + "Started!");
		}

		public void run()
		{
			calculateWinners();
			calculateNextWinningNumber();
		}

		private void calculateNextWinningNumber()
		{
			Random r = new Random();
			winningNumber = r.nextInt(36);
			Log.I(logPrefix + "Next winning number: " + winningNumber);
		}

		private void calculateWinners()
		{
			if (players.size() > 0)
			{
				Log.I(logPrefix + "Calculating winners!");
			}
		}
	}
}