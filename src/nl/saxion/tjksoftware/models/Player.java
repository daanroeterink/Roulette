package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import nl.saxion.tjksoftware.models.Bet.BetLocation;

@XmlRootElement
public class Player {

	private List<Bet> bets;
	private double money;
	private int ID;

	public Player() {
		bets = new ArrayList<Bet>();
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public List<Bet> getBets() {
		return bets;
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