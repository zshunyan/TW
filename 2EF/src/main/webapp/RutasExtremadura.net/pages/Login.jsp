<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login - Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <jsp:include page="Menu_NoAuth.jsp" />
    </header>
    <main>
        <h2>Iniciar Sesión</h2>
        <% String mensaje = request.getParameter("mensaje"); %>
        <% if (mensaje != null) { %>
            <p style="color: red;"><%= mensaje %></p>
        <% } %>
        <form action="<%= request.getContextPath() %>/Controller" method="post">
            <label>Email:</label>
            <input type="email" name="email" required />
            <label>Contraseña:</label>
            <input type="password" name="password" required />
            <input type="hidden" name="action" value="UsuarioLogin" />
            <input type="submit" value="Iniciar Sesión" />
        </form>
        <p>¿No tienes cuenta? <a href="Registro.jsp">Regístrate aquí</a></p>
    </main>
</body>
</html>
