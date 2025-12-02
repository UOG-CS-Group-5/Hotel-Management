package com.example.hotelmanagement.Controllers;

import com.example.hotelmanagement.managers.CustomerManager;
import com.example.hotelmanagement.managers.ReservationManager;
import com.example.hotelmanagement.models.Customer;
import com.example.hotelmanagement.models.Reservation;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class HomeController {

    private final CustomerManager customerManager = new CustomerManager();
    private final ReservationManager reservationManager = new ReservationManager();

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTotalCustomers;

    @FXML
    private Label lblTotalReservations;

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("MMMM d, yyyy"); // e.g. December 2, 2025

    @FXML
    public void initialize() {
        // Set date when screen loads
        lblDate.setText("Today: " + LocalDate.now().format(dateFormatter));

        // Load counts from DB
        List<Customer> customers = customerManager.getAllCustomers();
        List<Reservation> reservations = reservationManager.getAllReservations();

        lblTotalCustomers.setText(String.valueOf(customers.size()));
        lblTotalReservations.setText(String.valueOf(reservations.size()));
    }
}
