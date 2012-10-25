package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import nl.saxion.tjksoftware.models.Bet.BetLocation;

@XmlRootElement
public class Player
{

	private List<Bet> bets;

	private double money;

	private int ID;

	private String username, password;

	private String accessToken;

	public Player()
	{
		bets = new ArrayList<Bet>();
	}

	public String getAccessToken()
	{
		return accessToken;
	}

	public void setAccessToken(String accessToken)
	{
		this.accessToken = accessToken;
		System.out.println();
	}

	public double getMoney()
	{
		return money;
	}

	public void setMoney(double money)
	{
		this.money = money;
	}

	public int getID()
	{
		return ID;
	}

	public void setID(int iD)
	{
		ID = iD;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public Bet createBet(int ammount, BetLocation betlocation)
	{
		if (betlocation != null && (money - ammount) > 0)
		{
			Bet bet = new Bet(this, ammount, betlocation);
			bets.add(bet);
			return bet;
		}
		return null;
	}

	public String createAccessToken()
	{
		if (accessToken == null || accessToken == "")
		{
			accessToken = UUID.randomUUID().toString();
		}
		return accessToken.toString();
	}

	public void calculateHashCode()
	{
		createAccessToken();
	}

	@Override
	public boolean equals(Object other)
	{
		if (other instanceof Player)
		{
			if (other != null)
				return this.username.equals(((Player) other).username);
		}
		return false;
	}
}