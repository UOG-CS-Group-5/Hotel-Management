package com.example.hotelmanagement.models;

import java.sql.Date;

public class CustomerReservation {

    private int reservationID;
    private int customerID;
    private String fullName;
    private int roomID;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalCost;
    private Date reservationDate;

    public CustomerReservation(int reservationID, int customerID, String fullName,
                               int roomID, Date checkInDate, Date checkOutDate,
                               double totalCost, Date reservationDate) {
        this.reservationID = reservationID;
        this.customerID = customerID;
        this.fullName = fullName;
        this.roomID = roomID;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalCost = totalCost;
        this.reservationDate = reservationDate;
    }

    public int getReservationID() { return reservationID; }
    public int getCustomerID() { return customerID; }
    public String getFullName() { return fullName; }
    public int getRoomID() { return roomID; }
    public Date getCheckInDate() { return checkInDate; }
    public Date getCheckOutDate() { return checkOutDate; }
    public double getTotalCost() { return totalCost; }
    public Date getReservationDate() { return reservationDate; }
}

