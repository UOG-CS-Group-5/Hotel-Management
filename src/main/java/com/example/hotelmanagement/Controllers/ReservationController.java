package com.example.hotelmanagement.Controllers;

import com.example.hotelmanagement.managers.ReservationManager;
import com.example.hotelmanagement.models.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ReservationController {

    @FXML private TableView<Reservation> tblReservations;

    @FXML private TableColumn<Reservation, Integer> colReservationID;
    @FXML private TableColumn<Reservation, Integer> colCustomerID;
    @FXML private TableColumn<Reservation, Integer> colRoomID;
    @FXML private TableColumn<Reservation, String> colCheckIn;
    @FXML private TableColumn<Reservation, String> colCheckOut;
    @FXML private TableColumn<Reservation, Double> colTotalCost;
    @FXML private TableColumn<Reservation, String> colReservationDate;

    @FXML private TextField tfReservationID;
    @FXML private TextField tfCustomerID;
    @FXML private TextField tfRoomID;
    @FXML private TextField tfCheckIn;
    @FXML private TextField tfCheckOut;
    @FXML private TextField tfTotalCost;
    @FXML private TextField tfReservationDate;

    @FXML private Button btnAddUpdate;
    @FXML private Button btnDelete;

    private final ReservationManager manager = new ReservationManager();
    private ObservableList<Reservation> reservationList;

    @FXML
    public void initialize() {
        // Map columns to Reservation getters
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        loadReservations();

        // When user clicks a row in the table fill the form
        tblReservations.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSel, newSel) -> {
                    if (newSel != null) {
                        fillForm(newSel);
                    }
                }
        );

        // Usually the ID is not editable by user
        tfReservationID.setEditable(false);
    }

    private void loadReservations() {
        reservationList = FXCollections.observableArrayList(manager.getAllReservations());
        tblReservations.setItems(reservationList);
    }

    private void fillForm(Reservation r) {
        tfReservationID.setText(String.valueOf(r.getReservationID()));
        tfCustomerID.setText(String.valueOf(r.getCustomerID()));
        tfRoomID.setText(String.valueOf(r.getRoomID()));
        tfCheckIn.setText(r.getCheckInDate());
        tfCheckOut.setText(r.getCheckOutDate());
        tfTotalCost.setText(String.valueOf(r.getTotalCost()));
        tfReservationDate.setText(r.getReservationDate());
    }

    private void clearForm() {
        tfReservationID.clear();
        tfCustomerID.clear();
        tfRoomID.clear();
        tfCheckIn.clear();
        tfCheckOut.clear();
        tfTotalCost.clear();
        tfReservationDate.clear();
        tblReservations.getSelectionModel().clearSelection();
    }

    @FXML
    private void onAddUpdate() {
        // Simple validation
        if (tfCustomerID.getText().isBlank() || tfRoomID.getText().isBlank()) {
            System.out.println("Customer ID and Room ID are required");
            return;
        }

        try {
            int customerID = Integer.parseInt(tfCustomerID.getText().trim());
            int roomID = Integer.parseInt(tfRoomID.getText().trim());
            String checkIn = tfCheckIn.getText().trim();
            String checkOut = tfCheckOut.getText().trim();
            double totalCost = tfTotalCost.getText().isBlank()
                    ? 0.0
                    : Double.parseDouble(tfTotalCost.getText().trim());
            String reservationDate = tfReservationDate.getText().trim();

            if (tfReservationID.getText().isBlank()) {
                // Add new reservation
                manager.addReservation(customerID, roomID, checkIn, checkOut, totalCost, reservationDate);
            } else {
                // Update existing reservation
                int reservationID = Integer.parseInt(tfReservationID.getText().trim());
                manager.updateReservation(reservationID, customerID, roomID, checkIn, checkOut, totalCost, reservationDate);
            }

            loadReservations();
            clearForm();

        } catch (NumberFormatException e) {
            System.out.println("Invalid number value");
        }
    }

    @FXML
    private void onDelete() {
        Reservation selected = tblReservations.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No reservation selected");
            return;
        }

        manager.deleteReservation(selected.getReservationID());
        loadReservations();
        clearForm();
    }
}