package es.unex.cum.tw.rest;

import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/Calculadora")

public class CalculadoraWS {

	//-----------------------------PathParam--------------------------------
	@GET
	@Path("{op}/{op1}/{op2}")// /add/5/10
	//@Path("{op}-{op1}-{op2}") // /add-5-10
	@Produces(MediaType.TEXT_PLAIN)
	//http://localhost:8080/PruebaRest/rest/Calculadora/mul/2/3
	public String operarP(@PathParam("op")String op, @PathParam("op1")float op1, @PathParam("op2")float op2) {
		if(op.equals("add")) {
			return String.valueOf(op1+op2);
		}else if(op.equals("sub")) {
			return String.valueOf(op1-op2);
		}else if(op.equals("mul")) {
			return String.valueOf(op1*op2);
		}else if(op.equals("div")) {
			if(op2==0) {
				return "El divisor no puede ser 0";
			}else return String.valueOf(op1/op2);
		}else {
			return "Operacion desconocida";
		}
	}
	
	//-----------------------------QueryParam--------------------------------

	@GET
	@Path("/q")
	@Produces(MediaType.TEXT_PLAIN)
	//http://localhost:8080/PruebaRest/rest/Calculadora/q?op=mul&op1=2&op2=3
	public String operarQ(
			@QueryParam(value="op")String op, 
			@QueryParam(value="op1")float op1, 
			@QueryParam(value="op2")float op2) {
		if(op.equals("add")) {
			return String.valueOf(op1+op2);
		}else if(op.equals("sub")) {
			return String.valueOf(op1-op2);
		}else if(op.equals("mul")) {
			return String.valueOf(op1*op2);
		}else if(op.equals("div")) {
			if(op2==0) {
				return "El divisor no puede ser 0";
			}else return String.valueOf(op1/op2);
		}else {
			return "Operacion desconocida";
		}
	}
	
	//-----------------------------FormParam--------------------------------

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	//http://localhost:8080/PruebaRest/rest/Calculadora/
	//Header: Content-Type –> application/x-www-form-urlencoded
	//Body: op=add&op1=2&op2=3
	public String operarF(
			@FormParam(value="op")String op, 
			@FormParam(value="op1")float op1, 
			@FormParam(value="op2")float op2){
		if(op.equals("add")) {
			return String.valueOf(op1+op2);
		}else if(op.equals("sub")) {
			return String.valueOf(op1-op2);
		}else if(op.equals("mul")) {
			return String.valueOf("Esto es form "+op1*op2);
		}else if(op.equals("div")) {
			if(op2==0) {
				return "El divisor no puede ser 0";
			}else return String.valueOf("Esto es form "+op1/op2);
		}else {
			return "Operacion desconocida";
		}
	}
	
	
	
}
