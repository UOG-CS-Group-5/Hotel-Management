package com.example.hotelmanagement.Controllers;

import com.example.hotelmanagement.managers.ReservationManager;
import com.example.hotelmanagement.models.CustomerReservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Date;

public class SearchController {

    @FXML private TextField tfSearch;
    @FXML private Button btnSearch;
    @FXML private Button btnShowAll;

    @FXML private TableView<CustomerReservation> tblResults;

    @FXML private TableColumn<CustomerReservation, Integer> colReservationID;
    @FXML private TableColumn<CustomerReservation, Integer> colCustomerID;
    @FXML private TableColumn<CustomerReservation, String> colFullName;
    @FXML private TableColumn<CustomerReservation, Integer> colRoomID;
    @FXML private TableColumn<CustomerReservation, Date> colCheckIn;
    @FXML private TableColumn<CustomerReservation, Date> colCheckOut;
    @FXML private TableColumn<CustomerReservation, Double> colTotalCost;
    @FXML private TableColumn<CustomerReservation, Date> colReservationDate;

    private final ReservationManager reservationManager = new ReservationManager();
    private ObservableList<CustomerReservation> searchResults;

    @FXML
    public void initialize() {
        colReservationID.setCellValueFactory(new PropertyValueFactory<>("reservationID"));
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colFullName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colRoomID.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colCheckIn.setCellValueFactory(new PropertyValueFactory<>("checkInDate"));
        colCheckOut.setCellValueFactory(new PropertyValueFactory<>("checkOutDate"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
        colReservationDate.setCellValueFactory(new PropertyValueFactory<>("reservationDate"));

        loadAll();
    }

    private void loadAll() {
        searchResults = FXCollections.observableArrayList(
                reservationManager.getAllCustomerReservations()
        );
        tblResults.setItems(searchResults);
    }

    @FXML
    private void onSearch() {
        String query = tfSearch.getText().trim();

        if (query.isEmpty()) {
            loadAll();
            return;
        }

        // ID or Name detection
        try {
            int customerID = Integer.parseInt(query);
            searchResults = FXCollections.observableArrayList(
                    reservationManager.getCustomerReservationsByCustomerID(customerID)
            );
        } catch (NumberFormatException e) {
            searchResults = FXCollections.observableArrayList(
                    reservationManager.searchCustomerReservationsByName(query)
            );
        }

        tblResults.setItems(searchResults);
    }

    @FXML
    private void onShowAll() {
        tfSearch.clear();
        loadAll();
    }
}
