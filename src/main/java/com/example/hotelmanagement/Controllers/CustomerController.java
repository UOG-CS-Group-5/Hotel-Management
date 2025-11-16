package com.example.hotelmanagement.Controllers;

import com.example.hotelmanagement.managers.CustomerManager;
import com.example.hotelmanagement.models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerController {

    @FXML private TableView<Customer> tblCustomers;

    @FXML private TableColumn<Customer, Integer> colID;
    @FXML private TableColumn<Customer, String> colName;
    @FXML private TableColumn<Customer, String> colEmail;
    @FXML private TableColumn<Customer, String> colPhone;
    @FXML private TableColumn<Customer, String> colAddress;

    @FXML private TextField tfID;
    @FXML private TextField tfName;
    @FXML private TextField tfEmail;
    @FXML private TextField tfNumber;
    @FXML private TextField tfAddress;

    @FXML private Button btnAddUpdate;
    @FXML private Button btnDelete;

    private final CustomerManager manager = new CustomerManager();
    private ObservableList<Customer> customerList;

    @FXML
    public void initialize() {
        // Table column to model mapping
        colID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phoneNum")); // matches getPhoneNum()
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Load data
        loadCustomers();

        // When user clicks a row, fill the text fields
        tblCustomers.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        fillForm(newSelection);
                    }
                }
        );

        // Optional: make ID not editable by user
        tfID.setEditable(false);
    }

    private void loadCustomers() {
        customerList = FXCollections.observableArrayList(manager.getAllCustomers());
        tblCustomers.setItems(customerList);
    }

    private void fillForm(Customer c) {
        tfID.setText(String.valueOf(c.getCustomerID()));
        tfName.setText(c.getFullName());
        tfEmail.setText(c.getEmail());
        tfNumber.setText(c.getPhoneNum());
        tfAddress.setText(c.getAddress());
    }

    private void clearForm() {
        tfID.clear();
        tfName.clear();
        tfEmail.clear();
        tfNumber.clear();
        tfAddress.clear();
        tblCustomers.getSelectionModel().clearSelection();
    }

    @FXML
    private void onAddUpdate() {
        String name = tfName.getText().trim();
        String email = tfEmail.getText().trim();
        String phone = tfNumber.getText().trim();
        String address = tfAddress.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            System.out.println("Name and email are required");
            return;
        }

        if (tfID.getText().isBlank()) {
            // No ID in form: create new customer
            manager.addCustomer(name, email, phone, address);
        } else {
            // ID present: update existing customer
            try {
                int id = Integer.parseInt(tfID.getText().trim());
                manager.updateCustomer(id, name, email, phone, address);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID");
                return;
            }
        }

        loadCustomers();
        clearForm();
    }

    @FXML
    private void onDelete() {
        Customer selected = tblCustomers.getSelectionModel().getSelectedItem();

        if (selected == null) {
            System.out.println("No customer selected");
            return;
        }

        manager.deleteCustomer(selected.getCustomerID());
        loadCustomers();
        clearForm();
    }
}
