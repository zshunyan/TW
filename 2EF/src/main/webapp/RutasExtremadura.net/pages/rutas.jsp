<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.service.RutaService" %>
<%@ page import="es.unex.cum.tw.service.ReservaService" %>
<%@ page import="es.unex.cum.tw.service.ValoracionService" %>
<%@ page import="es.unex.cum.tw.model.Ruta" %>
<%@ page import="es.unex.cum.tw.model.Reserva" %>
<%
    RutaService rutaService = new RutaService();
    ReservaService reservaService = new ReservaService();
    ValoracionService valoracionService = new ValoracionService();

    List<Ruta> rutas = rutaService.getAllRutas();
    List<Reserva> reservas = null;

    // Determinar si el usuario está autenticado
    boolean isAuthenticated = session != null && session.getAttribute("nombre") != null;
    String menu = isAuthenticated ? "Menu_Auth.jsp" : "Menu_NoAuth.jsp";

    // Si está autenticado, obtener las reservas del usuario
    if (isAuthenticated) {
        int idUsuario = (Integer) session.getAttribute("idUsuario");
        reservas = reservaService.getReservasByUsuario(idUsuario);
    }
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Rutas - Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <style>
        .ruta {
            border: 1px solid #ccc;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .ruta img {
            width: 100%;
            max-width: 300px;
            height: auto;
            display: block;
            margin: 0 auto 10px auto;
        }
        .ruta-list {
            display: flex;
            flex-wrap: wrap;
            gap: 20px;
        }
        .ruta {
            flex: 1 1 calc(33.333% - 20px);
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <header>
        <jsp:include page="<%= menu %>" />
    </header>
    <main>
        <section id="rutas">
            <h2>Rutas en Extremadura</h2>
            <div class="ruta-list">
                <% for (Ruta ruta : rutas) { %>
                <div class="ruta">
                    <h3>Ruta: <%= ruta.getPathImagen() %></h3>
                    <img src="<%= request.getContextPath() + "/" + ruta.getPathImagen() %>" alt="Imagen de la ruta" />
                    <p><strong>Dificultad:</strong> <%= ruta.getDificultad() %></p>
                    <p><strong>Distancia:</strong> <%= ruta.getMetros() %> metros</p>
                    <p><strong>Valoración Media:</strong> <%= valoracionService.getPuntuacionMedia(ruta.getIdRuta()) %></p>
                    <% if (isAuthenticated) { %>
                        <% boolean tieneReserva = reservas.stream().anyMatch(reserva -> reserva.getIdRuta() == ruta.getIdRuta()); %>
                        <% if (tieneReserva) { %>
                            <form action="<%= request.getContextPath() %>/Controller" method="post">
                                <label for="puntuacion-<%= ruta.getIdRuta() %>">Añadir Valoración (1-5):</label>
                                <input type="number" id="puntuacion-<%= ruta.getIdRuta() %>" name="puntuacion" min="1" max="5" required />
                                <label for="comentario-<%= ruta.getIdRuta() %>">Comentario:</label>
                                <textarea id="comentario-<%= ruta.getIdRuta() %>" name="comentario" required></textarea>
                                <input type="hidden" name="idRuta" value="<%= ruta.getIdRuta() %>" />
                                <input type="hidden" name="action" value="AñadirValoracion" />
                                <input type="submit" value="Enviar Valoración" />
                            </form>
                        <% } %>
                    <% } %>
                </div>
                <% } %>
            </div>
        </section>
    </main>
    <footer>
        <p>&copy; 2025 Rutas Extremadura. Todos los derechos reservados.</p>
    </footer>
</body>
</html>