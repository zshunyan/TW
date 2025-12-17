<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registro - Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <jsp:include page="Menu_NoAuth.jsp" />
    </header>
    <main>
        <h2>Registro</h2>
        <% String mensaje = request.getParameter("mensaje"); %>
        <% if (mensaje != null) { %>
            <p style="color: red;"><%= mensaje %></p>
        <% } %>
        <form action="<%= request.getContextPath() %>/Controller" method="post">
            <label>Nombre:</label>
            <input type="text" name="nombre" required /><br />
            <label>Email:</label>
            <input type="email" name="email" required /><br />
            <label>Contraseña:</label>
            <input type="password" name="password" required /><br />
            <input type="submit" value="Registrar" />
            <input type="hidden" name="action" value="UsuarioRegistro" />
        </form>
    </main>
</body>
</html>
