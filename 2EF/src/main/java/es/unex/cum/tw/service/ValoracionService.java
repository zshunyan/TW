package es.unex.cum.tw.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.tw.model.Valoracion;

public class ValoracionService {
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

    public List<Valoracion> getAllValoraciones() {
        String query = "SELECT * FROM Valoraciones";
        List<Valoracion> valoraciones = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Valoracion valoracion = new Valoracion();
                valoracion.setIdValoracion(resultSet.getInt("idValoracion"));
                valoracion.setIdUsuario(resultSet.getInt("idUsuario"));
                valoracion.setIdRuta(resultSet.getInt("idRuta"));
                valoracion.setPuntuacion(resultSet.getInt("puntuacion"));
                valoracion.setComentario(resultSet.getString("comentario"));
                valoraciones.add(valoracion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las valoraciones: " + e.getMessage());
        }
        return valoraciones;
    }

    public void addValoracion(Valoracion valoracion) {
        String query = "INSERT INTO Valoraciones (idUsuario, idRuta, puntuacion, comentario) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Validar que los valores no sean nulos antes de insertarlos
            if (valoracion.getIdUsuario() == 0 || valoracion.getIdRuta() == 0 || valoracion.getPuntuacion() < 1 || valoracion.getPuntuacion() > 5) {
                throw new IllegalArgumentException("Los valores de idUsuario, idRuta y puntuación deben ser válidos");
            }

            statement.setInt(1, valoracion.getIdUsuario());
            statement.setInt(2, valoracion.getIdRuta());
            statement.setInt(3, valoracion.getPuntuacion());
            statement.setString(4, valoracion.getComentario());
            int rowsInserted = statement.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (rowsInserted == 0) {
                throw new SQLException("No se pudo insertar la valoración en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al agregar la valoración: " + e.getMessage());
        }
    }

    public void deleteValoracion(int idValoracion) {
        String query = "DELETE FROM Valoraciones WHERE idValoracion = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idValoracion);
            int rowsDeleted = statement.executeUpdate();

            // Verificar si la eliminación fue exitosa
            if (rowsDeleted == 0) {
                throw new SQLException("No se encontró la valoración con idValoracion = " + idValoracion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la valoración: " + e.getMessage());
        }
    }

    public double getPuntuacionMedia(int idRuta) {
        String query = "SELECT AVG(puntuacion) AS puntuacionMedia FROM Valoraciones WHERE idRuta = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idRuta);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getDouble("puntuacionMedia");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al calcular la puntuación media: " + e.getMessage());
        }
        return 0.0;
    }

    public List<Valoracion> getValoracionesByRuta(int idRuta) {
        String query = "SELECT * FROM Valoraciones WHERE idRuta = ?";
        List<Valoracion> valoraciones = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idRuta);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Valoracion valoracion = new Valoracion();
                    valoracion.setIdValoracion(resultSet.getInt("idValoracion"));
                    valoracion.setIdUsuario(resultSet.getInt("idUsuario"));
                    valoracion.setIdRuta(resultSet.getInt("idRuta"));
                    valoracion.setPuntuacion(resultSet.getInt("puntuacion"));
                    valoracion.setComentario(resultSet.getString("comentario"));
                    valoraciones.add(valoracion);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las valoraciones de la ruta: " + e.getMessage());
        }
        return valoraciones;
    }
}
