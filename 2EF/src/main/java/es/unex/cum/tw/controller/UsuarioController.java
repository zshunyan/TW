package es.unex.cum.tw.controller;

import es.unex.cum.tw.model.Reserva;
import es.unex.cum.tw.model.Ruta;
import es.unex.cum.tw.model.Usuario;
import es.unex.cum.tw.model.Valoracion;
import es.unex.cum.tw.service.ReservaService;
import es.unex.cum.tw.service.RutaService;
import es.unex.cum.tw.service.UsuarioService;
import es.unex.cum.tw.service.ValoracionService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Controller")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2, // 2MB
    maxFileSize = 1024 * 1024 * 10,      // 10MB
    maxRequestSize = 1024 * 1024 * 50    // 50MB
)
public class UsuarioController extends HttpServlet {
    private final UsuarioService usuarioService = new UsuarioService();
    private final ReservaService reservaService = new ReservaService();
    private final ValoracionService valoracionService = new ValoracionService();
    private final RutaService rutaService = new RutaService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("logout".equals(action)) {
            // Invalidar la sesión
            request.getSession().invalidate();
            // Redirigir al usuario a la página de inicio no autenticado
            response.sendRedirect(request.getContextPath() + "/RutasExtremadura.net/RutaExtremadura.jsp");
        } else if ("ListarValoraciones".equals(action)) {
            // Manejar la acción de listar valoraciones de una ruta
            try {
                int idRuta = Integer.parseInt(request.getParameter("idRuta"));
                Ruta ruta = rutaService.getRutaById(idRuta);
                List<Valoracion> valoraciones = valoracionService.getValoracionesByRuta(idRuta);
                double puntuacionMedia = valoracionService.getPuntuacionMedia(idRuta);

                // Guardar los detalles de la ruta y las valoraciones en los atributos de la solicitud
                request.setAttribute("pathImagen", ruta.getPathImagen());
                request.setAttribute("dificultad", ruta.getDificultad());
                request.setAttribute("metros", ruta.getMetros());
                request.setAttribute("valoraciones", valoraciones);
                request.setAttribute("puntuacionMedia", puntuacionMedia);

                // Redirigir a la página de detalles de la ruta
                request.getRequestDispatcher("RutasExtremadura.net/pages/detallesRuta.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("RutasExtremadura.net/pages/rutas.jsp?mensaje=Error al cargar los detalles de la ruta");
            }
        } else {
            response.sendRedirect("RutasExtremadura.net/pages/Login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("UsuarioLogin".equals(action)) {
            // Capturar los valores enviados desde el formulario
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Validar que los valores no sean nulos o vacíos
            if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
                response.sendRedirect("RutasExtremadura.net/pages/Login.jsp?mensaje=Error: Todos los campos son obligatorios");
                return;
            }

            try {
                // Intentar iniciar sesión
                Usuario usuario = usuarioService.login(email.trim(), password.trim());
                if (usuario != null) {
                    // Credenciales válidas: guardar datos en la sesión
                    request.getSession().setAttribute("nombre", usuario.getNombre());
                    request.getSession().setAttribute("idUsuario", usuario.getId());
                    request.getSession().setAttribute("rol", usuario.getRol());
                    response.sendRedirect("RutasExtremadura.net/pages/InicioAuth.jsp");
                } else {
                    // Usuario no encontrado en la base de datos
                    response.sendRedirect("RutasExtremadura.net/pages/Login.jsp?mensaje=Error: Usuario o contraseña incorrectos");
                }
            } catch (Exception e) {
                e.printStackTrace();
                // Mostrar mensaje de error si ocurre un problema al consultar la base de datos
                response.sendRedirect("RutasExtremadura.net/pages/Login.jsp?mensaje=Error: Problema al verificar las credenciales");
            }
        } else if ("UsuarioRegistro".equals(action)) {
            // Capturar los valores enviados desde el formulario
            String nombre = request.getParameter("nombre");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

            // Validar que los valores no sean nulos o vacíos
            if (nombre == null || email == null || password == null ||
                nombre.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {
                response.sendRedirect("RutasExtremadura.net/pages/Registro.jsp?mensaje=Error: Todos los campos son obligatorios");
                return;
            }

            // Verificar si el usuario ya existe
            if (usuarioService.usuarioExiste(email.trim())) {
                response.sendRedirect("RutasExtremadura.net/pages/Login.jsp?mensaje=Error: El usuario ya existe");
                return;
            }

            try {
                // Crear el objeto Usuario y pasarlo al servicio
                Usuario usuario = new Usuario(nombre.trim(), email.trim(), password.trim(), "usuario");
                usuarioService.addUsuario(usuario);
                response.sendRedirect("RutasExtremadura.net/pages/Login.jsp?mensaje=Registro exitoso");
            } catch (RuntimeException e) {
                e.printStackTrace();
                response.sendRedirect("RutasExtremadura.net/pages/Registro.jsp?mensaje=Error: " + e.getMessage());
            }
        } else if ("InsertarUsuarios".equals(action)) {
            List<Usuario> usuarios = new ArrayList<>();
            usuarios.add(new Usuario("admin", "admin@example.com", "admin123", "admin"));
            //usuarios.add(new Usuario("user1", "user1@example.com", "user123", "usuario"));

            usuarioService.addUsuarios(usuarios);
            response.getWriter().write("Usuarios insertados correctamente");
        } else if ("ReservarRuta".equals(action)) {
            // Manejar la reserva de una ruta
            try {
                int idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
                int idRuta = Integer.parseInt(request.getParameter("idRuta"));
                String fechaReserva = request.getParameter("fechaReserva");

                Reserva reserva = new Reserva();
                reserva.setIdUsuario(idUsuario);
                reserva.setIdRuta(idRuta);
                reserva.setFechaReserva(fechaReserva);

                reservaService.addReserva(reserva);
                response.sendRedirect("RutasExtremadura.net/pages/reservas.jsp?mensaje=Reserva realizada con éxito");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("RutasExtremadura.net/pages/reservas.jsp?mensaje=Error al realizar la reserva");
            }
        } else if ("AnularReserva".equals(action)) {
            // Manejar la anulación de una reserva
            try {
                int idReserva = Integer.parseInt(request.getParameter("idReserva"));
                reservaService.deleteReserva(idReserva);
                response.sendRedirect("RutasExtremadura.net/pages/reservas.jsp?mensaje=Reserva anulada con éxito");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("RutasExtremadura.net/pages/reservas.jsp?mensaje=Error al anular la reserva");
            }
        } else if ("AñadirValoracion".equals(action)) {
            // Manejar la acción de añadir una valoración
            try {
                int idUsuario = (Integer) request.getSession().getAttribute("idUsuario");
                int idRuta = Integer.parseInt(request.getParameter("idRuta"));
                int puntuacion = Integer.parseInt(request.getParameter("puntuacion"));
                String comentario = request.getParameter("comentario");

                Valoracion valoracion = new Valoracion();
                valoracion.setIdUsuario(idUsuario);
                valoracion.setIdRuta(idRuta);
                valoracion.setPuntuacion(puntuacion);
                valoracion.setComentario(comentario);

                valoracionService.addValoracion(valoracion);
                response.sendRedirect("RutasExtremadura.net/pages/rutas.jsp?mensaje=Valoración añadida con éxito");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("RutasExtremadura.net/pages/rutas.jsp?mensaje=Error al añadir la valoración");
            }
        } else if ("CrearUsuario".equals(action)) {
            // Crear un nuevo usuario (solo admin)
            try {
                String nombre = request.getParameter("nombre");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                String rol = request.getParameter("rol");

                Usuario usuario = new Usuario(nombre, email, password, rol);
                usuarioService.addUsuario(usuario);
                response.sendRedirect("RutasExtremadura.net/pages/admin.jsp?mensaje=Usuario creado con éxito");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("RutasExtremadura.net/pages/admin.jsp?mensaje=Error al crear el usuario");
            }
        } else if ("EliminarUsuario".equals(action)) {
            // Eliminar un usuario (solo admin)
            try {
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                usuarioService.deleteUsuario(idUsuario);
                response.sendRedirect("RutasExtremadura.net/pages/admin.jsp?mensaje=Usuario eliminado con éxito");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("RutasExtremadura.net/pages/admin.jsp?mensaje=Error al eliminar el usuario");
            }
        } else if ("SubirRuta".equals(action)) {
            // Subir una nueva ruta (solo admin)
            try {
                // Obtener la imagen subida
                String fileName = request.getPart("imagen").getSubmittedFileName();
                String uploadPath = getServletContext().getRealPath("") + "RutasExtremadura.net/images";

                // Crear la carpeta si no existe
                java.io.File uploadDir = new java.io.File(uploadPath);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // Guardar la imagen en la carpeta
                request.getPart("imagen").write(uploadPath + "/" + fileName);

                // Obtener los demás datos del formulario
                String pathImagen = "RutasExtremadura.net/images/" + fileName;
                String fechaIncorporacion = request.getParameter("fechaIncorporacion");
                int maximoUsuario = Integer.parseInt(request.getParameter("maximoUsuario"));
                int dificultad = Integer.parseInt(request.getParameter("dificultad"));
                int metros = Integer.parseInt(request.getParameter("metros"));

                // Crear el objeto Ruta y guardarlo en la base de datos
                Ruta ruta = new Ruta();
                ruta.setPathImagen(pathImagen);
                ruta.setFechaIncorporacion(fechaIncorporacion);
                ruta.setMaximoUsuario(maximoUsuario);
                ruta.setDificultad(dificultad);
                ruta.setMetros(metros);

                rutaService.addRuta(ruta);
                response.sendRedirect("RutasExtremadura.net/pages/admin.jsp?mensaje=Ruta subida con éxito");
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect("RutasExtremadura.net/pages/admin.jsp?mensaje=Error al subir la ruta");
            }
        } else if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El parámetro 'action' es obligatorio.");
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida: " + action);
        }
    }

    private void manejarLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Usuario usuario = usuarioService.login(email, password);

        if (usuario != null) {
            request.getSession().setAttribute("nombre", usuario.getNombre());
            request.getSession().setAttribute("idUsuario", usuario.getId());
            response.sendRedirect("RutasExtremadura.net/pages/InicioAuth.jsp");
        } else {
            response.sendRedirect("RutasExtremadura.net/pages/Login.jsp?mensaje=Credenciales inválidas");
        }
    }

    private void manejarRegistro(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(password);

        usuarioService.addUsuario(usuario);
        response.sendRedirect("RutasExtremadura.net/pages/Login.jsp?mensaje=Registro exitoso");
    }
}
