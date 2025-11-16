package com.example.hotelmanagement.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
    @FXML
    private BorderPane rootPane;  // Only if you add fx:id to the borderpane (optional)

    @FXML
    private AnchorPane centerPane;

    private void loadCenter(String fxmlName) {
        try {
            Parent pane = FXMLLoader.load(getClass().getResource("/com/example/hotelmanagement/" + fxmlName));
            centerPane.getChildren().setAll(pane);
            AnchorPane.setTopAnchor(pane, 0.0);
            AnchorPane.setBottomAnchor(pane, 0.0);
            AnchorPane.setLeftAnchor(pane, 0.0);
            AnchorPane.setRightAnchor(pane, 0.0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() {
        loadCenter("Home.fxml");
    }

    @FXML
    private void openHome() {
        loadCenter("Home.fxml");
    }

    @FXML
    private void openCustomer() {
        loadCenter("Customer.fxml");
    }

    @FXML
    private void openReservation() {
        loadCenter("Reservation.fxml");
    }

    @FXML
    private void openSearch() {
        loadCenter("Search.fxml");
    }
}
