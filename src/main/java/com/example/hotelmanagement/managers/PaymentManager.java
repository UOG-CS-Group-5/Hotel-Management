package com.example.hotelmanagement.managers;

import com.example.hotelmanagement.database.DatabaseConnection;
import com.example.hotelmanagement.models.Payment;

import java.sql.*;
import java.sql.Date;
import java.util.*;

// Handles customer payments (e.g. creating, deleting, searching for customer payments and relating info)
public class PaymentManager {
    // Adds new payment to database
    public void addPayment(int reservationID, Date paymentDate, double amountPaid, String paymentMethod) {
        String sql = "INSERT INTO Payments (reservationID, paymentDate, amountPaid, paymentMethod) VALUES (?, ?, ? ,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationID);
            statement.setDate(2, paymentDate);
            statement.setDouble(3, amountPaid);
            statement.setString(4, paymentMethod);
            statement.executeUpdate();
            System.out.println("==============================================");
            System.out.println("Payment added to database!");
            System.out.println("==============================================");
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Removes payment by paymentID
    public void deletePayment(int paymentID) {
        String sql = "DELETE FROM Payments WHERE paymentID = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, paymentID);
            int rows = statement.executeUpdate();
            if(rows > 0 ) {
                System.out.println("==============================================");
                System.out.println("Payment deleted successfully!");
                System.out.println("==============================================");
            }
            else {
                System.out.println("==============================================");
                System.out.println("No payment found with that ID.");
                System.out.println("==============================================");
            }
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Searches for payment by reservation ID, if not found return null
    public Payment searchPayment(int reservationID) {
        String sql = "SELECT * FROM Payments WHERE reservationID = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservationID);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return new Payment (
                        resultSet.getInt("paymentID"),
                        resultSet.getInt("reservationID"),
                        resultSet.getDate("paymentDate"),
                        resultSet.getDouble("amountPaid"),
                        resultSet.getString("paymentMethod")
                );
            }
        }catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    // Displays all payments
    public Payment displayAllPayments() {
        String sql = "SELECT * FROM Payments";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println("Payment ID: " + resultSet.getInt("paymentID"));
                System.out.println("Reservation ID: " + resultSet.getInt("reservationID"));
                System.out.println("Payment Date: " + resultSet.getDate("paymentDate"));
                System.out.println("Amount Paid: " + resultSet.getDouble("amountPaid"));
                System.out.println("Payment Method: " + resultSet.getString("paymentMethod"));
                System.out.println("----------------------------------------");
            }
            if(resultSet.next()) {
                return new Payment(
                        resultSet.getInt("paymentID"),
                        resultSet.getInt("reservationID"),
                        resultSet.getDate("paymentDate"),
                        resultSet.getDouble("amountPaid"),
                        resultSet.getString("paymentMethod")
                );
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    public void updatePayment(int paymentID, int reservationID, Date paymentDate,
                               double amountPaid, String paymentMethod) {
        String sql = "UPDATE Payments SET reservationID = ?, paymentDate = ?, amountPaid = ?, paymentMethod = ? " +
                "WHERE paymentID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, reservationID);
            statement.setDate(2, paymentDate);
            statement.setDouble(3, amountPaid);
            statement.setString(4, paymentMethod);
            statement.setInt(5, paymentID);

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println("Payment updated successfully");
            } else {
                System.out.println("No payment found with that ID");
            }

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
