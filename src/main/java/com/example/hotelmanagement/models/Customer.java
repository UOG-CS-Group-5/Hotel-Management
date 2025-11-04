package com.example.hotelmanagement.models;

// Customer details
public class Customer {
    // Variables
    private int customerID;
    private String fullName;
    private String email;
    private String phoneNum;
    private String address;
    private String dateCreated;

    // Constructor
    public Customer(int customerID, String fullName, String email,
                    String phoneNum, String address) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
        this.dateCreated = dateCreated;
    }

    // Getters
    public int getCustomerID() {
        return  customerID;
    }
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public String getAddress() {
        return address;
    }
    public String getDateCreated() {
        return dateCreated;
    }

    // toString to display info
    @Override
    public String toString() {
        return "Customer ID: " + customerID +
                "\nName: " + fullName +
                "\nEmail: " + email +
                "\nPhone Number: " + phoneNum +
                "\nAddress: " + address +
                "\nDate Created: " + dateCreated;
    }

}
