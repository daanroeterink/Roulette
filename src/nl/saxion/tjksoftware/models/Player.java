package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

import nl.saxion.tjksoftware.models.Bet.BetLocation;

public class Player {
	
	private List<Bet> bets;
	private double money;

	public Player() {
		bets = new ArrayList<Bet>();
	}
	
	public Bet createBet(int ammount, BetLocation betlocation) {
		if(betlocation != null && (money - ammount) < 0) {
		Bet bet = new Bet(this,ammount,betlocation);
		bets.add(bet);
		return bet;
		}
		return null;
	}
	
}
