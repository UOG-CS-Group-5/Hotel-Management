package com.example.hotelmanagement.managers;

import com.example.hotelmanagement.database.DatabaseConnection;
import com.example.hotelmanagement.models.Reservation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class ReservationManager {
    public void displayAllReservations() {
        String sql = "SELECT * FROM Reservations";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println("Reservation ID: " + resultSet.getInt("ReservationID"));
                System.out.println("Customer ID: " + resultSet.getInt("CustomerID"));
                System.out.println("Room ID: " + resultSet.getInt("RoomID"));
                System.out.println("Check In Date: " + resultSet.getDate("CheckInDate"));
                System.out.println("Check Out Date: " + resultSet.getDate("CheckOutDate"));
                System.out.println("Total Cost: " + resultSet.getDouble("TotalCost"));
                System.out.println("Reservation Date: " + resultSet.getDate("ReservationDate"));
                System.out.println("----------------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Adds new reservation for given customer
    public void addReservation(int customerID, int roomID,
                               Date checkInDate, Date checkOutDate,
                               double totalCost, Date reservationDate) {

        String sql = "INSERT INTO Reservations " +
                "(CustomerID, RoomID, CheckInDate, CheckOutDate, TotalCost, ReservationDate) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);
            statement.setInt(2, roomID);
            statement.setDate(3, checkInDate);
            statement.setDate(4, checkOutDate);
            statement.setDouble(5, totalCost);
            statement.setDate(6, reservationDate);

            int rows = statement.executeUpdate();
            System.out.println("Inserted reservations: " + rows);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Removes reservation by reservationID
    public void deleteReservation(int reservationID) {
        String sql = "DELETE FROM Reservations WHERE ReservationID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, reservationID);
            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("Reservation deleted successfully");
            } else {
                System.out.println("No reservation found with that ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Finds first reservation for a given customer ID
    public Reservation searchReservationByCustomerID(int customerID) {
        String sql = "SELECT * FROM Reservations WHERE CustomerID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                return new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("RoomID"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getDouble("TotalCost"),
                        rs.getDate("ReservationDate")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lists all reservations
    public List<Reservation> getAllReservations() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM Reservations";

        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                Reservation r = new Reservation(
                        rs.getInt("ReservationID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("RoomID"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getDouble("TotalCost"),
                        rs.getDate("ReservationDate")
                );
                list.add(r);
            }

            System.out.println("Loaded reservations from DB: " + list.size());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public void updateReservation(int reservationID, int customerID, int roomID,
                                  Date checkInDate, Date checkOutDate,
                                  double totalCost, Date reservationDate) {

        String sql = "UPDATE Reservations " +
                "SET CustomerID = ?, RoomID = ?, CheckInDate = ?, " +
                "CheckOutDate = ?, TotalCost = ?, ReservationDate = ? " +
                "WHERE ReservationID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, customerID);
            statement.setInt(2, roomID);
            statement.setDate(3, checkInDate);
            statement.setDate(4, checkOutDate);
            statement.setDouble(5, totalCost);
            statement.setDate(6, reservationDate);
            statement.setInt(7, reservationID);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Reservation updated successfully");
            } else {
                System.out.println("No reservation found with that ID");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
