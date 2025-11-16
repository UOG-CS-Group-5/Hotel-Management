package com.example.hotelmanagement.Controllers;

import com.example.hotelmanagement.managers.ReservationManager;
import com.example.hotelmanagement.models.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

public class ReservationController {

    @FXML private TableView<Reservation> tblReservations;

    @FXML private TableColumn<Reservation, Integer> colReservationID;
    @FXML private TableColumn<Reservation, Integer> colCustomerID;
    @FXML private TableColumn<Reservation, Integer> colRoomID;
    @FXML private TableColumn<Reservation, Date> colCheckIn;
    @FXML private TableColumn<Reservation, Date> colCheckOut;
    @FXML private TableColumn<Reservation, Double> colTotalCost;
    @FXML private TableColumn<Reservation, Date> colReservationDate;

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
        tfCheckIn.setText(r.getCheckInDate() != null ? r.getCheckInDate().toString() : "");
        tfCheckOut.setText(r.getCheckOutDate() != null ? r.getCheckOutDate().toString() : "");
        tfTotalCost.setText(String.valueOf(r.getTotalCost()));
        tfReservationDate.setText(r.getReservationDate() != null ? r.getReservationDate().toString() : "");
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

            String checkInText = tfCheckIn.getText().trim();
            String checkOutText = tfCheckOut.getText().trim();
            String reservationDateText = tfReservationDate.getText().trim();

            // Dates must be in yyyy-MM-dd format for Date.valueOf()
            Date checkIn = checkInText.isEmpty() ? null : Date.valueOf(checkInText);
            Date checkOut = checkOutText.isEmpty() ? null : Date.valueOf(checkOutText);
            Date reservationDate = reservationDateText.isEmpty() ? null : Date.valueOf(reservationDateText);

            double totalCost = tfTotalCost.getText().isBlank()
                    ? 0.0
                    : Double.parseDouble(tfTotalCost.getText().trim());

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
        } catch (IllegalArgumentException e) {
            // This will catch bad date formats from Date.valueOf(...)
            System.out.println("Date must be in format yyyy-MM-dd");
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
