<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page import="es.unex.cum.tw.service.RutaService" %>
<%@ page import="es.unex.cum.tw.model.Ruta" %>
<%
    // Verificar si el usuario está autenticado
    boolean isAuthenticated = session != null && session.getAttribute("nombre") != null;

    RutaService rutaService = new RutaService();
    List<Ruta> rutas = rutaService.getAllRutas();

    // Obtener el criterio de ordenación seleccionado por el usuario
    String orden = request.getParameter("orden");
    if (orden == null) {
        orden = "ultimas"; // Por defecto, mostrar las últimas 5 rutas añadidas
    }

    List<Ruta> rutasOrdenadas;
    List<Ruta> restantesRutas = null; // Para manejar las rutas restantes si se selecciona "ultimas"
    switch (orden) {
        case "dificultad":
            rutasOrdenadas = rutas.stream()
                                  .sorted((r1, r2) -> Integer.compare(r1.getDificultad(), r2.getDificultad()))
                                  .collect(Collectors.toList());
            break;
        case "distancia":
            rutasOrdenadas = rutas.stream()
                                  .sorted((r1, r2) -> Integer.compare(r1.getMetros(), r2.getMetros()))
                                  .collect(Collectors.toList());
            break;
        case "ultimas":
        default:
            rutasOrdenadas = rutas.stream()
                                  .sorted((r1, r2) -> r2.getFechaIncorporacion().compareTo(r1.getFechaIncorporacion()))
                                  .limit(5)
                                  .collect(Collectors.toList());
            restantesRutas = rutas.stream()
                                   .sorted((r1, r2) -> r2.getFechaIncorporacion().compareTo(r1.getFechaIncorporacion()))
                                   .skip(5)
                                   .collect(Collectors.toList());
            break;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Novedades - Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
    <style>
        .ruta-card {
            border: 1px solid #ccc;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .ruta-card img {
            width: 100%;
            max-width: 300px;
            height: auto;
            display: block;
            margin: 0 auto 10px auto;
        }
        .novedades-header {
            font-weight: bold;
            font-size: 1.5em;
            text-align: center;
        }
    </style>
</head>
<body>
    <header>
        <%-- Incluir el menú correspondiente según el estado de autenticación --%>
        <% if (isAuthenticated) { %>
            <jsp:include page="Menu_Auth.jsp" />
        <% } else { %>
            <jsp:include page="Menu_NoAuth.jsp" />
        <% } %>
    </header>
    <main>
        <section id="novedades">
            <h2>Novedades</h2>
            <form method="get" action="novedades.jsp">
                <label for="orden">Ordenar por:</label>
                <select name="orden" id="orden" onchange="this.form.submit()">
                    <option value="ultimas" <%= "ultimas".equals(orden) ? "selected" : "" %>>Últimas añadidas</option>
                    <option value="dificultad" <%= "dificultad".equals(orden) ? "selected" : "" %>>Dificultad</option>
                    <option value="distancia" <%= "distancia".equals(orden) ? "selected" : "" %>>Distancia</option>
                </select>
            </form>
            <div class="ruta-list">
                <% if ("ultimas".equals(orden)) { %>
                    <h3>Últimas 5 Rutas Añadidas</h3>
                    <% for (Ruta ruta : rutasOrdenadas) { %>
                        <div class="ruta-card">
                            <img src="<%= request.getContextPath() + "/" + ruta.getPathImagen() %>" alt="Imagen de la ruta" class="ruta-imagen" />
                            <h3>Ruta: <%= ruta.getPathImagen() %></h3>
                            <p><strong>Dificultad:</strong> <%= ruta.getDificultad() %></p>
                            <p><strong>Distancia:</strong> <%= ruta.getMetros() %> metros</p>
                            <p><strong>Fecha de incorporación:</strong> <%= ruta.getFechaIncorporacion() %></p>
                        </div>
                    <% } %>
                    <% if (restantesRutas != null && !restantesRutas.isEmpty()) { %>
                        <hr />
                        <h3 class="novedades-header">-------------------------------------Novedades-------------------------------------</h3>
                        <% for (Ruta ruta : restantesRutas) { %>
                            <div class="ruta-card">
                                <img src="<%= request.getContextPath() + "/" + ruta.getPathImagen() %>" alt="Imagen de la ruta" class="ruta-imagen" />
                                <h3>Ruta: <%= ruta.getPathImagen() %></h3>
                                <p><strong>Dificultad:</strong> <%= ruta.getDificultad() %></p>
                                <p><strong>Distancia:</strong> <%= ruta.getMetros() %> metros</p>
                                <p><strong>Fecha de incorporación:</strong> <%= ruta.getFechaIncorporacion() %></p>
                            </div>
                        <% } %>
                    <% } %>
                <% } else { %>
                    <% for (Ruta ruta : rutasOrdenadas) { %>
                        <div class="ruta-card">
                            <img src="<%= request.getContextPath() + "/" + ruta.getPathImagen() %>" alt="Imagen de la ruta" class="ruta-imagen" />
                            <h3>Ruta: <%= ruta.getPathImagen() %></h3>
                            <p><strong>Dificultad:</strong> <%= ruta.getDificultad() %></p>
                            <p><strong>Distancia:</strong> <%= ruta.getMetros() %> metros</p>
                            <p><strong>Fecha de incorporación:</strong> <%= ruta.getFechaIncorporacion() %></p>
                        </div>
                    <% } %>
                <% } %>
            </div>
                    </section>
    </main>
    <footer>
        <p>&copy; 2025 Rutas Extremadura. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
``` 
