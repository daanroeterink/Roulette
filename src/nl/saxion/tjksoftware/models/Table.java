package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import nl.saxion.tjksoftware.models.Bet.BetLocation;

@XmlRootElement
public class Table {

	private List<Player> players;
	private List<Bet> bets;
	private int ID;

	public Table(int ID)
	{
		players = new ArrayList<Player>();
		bets = new ArrayList<Bet>();
		this.ID = ID;
	}
	
	public Table() {
		
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Bet> getBets() {
		return bets;
	}

	public void setBets(List<Bet> bets) {
		this.bets = bets;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
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
		if(players != null && betLocation != null && player != null) {
			Bet bet = player.createBet(ammount, betLocation);
			bets.add(bet);
		}
		return false;
	}
}
