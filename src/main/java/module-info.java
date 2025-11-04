module com.example.hotelmanagement {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.hotelmanagement to javafx.fxml;
    exports com.example.hotelmanagement;
    exports com.example.hotelmanagement.database;
    opens com.example.hotelmanagement.database to javafx.fxml;
}