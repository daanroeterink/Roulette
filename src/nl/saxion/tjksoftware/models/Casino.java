package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlRootElement;

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
	}

	public List<Table> getTables() {
		return tables;
	}

	public void setTafels(List<Table> tafels) {
		this.tables = tafels;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setSpelers(List<Player> players) {
		this.players = players;
	}

	public List<Bet> getBets() {
		List<Bet> returnBets = new ArrayList<Bet>();

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
}
