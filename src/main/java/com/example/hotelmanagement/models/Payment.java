package com.example.hotelmanagement.models;

// Payment details
public class Payment {
    // Variables
    private int paymentID;
    private int reservationID;
    private String paymentDate;
    private double amountPaid;
    private String paymentMethod;

    // Constructor
    public Payment(int paymentID, int reservationID, String paymentDate,
                   double amountPaid, String paymentMethod) {
        this.paymentID = paymentID;
        this.reservationID = reservationID;
        this.paymentDate = paymentDate;
        this.amountPaid = amountPaid;
        this.paymentMethod = paymentMethod;
    }

    // Getters
    public int getPaymentID() {
        return paymentID;
    }
    public int getReservationID() {
        return reservationID;
    }
    public String getPaymentDate() {
        return paymentDate;
    }
    public double getAmountPaid() {
        return amountPaid;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }


    // toString to display info
    @Override
    public String toString() {
        return "Payment ID: " + paymentID +
                "\nReservation ID: " + reservationID +
                "\nPayment Date: " + paymentDate +
                "\nAmount Paid: $" + amountPaid +
                "\nPayment Method: " + paymentMethod;
    }

}
