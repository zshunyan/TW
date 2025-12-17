package es.unex.cum.tw.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String uri = httpRequest.getRequestURI();

        // Permitir acceso sin autenticación a ciertas páginas
        if (uri.endsWith("Login.jsp") || uri.endsWith("Registro.jsp") || uri.endsWith("Menu_NoAuth.jsp")) {
            chain.doFilter(request, response); // Continuar con la solicitud
            return;
        }

        // Verificar si el usuario está autenticado
        if (httpRequest.getSession().getAttribute("nombre") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/RutasExtremadura.net/pages/Login.jsp");
        } else {
            chain.doFilter(request, response); // Continuar con la solicitud
        }
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}
