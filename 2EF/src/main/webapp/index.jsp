<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session = request.getSession(false);
    if (session != null && session.getAttribute("nombre") != null) {
        // Usuario autenticado, redirigir a InicioAuth.html
        response.sendRedirect("RutasExtremadura.net/pages/InicioAuth.jsp");
    } else {
        // Usuario no autenticado, redirigir a RutaExtremadura.html
        response.sendRedirect("RutasExtremadura.net/RutaExtremadura.jsp");
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv='content-type' content='text/html; charset=iso-8859-1' />
<title>Ruta Extremadura</title>
<link rel='stylesheet' type='text/css' href='${pageContext.request.contextPath}/RutasExtremadura.net/css/style.css' />
</head>
<body>
    <jsp:include page="/RutasExtremadura.net/pages/Menu_NoAuth.jsp" />
    <div id='contenedor'>
        <jsp:include page="/RutasExtremadura.net/pages/Menu_Auth.jsp" />
        <div id='Content'>
        <% String mensaje = (String) request.getParameter("mensaje");
           if (mensaje == null) {
               mensaje = (String) request.getAttribute("mensaje");
           }
        %>        
            <h1><%= mensaje %></h1>
        </div>
    </div>
</body>
</html>
