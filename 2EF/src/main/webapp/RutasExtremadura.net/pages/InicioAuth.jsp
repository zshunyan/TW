<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%
    boolean isAuthenticated = session != null && session.getAttribute("nombre") != null;
    if (!isAuthenticated) {
        response.sendRedirect("../pages/Login.jsp");
    }
    boolean isAdmin = "admin".equals(session.getAttribute("rol"));
%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Inicio Autenticado - Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <jsp:include page="Menu_Auth.jsp" />
    </header>
    <main>
        <h2>Bienvenido, <%= session.getAttribute("nombre") %>!</h2>
        <p>Explora las rutas y novedades disponibles.</p>
        <section id="rutas">
            <h3><a href="rutas.jsp">Ver Rutas</a></h3>
        </section>
        <section id="novedades">
            <h3><a href="novedades.jsp">Ver Novedades</a></h3>
        </section>
        <% if (isAdmin) { %>
        <section id="administracion">
            <h3><a href="admin.jsp">Panel de Administración</a></h3>
        </section>
        <% } %>
    </main>
    <footer>
        <p>&copy; 2025 Rutas Extremadura. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
