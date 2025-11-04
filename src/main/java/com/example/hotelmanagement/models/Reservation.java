package com.example.hotelmanagement.models;

// Reservation details
public class Reservation {
    private int reservationID;
    private int customerID;
    private int roomID;
    private String checkInDate;
    private String checkOutDate;
    private double totalCost;
    private String reservationDate;

    // Constructor
    public Reservation(int reservationID, int customerID, int roomID, String checkInDate,
                       String checkOutDate, double totalCost, String reservationDate) {
        this.reservationID = reservationID;
        this.customerID = customerID;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
        this.reservationDate = reservationDate;
    }

    // Getters
    public int getReservationID() {
        return reservationID;
    }
    public int getCustomerID() {
        return customerID;
    }
    public int getRoomID() {
        return roomID;
    }
    public String getCheckInDate() {
        return checkInDate;
    }
    public String getCheckOutDate() {
        return checkOutDate;
    }
    public double getTotalCost() {
        return totalCost;
    }
    public String getReservationDate() {
        return reservationDate;
    }

    // toString to display info
    @Override
    public String toString() {
        return "Reservation ID: " + reservationID +
                "\nCustomer ID: " + customerID +
                "\nRoom ID: " + roomID +
                "\nCheckInDate='" + checkInDate +
                "\nCheckOutDate='" + checkOutDate +
                "\nTotalCost=" + totalCost +
                "\nReservationDate='" + reservationDate;
    }
}
