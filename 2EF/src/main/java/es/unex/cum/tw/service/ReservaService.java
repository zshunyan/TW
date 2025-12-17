package es.unex.cum.tw.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import es.unex.cum.tw.model.Reserva;

public class ReservaService {
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

    public List<Reserva> getAllReservas() {
        String query = "SELECT * FROM Reservas";
        List<Reserva> reservas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(resultSet.getInt("idReserva"));
                reserva.setIdUsuario(resultSet.getInt("idUsuario"));
                reserva.setIdRuta(resultSet.getInt("idRuta"));
                reserva.setFechaReserva(resultSet.getString("fechaReserva"));
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las reservas: " + e.getMessage());
        }
        return reservas;
    }

    public void addReserva(Reserva reserva) {
        String query = "INSERT INTO Reservas (idUsuario, idRuta, fechaReserva) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            // Validar que los valores no sean nulos antes de insertarlos
            if (reserva.getIdUsuario() == 0 || reserva.getIdRuta() == 0 || reserva.getFechaReserva() == null) {
                throw new IllegalArgumentException("Los valores de idUsuario, idRuta y fechaReserva no pueden ser nulos");
            }

            statement.setInt(1, reserva.getIdUsuario());
            statement.setInt(2, reserva.getIdRuta());
            statement.setString(3, reserva.getFechaReserva());
            int rowsInserted = statement.executeUpdate();

            // Verificar si la inserción fue exitosa
            if (rowsInserted == 0) {
                throw new SQLException("No se pudo insertar la reserva en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al agregar la reserva: " + e.getMessage());
        }
    }

    public void deleteReserva(int idReserva) {
        String query = "DELETE FROM Reservas WHERE idReserva = ?";
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idReserva);
            int rowsDeleted = statement.executeUpdate();

            // Verificar si la eliminación fue exitosa
            if (rowsDeleted == 0) {
                throw new SQLException("No se encontró la reserva con idReserva = " + idReserva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al eliminar la reserva: " + e.getMessage());
        }
    }

    public List<Reserva> getReservasByUsuario(int idUsuario) {
        String query = "SELECT * FROM Reservas WHERE idUsuario = ?";
        List<Reserva> reservas = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setIdReserva(resultSet.getInt("idReserva"));
                    reserva.setIdUsuario(resultSet.getInt("idUsuario"));
                    reserva.setIdRuta(resultSet.getInt("idRuta"));
                    reserva.setFechaReserva(resultSet.getString("fechaReserva"));
                    reservas.add(reserva);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al obtener las reservas del usuario: " + e.getMessage());
        }
        return reservas;
    }
}
