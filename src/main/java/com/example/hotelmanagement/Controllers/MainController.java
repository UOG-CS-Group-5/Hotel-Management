package com.example.hotelmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import com.example.hotelmanagement.Controllers.HomeController;

public class MainController {

    @FXML
    private BorderPane rootPane;  

    @FXML
    private AnchorPane centerPane;

    private void loadCenter(String fxmlName) {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/com/example/hotelmanagement/" + fxmlName));
            Parent pane = loader.load();

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

    @FXML
    private void onSignout(ActionEvent event) {
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
