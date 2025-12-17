package es.unex.cum.tw.rest;

import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

//localhost:8080/PruebaRest/rest/HolaMundo
@Path("/HolaMundo")
public class Hello {
// Texto
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String sayPlainTextHello() {
		return "Hello Jersey (TEXTO)";
	}

// XML
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public String sayXMLHello() {
		return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey (XML)" + "</hello>";
	}

// HTML
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String sayHtmlHello() {
		return "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" + "Hello Jersey (HTML)"
				+ "</body></h1>" + "</html> ";
	}

// JSON
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String sayJSONHello() {
		return "HelloWorld";
	}
// POST
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public String otroMetodo() {
		return "Prueba hecha en clase";
	}
	
	@GET
	@Path("/Personalizado")//por tener previamente el uri ya declarado, no puede tener dos con misma llamada, entonces se añade un path adicional
	@Produces(MediaType.APPLICATION_JSON)
	public String mensajePersonalizado (@PathParam("name")String s) {
		return "Hola "+s+" Mundo";
	}
	
	

}