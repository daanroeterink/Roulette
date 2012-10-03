package nl.saxion.tjksoftware.models;

import java.util.ArrayList;
import java.util.List;

public class Casino {

	private static Casino casino;
	
	private List<Table> tafels;
	private List<Player> spelers;

	public static Casino getInstance() {
		if (casino == null) {
			casino = new Casino();
		}
		return casino;
	}
	
	public Casino() {
		tafels = new ArrayList<Table>();
		spelers = new ArrayList<Player>();
	}
	

}
