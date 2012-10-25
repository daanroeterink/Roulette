package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.Iterator;
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

	private int currentRound = 0;

	private List<String> lastWinners = new ArrayList<String>();

	private BetLocation winningNumber;

	private String logPrefix;

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
		timer.schedule(t, 0, 60000);
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

	public int getCurrentRound()
	{
		return currentRound;
	}

	public List<String> getLastWinners()
	{
		return lastWinners;
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
		if (player.getAccessToken() != null)
		{
			Iterator<Player> i = players.iterator();
			while (i.hasNext())
			{
				Player p = i.next();
				if (p.getAccessToken() != null)
				{
					if (p.getAccessToken().equals(player.getAccessToken()))
					{
						i.remove();
					}
				}
			}
		}
	}

	public boolean placeBet(Player player, int ammount, BetLocation betLocation)
	{
		if (players != null && betLocation != null && player != null)
		{
			Bet bet = player.createBet(ammount, betLocation);
			if (bet != null)
			{
				bets.add(bet);
				Log.I(logPrefix + "BET amount: " + ammount + " on: " + betLocation.toString() + " by: "
					+ player.getUsername());
			}
		}
		return false;
	}

	public class TableThread extends TimerTask
	{
		/** Het id van de table */
		private int Id;

		public TableThread(int Id)
		{
			this.Id = Id;
			logPrefix = "Table " + Id + ": ";
			Log.I(logPrefix + "Started!");
		}

		public void run()
		{
			lastWinners.clear();
			calculateWinners();
			calculateNextWinningNumber();
			clearBets();
			currentRound++;
		}

		private void calculateNextWinningNumber()
		{
			Random r = new Random();
			winningNumber = BetLocation.values()[r.nextInt(36)];
			Log.I(logPrefix + "Next winning number: " + winningNumber);
		}

		private void clearBets()
		{
			bets.clear();
		}

		private void calculateWinners()
		{
			if (players.size() > 0)
			{
				if (bets.size() > 0)
				{
					Log.I(logPrefix + "Calculating winners!");
					for (Bet bet : bets)
					{
						if (bet != null)
						{
							// Straight-up bet
							if (bet.getBetLocation().equals(winningNumber))
							{
								double amount = bet.getBetAmmount() * 36;

								Log.I(logPrefix + "WON [Straight-Up] [player:" +
									bet.getPlayer().getUsername() +
									"] amount: " + amount
									);
								bet.getPlayer().addMoney(amount);
								lastWinners.add(bet.getPlayer().getUsername());
							}

							// Check black
							if (bet.getBetLocation().equals(BetLocation.black))
							{
								for (int i : Bet.BlACK)
								{
									if (winningNumber.equals(BetLocation.values()[i]))
									{
										double amount = bet.getBetAmmount() * 2;

										Log.I(logPrefix + "WON [BLACK] [playerid:" +
											bet.getPlayer().getID() +
											"] amount: " + amount
											);
										bet.getPlayer().addMoney(amount);
										lastWinners.add(bet.getPlayer().getUsername());
									}
								}
							}

							// Check red
							if (bet.getBetLocation().equals(BetLocation.red))
							{
								for (int i : Bet.RED)
								{
									if (winningNumber.equals(BetLocation.values()[i]))
									{
										double amount = bet.getBetAmmount() * 2;

										Log.I(logPrefix + "WON [RED] [playerid:" +
											bet.getPlayer().getID() +
											"] amount: " + amount
											);
										bet.getPlayer().addMoney(amount);
										lastWinners.add(bet.getPlayer().getUsername());
									}
								}
							}

						}
					}
				}
				else
				{
					Log.I(logPrefix + "There were no bets placed");
				}
			}
		}
	}
}