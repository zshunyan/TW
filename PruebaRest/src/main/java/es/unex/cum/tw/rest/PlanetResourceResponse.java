package es.unex.cum.tw.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

//http://localhost:8080/PruebaRest/rest/Planets/1
@Path("/Planets")
public class PlanetResourceResponse {
	private AtomicInteger idCounter = new AtomicInteger();
	private static Map<Integer, Planet> pDB = new ConcurrentHashMap<Integer, Planet>() {
		{
			put(1, new Planet(1, "Tierra", (float) 540.0D));
			put(2, new Planet(2, "Marte", (float) 340.0D));
			put(3, new Planet(3, "Neptuno", (float) 240.0D));
		}
	};

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getPlanet(@PathParam("id") int id) {
		Planet found = pDB.get(id);
		if (found == null) {
			return Response.status(Status.BAD_REQUEST).entity("Planet not found").build();
		} else {
			return Response.ok(found).build();
		}
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getPlanets() {
		return Response.ok(pDB).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPlanet(Planet in) {
		this.pDB.put(in.getId(), in);
		// return Response.status(Status.CREATED).build();
		return Response.ok(pDB).build();
	}
	
//	@POST
//	public Response createPlanet2(
//			@FormParam(value="id")int id, 
//			@FormParam(value="name")String name, 
//			@FormParam(value="radius")String r){
//		this.pDB.put(in.getId(), in);
//		// return Response.status(Status.CREATED).build();
//		return Response.ok(pDB).build();
//	}

	@DELETE
	@Path("{id}")
	public Response deletePlanet(@PathParam("id") int id) {
		Planet found = pDB.get(id);
		if (found == null) {
			return Response.status(Status.BAD_REQUEST).entity("Planet not found").build();
		} else {
			pDB.remove(id);
			return Response.ok(found).build();
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePlanet(Planet in) {
		Planet found = pDB.get(in.getId());
		if (found == null) {
			return Response.status(Status.BAD_REQUEST).entity("Planet not found").build();
		} else {
			found.setName(in.getName());
			return Response.ok(found).build();
		}
	}
}