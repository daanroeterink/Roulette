package nl.saxion.tjksoftware.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import nl.saxion.tjksoftware.models.Casino;
import nl.saxion.tjksoftware.models.Table;


@Path("/tables")
public class TablesResource {
	
	public TablesResource() {
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Table> getTables() {
		return Casino.getInstance().getTables();
	}
}
