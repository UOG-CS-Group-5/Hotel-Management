package com.example.hotelmanagement.managers;

import com.example.hotelmanagement.models.Reservation;
import java.util.*;

// Handles reservation management (e.g. creating, deleting, searching for reservations)
public class ReservationManager {
    private Map<Integer, Reservation> reservations = new HashMap<>(); // Hash map to map customer to ID
    private int nextID = 1; // Variable for auto-increment for new reservations

    // Adds new reservation for given customer
    public void addReservation(int customerID, int roomID, String checkInDate, String checkOutDate, double totalCost, String reservationDate) {
        Reservation reservation = new Reservation(nextID++, customerID, roomID, checkInDate, checkOutDate, totalCost, reservationDate);

        reservations.put(reservation.getReservationID(), reservation);
        System.out.println("==============================================");
        System.out.println("Reservation added successfully!");
        System.out.println("==============================================");
    }

    // Removes reservation by reservationID
    public void deleteReservation(int reservationID) {
        if(reservations.remove(reservationID) != null) {
            System.out.println("==============================================");
            System.out.println("Reservation deleted successfully!");
            System.out.println("==============================================");
        }
        else {
            System.out.println("==============================================");
            System.out.println("Reservation not found!");
            System.out.println("==============================================");
        }
    }

    // Displays all reservations for a given customer ID
    public List<Reservation> searchReservationByCustomerID(int customerID) {
        List<Reservation> result = new ArrayList<>();
        for(Reservation r : reservations.values()) {
            if(r.getCustomerID() == customerID)
                result.add(r);
        }
        return result;
    }

    // Displays all reservations
    public void displayAllReservations() {
        if(reservations.isEmpty()) {
            System.out.println("==============================================");
            System.out.println("No reservations found.");
            System.out.println("==============================================");
            return;
        }
        for(Reservation r : reservations.values()) {
            System.out.println("==============================================");
            System.out.println(r);
        }
    }
}
