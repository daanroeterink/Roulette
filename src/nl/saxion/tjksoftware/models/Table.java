package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.tjksoftware.models.Bet.BetLocation;

public class Table
{
	private List<Player> players;
	private List<Bet> bets;
	private int ID;

	public Table()
	{
		players = new ArrayList<Player>();
		bets = new ArrayList<Bet>();
	}

	public boolean addPlayer(Player newPlayer) {
		if (players != null && newPlayer != null)
		{
			if (players.size() < 10)
			{
				players.add(newPlayer);
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean placeBet(Player player, int ammount, BetLocation betLocation ) {
		if(players != null && betLocation != null) {
			player.createBet(ammount, betLocation);
		}
		return false;
	}

}
