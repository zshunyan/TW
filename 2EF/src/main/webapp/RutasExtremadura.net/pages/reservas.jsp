<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.service.ReservaService" %>
<%@ page import="es.unex.cum.tw.service.RutaService" %>
<%@ page import="es.unex.cum.tw.model.Reserva" %>
<%@ page import="es.unex.cum.tw.model.Ruta" %>
<%
    if (session == null || session.getAttribute("nombre") == null) {
        response.sendRedirect("../pages/Login.jsp");
    }
    ReservaService reservaService = new ReservaService();
    RutaService rutaService = new RutaService();
    int idUsuario = (Integer) session.getAttribute("idUsuario");
    List<Reserva> reservas = reservaService.getReservasByUsuario(idUsuario); // Verifica que este método esté definido
    List<Ruta> rutas = rutaService.getAllRutas();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Reservas - Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <jsp:include page="Menu_Auth.jsp" />
    </header>
    <main>
        <section id="reservas">
            <h2>Reservas</h2>
            <form action="<%= request.getContextPath() %>/Controller" method="post">
                <label for="ruta">Ruta:</label>
                <select name="idRuta" id="ruta">
                    <% for (Ruta ruta : rutas) { %>
                        <option value="<%= ruta.getIdRuta() %>"><%= ruta.getPathImagen() %></option>
                    <% } %>
                </select>
                <label for="fecha">Fecha de Reserva:</label>
                <input type="date" id="fecha" name="fechaReserva" required />
                <input type="submit" value="Reservar" />
                <input type="hidden" name="action" value="ReservarRuta" />
            </form>
            <h3>Mis Reservas</h3>
            <ul>
                <% for (Reserva reserva : reservas) { %>
                    <li>
                        Ruta: <%= reserva.getIdRuta() %>, Fecha: <%= reserva.getFechaReserva() %>
                        <form action="<%= request.getContextPath() %>/Controller" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="AnularReserva" />
                            <input type="hidden" name="idReserva" value="<%= reserva.getIdReserva() %>" />
                            <input type="submit" value="Anular" />
                        </form>
                    </li>
                <% } %>
            </ul>
        </section>
    </main>
    <footer>
        <p>&copy; 2025 Rutas Extremadura. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
