<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Rutas Extremadura</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/RutasExtremadura.net/css/style.css">
</head>
<body>
    <header>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/RutasExtremadura.net/RutaExtremadura.html">Inicio</a></li>
                <li><a href="${pageContext.request.contextPath}/RutasExtremadura.net/pages/rutas.html">Rutas</a></li>
                <li><a href="${pageContext.request.contextPath}/RutasExtremadura.net/pages/novedades.html">Novedades</a></li>
                <li><a href="${pageContext.request.contextPath}/RutasExtremadura.net/pages/reservas.html">Reservas</a></li>
                <li><a href="${pageContext.request.contextPath}/RutasExtremadura.net/pages/historico.html">Histórico de Puntuaciones</a></li>
                <li><a href="${pageContext.request.contextPath}/RutasExtremadura.net/pages/conecta4.html">Conecta4</a></li>
                <li><a href="${pageContext.request.contextPath}/RutasExtremadura.net/pages/bibliografia.html">Bibliografía</a></li>
                <% if (session.getAttribute("nombre") != null) { %>
                    <li><a href="${pageContext.request.contextPath}/Controller?action=logout">Cerrar sesión</a></li>
                <% } else { %>
                    <li><a href="${pageContext.request.contextPath}/RutasExtremadura.net/pages/login.jsp">Iniciar sesión</a></li>
                <% } %>
            </ul>
        </nav>
    </header>
</body>
</html>
