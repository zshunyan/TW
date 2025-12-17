package es.unex.cum.tw.rest;

import jakarta.ws.rs.DELETE;

import jakarta.ws.rs.GET;

import jakarta.ws.rs.POST;

import jakarta.ws.rs.Path;

import jakarta.ws.rs.PathParam;

import jakarta.ws.rs.Produces;

import jakarta.ws.rs.core.MediaType;

@Path("/Factorial")

public class Factorial {

	private static long resultado = 1;

	private static long valor = 1;

// Factorial (Get) → Devuelve el cálculo del factorial

	@GET

	@Produces(MediaType.TEXT_PLAIN)

	public long getFactorial() {

		factorial();

		return resultado;

	}

	@POST

	@Produces(MediaType.TEXT_PLAIN)

	@Path("/{id}")

// Factorial/{id} (Post)→ Añade el número para el cálculo del factorial

	public String addFactorial(@PathParam("id") int id) {

		valor = id;

		return "Todo ha ido bien";

	}

// Factorial (delete) → Reinicia el número del factorial

	@DELETE

	@Produces(MediaType.TEXT_PLAIN)

	public String reiniciar() {

		return "Reinicio Correcto";

	}

	public void factorial() {

		int factorial = 1;

		for (int i = 2; i <= valor; ++i) {

			factorial *= i;

		}

		resultado = factorial;

	}
	

}
