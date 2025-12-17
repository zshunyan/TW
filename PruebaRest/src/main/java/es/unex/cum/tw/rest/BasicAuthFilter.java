package es.unex.cum.tw.rest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
/*
@Provider
public class BasicAuthFilter implements ContainerRequestFilter {
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authHeader = requestContext.getHeaderString("Authorization");
//Compruebo el tipo de autorización 
		if (authHeader == null || !authHeader.startsWith("Basic ")) {
			abortRequest(requestContext, "Falta el header Authorization o no es Basic Auth.");
			return;
		}
//Recojo los valores
		String base64Credentials = authHeader.substring("Basic ".length());
		String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
// Separar usuario y contraseña
		final String[] values = credentials.split(":", 2);
		if (values.length < 2) {
			abortRequest(requestContext, "Formato de credenciales inválido.");
			return;
		}
		String username = values[0];
		String password = values[1];
		if ((!username.equals("admin") || !password.equals("admin"))) {
			abortRequest(requestContext, "Credenciales incorrectas.");
		}
// Si pasa la autenticación, sigue hacia el recurso
	}

	private void abortRequest(ContainerRequestContext requestContext, String mensaje) {
		requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).entity(mensaje).build());
	}

	@Path("/datos")
	public class ServicioSeguro {
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public String obtenerDatos() {
			System.out.println("Hecho");
			return "{\"mensaje\": \"Acceso autorizado a los datos.\"}";
		}
	}
}
*/