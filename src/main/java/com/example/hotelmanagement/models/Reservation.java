package com.example.hotelmanagement.models;

import java.sql.Date;

// Reservation details
    public class Reservation {
        private int reservationID;
        private int customerID;
        private int roomID;
        private Date checkInDate;
        private Date checkOutDate;
        private double totalCost;
        private Date reservationDate;

        public Reservation(int reservationID, int customerID, int roomID,
                           Date checkInDate, Date checkOutDate,
                           double totalCost, Date reservationDate) {
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

        public Date getCheckInDate() {
            return checkInDate;
        }

        public Date getCheckOutDate() {
            return checkOutDate;
        }

        public double getTotalCost() {
            return totalCost;
        }

        public Date getReservationDate() {
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