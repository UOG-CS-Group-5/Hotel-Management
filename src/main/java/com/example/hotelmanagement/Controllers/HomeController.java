package com.example.hotelmanagement.Controllers;

import com.example.hotelmanagement.managers.CustomerManager;
import com.example.hotelmanagement.managers.ReservationManager;
import com.example.hotelmanagement.models.Customer;
import com.example.hotelmanagement.models.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class HomeController {

    @FXML private Label lblDate;
    @FXML private Label lblTotalCustomers;
    @FXML private Label lblTotalReservations;
    @FXML private Button btnLogout;

    private final CustomerManager customerManager = new CustomerManager();
    private final ReservationManager reservationManager = new ReservationManager();

    @FXML
    public void initialize() {
        // Show today's date
        LocalDate today = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        lblDate.setText("Today: " + today.format(fmt));

        // Load counts from DB
        List<Customer> customers = customerManager.getAllCustomers();
        List<Reservation> reservations = reservationManager.getAllReservations();

        lblTotalCustomers.setText(String.valueOf(customers.size()));
        lblTotalReservations.setText(String.valueOf(reservations.size()));
    }

    @FXML
    private void onLogout(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(
                    getClass().getResource("/com/example/hotelmanagement/Logon.fxml")
            );
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
