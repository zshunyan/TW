<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" session="true"%>
<%@ page import="java.util.List" %>
<%@ page import="es.unex.cum.tw.service.UsuarioService" %>
<%@ page import="es.unex.cum.tw.model.Usuario" %>
<%
    UsuarioService usuarioService = new UsuarioService();
    List<Usuario> usuarios = usuarioService.getAllUsuarios();

    boolean isAdmin = session != null && "admin".equals(session.getAttribute("rol"));
    if (!isAdmin) {
        response.sendRedirect("Login.jsp?mensaje=Acceso denegado");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Administración - Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>
    <header>
        <jsp:include page="Menu_Auth.jsp" />
    </header>
    <main>
        <h2>Panel de Administración</h2>
        <section id="usuarios">
            <h3>Gestión de Usuarios</h3>
            <form action="<%= request.getContextPath() %>/Controller" method="post">
                <label>Nombre:</label>
                <input type="text" name="nombre" required />
                <label>Email:</label>
                <input type="email" name="email" required />
                <label>Contraseña:</label>
                <input type="password" name="password" required />
                <label>Rol:</label>
                <select name="rol">
                    <option value="usuario">Usuario</option>
                    <option value="admin">Administrador</option>
                </select>
                <input type="hidden" name="action" value="CrearUsuario" />
                <input type="submit" value="Crear Usuario" />
            </form>
            <h4>Lista de Usuarios</h4>
            <ul>
                <% for (Usuario usuario : usuarios) { %>
                    <li>
                        <%= usuario.getNombre() %> - <%= usuario.getEmail() %> - <%= usuario.getRol() %>
                        <form action="<%= request.getContextPath() %>/Controller" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="EliminarUsuario" />
                            <input type="hidden" name="idUsuario" value="<%= usuario.getId() %>" />
                            <input type="submit" value="Eliminar" />
                        </form>
                    </li>
                <% } %>
            </ul>
        </section>
        <section id="rutas">
            <h3>Subir Nueva Ruta</h3>
            <form action="<%= request.getContextPath() %>/Controller" method="post" enctype="multipart/form-data">
                <label>Imagen:</label>
                <input type="file" name="imagen" required />
                <label>Fecha de Incorporación:</label>
                <input type="date" name="fechaIncorporacion" required />
                <label>Máximo de Usuarios:</label>
                <input type="number" name="maximoUsuario" required />
                <label>Dificultad:</label>
                <input type="number" name="dificultad" min="1" max="5" required />
                <label>Distancia (metros):</label>
                <input type="number" name="metros" required />
                <input type="hidden" name="action" value="SubirRuta" />
                <input type="submit" value="Subir Ruta" />
            </form>
        </section>
    </main>
    <footer>
        <p>&copy; 2025 Rutas Extremadura. Todos los derechos reservados.</p>
    </footer>
</body>
</html>
