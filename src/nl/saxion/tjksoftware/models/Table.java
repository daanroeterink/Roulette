package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

public class Table {

	private List<Player> players;
	private List<Bet> bet;
	private int ID;
	
	public Table() {
		players = new ArrayList<Player>();
		bet = new ArrayList<Bet>();
	}
	
	public boolean addPlayer(Player newPlayer) {
		return false;
	}
	
	public boolean placeBet(Player player, Bet bet) {
		return false;
	}
}
