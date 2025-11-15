package com.example.hotelmanagement.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class LogonController {
    @FXML private TextField tf_ID;
    @FXML private PasswordField pf_Pass;
    @FXML private Label lblStatus;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static final String ADMIN_USER = "admin";
    private static final String ADMIN_PASS = "admin";

    @FXML
    private void initialize() {
        if (pf_Pass != null) {
            pf_Pass.setOnAction(e -> onLogin());
        }
    }

    @FXML
    private void onLogin(){
        String user = tf_ID.getText().trim();
        String pass = pf_Pass.getText().trim();

        if (user.equals(ADMIN_USER) && pass.equals(ADMIN_PASS)) {
            lblStatus.setText("Login successful!");

            try {
                // Load the next screen
                Parent root = FXMLLoader.load(getClass().getResource("/com/example/hotelmanagement/MainView.fxml"));
                Stage stage = (Stage) tf_ID.getScene().getWindow(); // get current window
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            lblStatus.setText("Invalid login. Try again.");
        }

    }



}