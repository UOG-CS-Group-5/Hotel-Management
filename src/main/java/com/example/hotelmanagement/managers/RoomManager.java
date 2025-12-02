package com.example.hotelmanagement.managers;

import com.example.hotelmanagement.database.DatabaseConnection;
import com.example.hotelmanagement.models.Room;

import java.sql.*;
import java.util.*;

public class RoomManager {
    // Add a new room
    public void addRoom(int roomID, String roonNumber, String roomType, double pricePerNight, boolean availability) {
        String sql = "INSERT INTO Rooms (RoomID, RoomNumber, RoomType, " +
                "PricePerNight, Availability) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomID);
            statement.setString(2, roonNumber);
            statement.setString(3, roomType);
            statement.setDouble(4, pricePerNight);
            statement.setBoolean(5, availability);
            statement.executeUpdate();
            System.out.println("==============================================");
            System.out.println("Room added to database!");
            System.out.println("==============================================");
        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }

    // Delete a room by ID
    public void deleteRoom(int roomID) {
        String sql = "DELETE FROM Rooms WHERE RoomID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomID);
            int rows = statement.executeUpdate();

            if (rows > 0) {
                System.out.println("==============================================");
                System.out.println("Room deleted successfully!");
                System.out.println("==============================================");
            } else {
                System.out.println("No room found with ID = " + roomID);
            }

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }
    }

    // Get all rooms from the database
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Rooms";

        try (Connection connection = DatabaseConnection.getConnection();
                Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                System.out.println("Room ID: " + resultSet.getInt("RoomID"));
                System.out.println("Room Number: " + resultSet.getString("RoomNumber"));
                System.out.println("Room Type: " + resultSet.getString("RoomType"));
                System.out.println("Price per Night: " + resultSet.getDouble("PricePerNight"));
                System.out.println("Availability: " + resultSet.getBoolean("Availability"));
                System.out.println("----------------------------------------");
            }

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }

        return rooms;
    }

    // Get a room by ID (returns null if not found)
    public Room getRoomByID(int roomID) {
        String sql = "SELECT * FROM Rooms WHERE RoomID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomID);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return new Room(
                        resultSet.getInt("RoomID"),
                        resultSet.getString("RoomNumber"),
                        resultSet.getString("RoomType"),
                        resultSet.getDouble("PricePerNight"),
                        resultSet.getBoolean("Availability")
                );
            }

        } catch (SQLException e) {
            System.err.println("Database Error: " + e.getMessage());
        }

        return null;
    }
}
