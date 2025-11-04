package com.example.hotelmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Creates connection to Microsoft Access database
public class DatabaseConnection {
    // path to database file
    private static final String DATABASEURL =
            "jdbc:ucanaccess://C:\\Users\\James\\IdeaProjects\\Hotel-Management\\src\\main\\java\\com\\example\\hotelmanagement\\database\\HotelManagementSoftwareDatabase.accdb";
    // *Using Intellij
    // To get database file, right click DB file in the project explorer on the left
    // Select "Copy Path/Reference" and select "Absolute Path"
    // Replace after "jdbc:ucanaccess://" in the URL above

    // Returns a connection from the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASEURL);
    }
}
