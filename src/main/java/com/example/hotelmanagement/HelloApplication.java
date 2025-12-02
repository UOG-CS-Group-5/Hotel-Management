package com.example.hotelmanagement;

// Testing database connection
import java.sql.*;
public class HelloApplication {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:ucanaccess://C://Users//James//Documents//.School Stuff//Work//WMCRM.accdb";
        Connection con = DriverManager.getConnection(url);
        Statement s = con.createStatement();
        ResultSet test1 = s.executeQuery("Select * from Customer");
        ResultSet test2 = s.executeQuery("Select CustomerID, COUNT(*), Email From Customer Group By CustomerID Order By CustomerID");
        while(test1.next()) {
            System.out.println(test1.getString(1)+ " " + test1.getString(2)+ " " + test1.getString(3)+ " "+ test1.getString(4));
        }
        System.out.println("\n");
        while(test2.next()) {
            System.out.println(test2.getString(1)+ " " + test2.getString(2) + " " + test2.getString(3)); }
    }
}
