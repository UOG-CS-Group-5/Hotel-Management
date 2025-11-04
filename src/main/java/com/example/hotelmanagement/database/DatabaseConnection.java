package com.example.hotelmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Creates connection to Microsoft Access database
public class DatabaseConnection {
    // path to database file
    private static final String DATABASEURL =
            "jdbc:ucanaccess://C://Users//James//Documents//.School Stuff//Work//HotelManagementSoftwareDatabase.accdb";

    // Returns a connection from the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASEURL);
    }
}
