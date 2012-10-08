package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

import nl.saxion.tjksoftware.models.Bet.BetLocation;

@XmlRootElement
public class Casino {

	private static Casino casino;

	private List<Table> tables;
	private List<Player> players;

	public static Casino getInstance() {
		if (casino == null) {
			casino = new Casino();
		}
		return casino;
	}

	public Casino() {
		tables = new ArrayList<Table>();
		players = new ArrayList<Player>();
		tables.add(new Table(tables.size() + 1));
		tables.add(new Table(tables.size() + 1));
		tables.add(new Table(tables.size() + 1));
		tables.get(0).addPlayer(new Player());
		tables.get(0).getPlayers().get(0).setMoney(5000);
		tables.get(0).placeBet(tables.get(0).getPlayers().get(0), 1000,
				BetLocation.black);
	}

	public List<Table> getTables() {
		List<Table> returnTableList = new ArrayList<Table>();
		returnTableList.addAll(tables);
		return returnTableList;
	}

	public void setTafels(List<Table> tafels) {
		this.tables = tafels;
	}

	public List<Player> getPlayers() {
		List<Player> returnList = new ArrayList<Player>();
		returnList.addAll(players);
		return returnList;
	}

	public void setSpelers(List<Player> players) {
		this.players = players;
	}

	public List<Bet> getBets() {
		List<Bet> returnBets = new ArrayList<Bet>();

		if (tables != null)
			for (Table table : tables) {
				returnBets.addAll(table.getBets());
			}
		return returnBets;
	}

	public Table getTableWithID(int id) {
		for (Table table : tables) {
			if (table.getID() == id) {
				return table;
			}
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	public Player getPlayerWithID(int id) {
		for (Player player : players) {
			if (player.getID() == id) {
				return player;
			}
		}
		throw new WebApplicationException(Status.NOT_FOUND);
	}

	
	public boolean addUniquePlayer(Player player) {
		if (player != null) {
			for (Player pla : players) {
				if (player.equals(pla)) {
					throw new WebApplicationException(Status.CONFLICT);
				}
			}
			players.add(player);
			return true;
		}
		throw new WebApplicationException(Status.BAD_REQUEST);
	}
}
