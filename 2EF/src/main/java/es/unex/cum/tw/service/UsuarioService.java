package es.unex.cum.tw.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.tw.model.Usuario;

public class UsuarioService {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/tw";
    private final String jdbcUsername = "tw";
    private final String jdbcPassword = "tw2425";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el controlador JDBC de MySQL", e);
        }
    }

    public Usuario login(String email, String password) {
        String query = "SELECT * FROM Usuario WHERE email = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(resultSet.getInt("id"));
                    usuario.setNombre(resultSet.getString("nombre"));
                    usuario.setEmail(resultSet.getString("email"));
                    usuario.setRol(resultSet.getString("rol"));
                    return usuario;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Usuario> getAllUsuarios() {
        String query = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNombre(resultSet.getString("nombre"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setRol(resultSet.getString("rol"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public void addUsuario(Usuario usuario) {
        String query = "INSERT INTO Usuario (nombre, email, password, rol) VALUES (?, ?, ?, 'usuario')";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Validar que los valores no sean nulos antes de insertarlos
            if (usuario.getNombre() == null || usuario.getEmail() == null || usuario.getPassword() == null) {
                throw new IllegalArgumentException("Los valores de nombre, email y password no pueden ser nulos");
            }

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPassword());
            int rowsInserted = statement.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (rowsInserted == 0) {
                throw new SQLException("No se pudo insertar el usuario en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al insertar el usuario en la base de datos: " + e.getMessage());
        }
    }

    public void addUsuarios(List<Usuario> usuarios) {
        String query = "INSERT INTO Usuario (nombre, email, password, rol) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (Usuario usuario : usuarios) {
                // Validar que los valores no sean nulos antes de insertarlos
                if (usuario.getNombre() == null || usuario.getEmail() == null || usuario.getPassword() == null || usuario.getRol() == null) {
                    throw new IllegalArgumentException("Los valores de nombre, email, password y rol no pueden ser nulos");
                }

                statement.setString(1, usuario.getNombre());
                statement.setString(2, usuario.getEmail());
                statement.setString(3, usuario.getPassword());
                statement.setString(4, usuario.getRol());
                statement.addBatch(); // Añadir al batch
            }
            statement.executeBatch(); // Ejecutar el batch
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean usuarioExiste(String email) {
        String query = "SELECT COUNT(*) FROM Usuario WHERE email = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0; // Si el conteo es mayor a 0, el usuario ya existe
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void updateUsuario(Usuario usuario) {
        String query = "UPDATE Usuario SET nombre = ?, email = ?, password = ?, rol = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getEmail());
            statement.setString(3, usuario.getPassword());
            statement.setString(4, usuario.getRol());
            statement.setInt(5, usuario.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUsuario(int id) {
        String query = "DELETE FROM Usuario WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
