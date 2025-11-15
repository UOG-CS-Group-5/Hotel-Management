package com.example.hotelmanagement.managers;

import com.example.hotelmanagement.database.DatabaseConnection;
import com.example.hotelmanagement.models.Customer;
import com.example.hotelmanagement.models.Reservation;

import java.sql.*;
import java.util.*;

// Handles reservation management (e.g. creating, deleting, searching for reservations)
public class ReservationManager {
    // Adds new reservation for given customer
    public void addReservation(int customerID, int roomID, String checkInDate, String checkOutDate, double totalCost, String reservationDate) {
        // Reservation reservation = new Reservation(nextID++, customerID, roomID, checkInDate, checkOutDate, totalCost, reservationDate);

        String sql = "INSERT INTO Reservations (customerID, roomID, checkInDate, checkOutDate, totalCost, reservationDate) VALUES (?, ?, ?, ?, ?, ?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerID);
            statement.setInt(2, roomID);
            statement.setString(3, checkInDate);
            statement.setString(4, checkOutDate);
            statement.setDouble(5, totalCost);
            statement.setString(6, reservationDate);
            statement.executeUpdate();
            System.out.println("==============================================");
            System.out.println("Reservation added successfully!");
            System.out.println("==============================================");
        }   catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Removes reservation by reservationID
    public void deleteReservation(int reservationID) {
        String sql = "DELETE FROM Reservations WHERE ReservationID = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationID);
            int rows = statement.executeUpdate();
            if(rows > 0 ) {
                System.out.println("==============================================");
                System.out.println("Reservation deleted successfully!");
                System.out.println("==============================================");
            }
            else {
                System.out.println("==============================================");
                System.out.println("No reservation found with that ID.");
                System.out.println("==============================================");
            }
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Displays all reservations for a given customer ID
    public Reservation searchReservationByCustomerID(int customerID) {
        String sql = "SELECT * FROM Reservations WHERE CustomerID = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new Reservation(
                        resultSet.getInt("ReservationID"),
                        resultSet.getInt("CustomerID"),
                        resultSet.getInt("RoomID"),
                        resultSet.getString("CheckInDate"),
                        resultSet.getString("CheckOutDate"),
                        resultSet.getDouble("TotalCost"),
                        resultSet.getString("ReservationDate")
                );
            }
        }catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    // Displays all reservations
    public void displayAllReservations() {
        String sql = "SELECT * FROM Reservations";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println("Reservation ID: " + resultSet.getInt("ReservationID"));
                System.out.println("Customer ID: " + resultSet.getInt("CustomerID"));
                System.out.println("Room ID:" + resultSet.getInt("RoomID"));
                System.out.println("Check In Date: " + resultSet.getString("CheckInDate"));
                System.out.println("Check Out Date: " + resultSet.getString("CheckOutDate"));
                System.out.println("Total Cost: " + resultSet.getDouble("TotalCost"));
                System.out.println("Reservation Date: " + resultSet.getString("ReservationDate"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
