<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.service.RutaService" %>
<%@ page import="es.unex.cum.tw.model.Ruta" %>
<%
    RutaService rutaService = new RutaService();
    List<Ruta> rutas = rutaService.getAllRutas();
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/script.js"></script>
    <style>
        .ruta-card {
            border: 1px solid #ccc;
            padding: 15px;
            margin-bottom: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .ruta-imagen {
            width: 100%;
            max-width: 300px;
            height: auto;
            display: block;
            margin: 0 auto 10px auto;
        }
    </style>
</head>
<body>
    <header>
        <jsp:include page="pages/Menu_NoAuth.jsp" />
    </header>
    <main>
        <section id="intro">
            <h1>Bienvenidos a Rutas Extremadura</h1>
            <p>Explora las mejores rutas de Extremadura. Descubre paisajes únicos, disfruta de la naturaleza y vive una experiencia inolvidable.</p>
        </section>
        <section id="rutas-destacadas">
            <h2>Rutas Destacadas</h2>
            <div class="rutas-container">
                <% for (Ruta ruta : rutas) { %>
                    <div class="ruta-card">
                        <img src="<%= request.getContextPath() + "/" + ruta.getPathImagen() %>" alt="Imagen de la ruta" class="ruta-imagen" />
                        <h3>Ruta: <%= ruta.getPathImagen() %></h3>
                        <p><strong>Dificultad:</strong> <%= ruta.getDificultad() %></p>
                        <p><strong>Distancia:</strong> <%= ruta.getMetros() %> metros</p>
                        <p><strong>Máximo de usuarios:</strong> <%= ruta.getMaximoUsuario() %></p>
                        <p><strong>Fecha de incorporación:</strong> <%= ruta.getFechaIncorporacion() %></p>
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