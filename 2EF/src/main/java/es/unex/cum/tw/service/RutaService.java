package es.unex.cum.tw.service;

import es.unex.cum.tw.model.Ruta;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RutaService {
    private String jdbcURL = "jdbc:mysql://localhost:3306/tw?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String jdbcUsername = "tw";
    private String jdbcPassword = "tw2425";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error al cargar el controlador JDBC de MySQL", e);
        }
    }

    public List<Ruta> getAllRutas() {
        String query = "SELECT * FROM Rutas";
        List<Ruta> rutas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Ruta ruta = new Ruta();
                ruta.setIdRuta(resultSet.getInt("idRuta"));
                ruta.setPathImagen(resultSet.getString("pathImagen"));
                ruta.setFechaIncorporacion(resultSet.getString("fechaIncorporacion"));
                ruta.setMaximoUsuario(resultSet.getInt("maximoUsuario"));
                ruta.setDificultad(resultSet.getInt("dificultad"));
                ruta.setMetros(resultSet.getInt("metros"));
                rutas.add(ruta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las rutas de la base de datos: " + e.getMessage());
        }
        return rutas;
    }

    public void addRuta(Ruta ruta) {
        String query = "INSERT INTO Rutas (pathImagen, fechaIncorporacion, maximoUsuario, dificultad, metros) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ruta.getPathImagen());
            statement.setString(2, ruta.getFechaIncorporacion());
            statement.setInt(3, ruta.getMaximoUsuario());
            statement.setInt(4, ruta.getDificultad());
            statement.setInt(5, ruta.getMetros());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRuta(Ruta ruta) {
        String query = "UPDATE Rutas SET pathImagen = ?, fechaIncorporacion = ?, maximoUsuario = ?, dificultad = ?, metros = ? WHERE idRuta = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, ruta.getPathImagen());
            statement.setString(2, ruta.getFechaIncorporacion());
            statement.setInt(3, ruta.getMaximoUsuario());
            statement.setInt(4, ruta.getDificultad());
            statement.setInt(5, ruta.getMetros());
            statement.setInt(6, ruta.getIdRuta());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRuta(int idRuta) {
        String query = "DELETE FROM Rutas WHERE idRuta = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idRuta);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


	public Ruta getRutaById(int idRuta) {
		String query = "SELECT * FROM Rutas WHERE idRuta = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idRuta);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Ruta ruta = new Ruta();
                    ruta.setIdRuta(resultSet.getInt("idRuta"));
                    ruta.setPathImagen(resultSet.getString("pathImagen"));
                    ruta.setFechaIncorporacion(resultSet.getString("fechaIncorporacion"));
                    ruta.setMaximoUsuario(resultSet.getInt("maximoUsuario"));
                    ruta.setDificultad(resultSet.getInt("dificultad"));
                    ruta.setMetros(resultSet.getInt("metros"));
                    return ruta;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener la ruta con idRuta = " + idRuta + ": " + e.getMessage());
        }
        return null;
	}
}
