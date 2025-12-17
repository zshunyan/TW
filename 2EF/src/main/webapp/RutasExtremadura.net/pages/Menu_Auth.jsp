<nav>
    <ul>
        <li><a href="<%= request.getContextPath() %>/RutasExtremadura.net/pages/InicioAuth.jsp">Inicio</a></li>
        <li><a href="<%= request.getContextPath() %>/RutasExtremadura.net/pages/rutas.jsp">Rutas</a></li>
        <li><a href="<%= request.getContextPath() %>/RutasExtremadura.net/pages/novedades.jsp">Novedades</a></li>
        <li><a href="<%= request.getContextPath() %>/RutasExtremadura.net/pages/reservas.jsp">Reservas</a></li>
        <% if ("admin".equals(session.getAttribute("rol"))) { %>
            <li><a href="<%= request.getContextPath() %>/RutasExtremadura.net/pages/admin.jsp">Administracion</a></li>
        <% } %>
        <li><a href="<%= request.getContextPath() %>/Controller?action=logout">Cerrar Sesion</a></li>
    </ul>
</nav>
