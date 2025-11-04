package com.example.hotelmanagement.managers;

import com.example.hotelmanagement.database.DatabaseConnection;
import com.example.hotelmanagement.models.Customer;

import java.sql.*;
import java.util.*;

// Handles customer management (e.g. creating, deleting, searching for customer)
public class CustomerManager {
    // Adds new customer to database
    public void addCustomer(String fullName, String email, String phone, String address) {
        String sql = "INSERT INTO Customers (fullName, email, phone, address) VALUES (?, ?, ? ,?)";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, fullName);
            statement.setString(2, email);
            statement.setString(3, phone);
            statement.setString(4, address);
            statement.executeUpdate();
            System.out.println("==============================================");
            System.out.println("Customer added to database!");
            System.out.println("==============================================");
        } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Removes customer by customerID
    public void deleteCustomer(int customerID) {
        String sql = "DELETE FROM Customers WHERE customerID = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, customerID);
                int rows = statement.executeUpdate();
                if(rows > 0 ) {
                    System.out.println("==============================================");
                    System.out.println("Customer deleted successfully!");
                    System.out.println("==============================================");
                }
                else {
                    System.out.println("==============================================");
                    System.out.println("No customer found with that ID.");
                    System.out.println("==============================================");
                }
            } catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Searches for customer by name, if not found return null
    public Customer searchName(String fullName) {
        String sql = "SELECT * FROM Customers WHERE fullName = ?";
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, fullName);
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    return new Customer(
                            resultSet.getInt("customerID"),
                            resultSet.getString("fullName"),
                            resultSet.getString("email"),
                            resultSet.getString("phone"),
                            resultSet.getString("address")
                    );
                }
            }catch(SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return null;
    }

    // Displays all customers
    public void displayAllCustomers() {
        String sql = "SELECT * FROM Customers";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("customerID"));
                System.out.println("Full Name: " + resultSet.getString("fullName"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone Number: " + resultSet.getString("phone"));
                System.out.println("Address: " + resultSet.getString("address"));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
