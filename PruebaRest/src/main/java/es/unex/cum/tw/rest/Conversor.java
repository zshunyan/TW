package es.unex.cum.tw.rest;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

//http://localhost:8080/PruebaRest/rest/Conversores

@Path("/Conversores")
public class Conversor {
	@GET
	@Path("/C2F")
	@Produces(MediaType.TEXT_PLAIN)
	//localhost:8080/PruebaRest/rest/Conversores/C2F
	//Accept text/plain
	public String convertC2F() {
		Double celsius = 36.8;
		return String.valueOf( ((celsius * 9) / 5) + 32);
	}
	@GET
	@Path("/F2C")
	@Produces(MediaType.TEXT_PLAIN)
	//localhost:8080/PruebaRest/rest/Conversores/F2C
	public String convertF2C() {
		Double fahrenheit = 98.24;
		return String.valueOf((fahrenheit - 32) * 5 / 9);
	}
	
	
	@GET
	@Path("/C2F/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	//localhost:8080/PruebaRest/rest/Conversores/C2F/100
	public String convertC2F(@PathParam("id")double c) {
		Double celsius = c;
		return String.valueOf( ((celsius * 9) / 5) + 32);
	}
	@GET
	@Path("/F2C/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	//localhost:8080/PruebaRest/rest/Conversores/F2C/100
	public String convertF2C(@PathParam("id")double c) {
		Double fahrenheit = c;
		return String.valueOf((fahrenheit - 32) * 5 / 9);
	}
	
	//con jason
	//Accept application/json
	@GET
	@Path("/C2FJSON/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//localhost:8080/PruebaRest/rest/Conversores/C2FJSON/100
	public String convertC2FJSON(@PathParam("id")double c) {
		Double celsius = c;
		return String.valueOf( "J "+((celsius * 9) / 5) + 32);
	}
	@GET
	@Path("/F2CJSON/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	//localhost:8080/PruebaRest/rest/Conversores/F2CJSON/100
	public String convertF2CJSON(@PathParam("id")double c) {
		Double fahrenheit = c;
		return String.valueOf("J "+(fahrenheit - 32) * 5 / 9);
	}
	
	@GET
	@Path("/M2P/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String M2Pul(@PathParam("id")double c) {
		return String.valueOf(c*39.3701);
	}
	
	@GET
	@Path("/P2M/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String Pul2M(@PathParam("id")double c) {
		return String.valueOf(c*0.0254);
	}
	
	//--------------------------QueryParam
	@GET
	@Path("/M2P/q")
	@Produces(MediaType.TEXT_PLAIN)
	//http://localhost:8080/PruebaRest/rest/Conversores/M2P/q?c=5
	public String M2PulB(@QueryParam(value="c")double c) {
		return String.valueOf(c*39.3701);
	}
	
	@GET
	@Path("/P2M/q")
	@Produces(MediaType.TEXT_PLAIN)
	//http://localhost:8080/PruebaRest/rest/Conversores/P2M/q?c=5
	public String Pul2MB(@PathParam(value="c")double c) {
		return String.valueOf(c*0.0254);
	}
	
	//---------------------------FormParam
	@POST
	@Path("/M2P/")
	@Produces(MediaType.TEXT_PLAIN)
	//http://localhost:8080/PruebaRest/rest/Conversores/M2P/
	//Accept text/plain
	//Header: Content-Type –> application/x-www-form-urlencoded
	//En body poner c=5
	public String M2PulC(@FormParam(value="c")double c) {//el value c tiene que llamar igual que en formulario
		return String.valueOf("Esto es formParam "+ c*39.3701);
	}
	
	
}
