package com.example.hotelmanagement.ui;

import com.example.hotelmanagement.managers.*;
import com.example.hotelmanagement.models.Customer;
import com.example.hotelmanagement.models.Reservation;

import java.util.Scanner;

// Handles console UI
public class ConsoleUI {
    private Scanner input = new Scanner(System.in);
    private CustomerManager customerManager = new CustomerManager();
    private ReservationManager reservationManager = new ReservationManager();

    // Starts program
    public void start() {
        System.out.println("UOG G5 Hotel Management Software");
        if (!login()) {
            return;
        }

        while (true) {
            // Main menu options
            System.out.println("\n| Main Menu |");
            System.out.println("1. Customer Menu");
            System.out.println("2. Reservation Menu");
            System.out.println("3. Search Menu");
            System.out.println("4. Exit");
            System.out.print("Select option: ");
            int option = input.nextInt();
            input.nextLine(); // clear newline

            switch (option) {
                case 1 -> customerMenu();
                case 2 -> reservationMenu();
                case 3 -> searchMenu();
                case 4 -> {
                    System.out.println("Exiting, see you next time!");
                    return;
                }
                default -> System.out.println("Invalid entry. Please enter a valid option!");
            }
        }
    }

    // Login screen
    private boolean login() {
        System.out.print("Enter ID: ");
        String id = input.nextLine();
        System.out.print("Enter Password: ");
        String password = input.nextLine();

        // Default login id and password
        if (id.equals("admin") && password.equals("admin")) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid login! Exiting application...");
            return false;
        }
    }

    // Customer menu
    private void customerMenu() {
        System.out.println("\n| Customer Menu |");
        System.out.println("1. Add Customer");
        System.out.println("2. Delete Customer");
        System.out.println("3. View All Customers");
        System.out.print("Select: ");
        int option = input.nextInt();
        input.nextLine(); // clear newline

        switch (option) {
            case 1 -> {
                System.out.print("Enter Full Name: ");
                String fullName = input.nextLine();
                System.out.print("Enter Email: ");
                String email = input.nextLine();
                System.out.print("Enter Phone Number: ");
                String phoneNum = input.nextLine();
                System.out.print("Enter Address: ");
                String address = input.nextLine();
                customerManager.addCustomer(fullName, email, phoneNum, address);
            }
            case 2 -> {
                System.out.print("Enter Customer ID: ");
                int id = input.nextInt();
                input.nextLine(); // clear newline
                customerManager.deleteCustomer(id);
            }
            case 3 -> customerManager.displayAllCustomers();
            default -> System.out.println("Invalid option.");
        }
    }

    // Reservation menu
    private void reservationMenu() {
        System.out.println("\n| Reservation Menu |");
        System.out.println("1. Add Reservation");
        System.out.println("2. Delete Reservation");
        System.out.println("3. View All Reservations");
        System.out.print("Select: ");
        int option = input.nextInt();
        input.nextLine(); // clear newline

        switch (option) {
            case 1 -> {
                System.out.print("Enter Customer ID: ");
                int customerID = input.nextInt();
                System.out.print("Enter Room ID: ");
                int roomID = input.nextInt();
                input.nextLine(); // clear newline before reading String

                System.out.print("Enter Check-in Date (YYYY-MM-DD): ");
                String checkInText = input.nextLine();

                System.out.print("Enter Check-out Date (YYYY-MM-DD): ");
                String checkOutText = input.nextLine();

                System.out.print("Enter Total Cost: ");
                double totalCost = input.nextDouble();
                input.nextLine(); // clear newline

                System.out.print("Enter Reservation Date (YYYY-MM-DD): ");
                String reservationDateText = input.nextLine();

                try {
                    // Convert String to java.sql.Date
                    java.sql.Date checkIn = java.sql.Date.valueOf(checkInText);
                    java.sql.Date checkOut = java.sql.Date.valueOf(checkOutText);
                    java.sql.Date reservationDate = java.sql.Date.valueOf(reservationDateText);

                    reservationManager.addReservation(customerID, roomID,
                            checkIn, checkOut, totalCost, reservationDate);
                } catch (IllegalArgumentException e) {
                    System.out.println("Date format must be YYYY-MM-DD.");
                }
            }
            case 2 -> {
                System.out.print("Enter Reservation ID: ");
                int id = input.nextInt();
                input.nextLine(); // clear newline
                reservationManager.deleteReservation(id);
            }
            case 3 -> reservationManager.displayAllReservations();
            default -> System.out.println("Invalid option.");
        }
    }

    // Searches for customers and reservations
    private void searchMenu() {
        System.out.println("\n| Search Menu |");
        System.out.println("1. Search Customer by Name");
        System.out.println("2. Search Reservation by Customer ID");
        System.out.println("3. Show Total Cost for Customer ID");
        System.out.print("Select: ");
        int option = input.nextInt();
        input.nextLine(); // clear newline

        switch (option) {
            case 1 -> {
                System.out.print("Enter Customer Name: ");
                String fullName = input.nextLine();
                Customer c = customerManager.searchName(fullName);
                System.out.println((c != null) ? c : "Customer not found.");
            }
            case 2 -> {
                System.out.print("Enter Customer ID: ");
                int customerID = input.nextInt();
                input.nextLine(); // clear newline
                Reservation reservation = reservationManager.searchReservationByCustomerID(customerID);
                if (reservation == null) {
                    System.out.println("No reservations found.");
                } else {
                    System.out.println(reservation);
                }
            }
            case 3 -> {
                System.out.print("Enter Customer ID: ");
                int customerID = input.nextInt();
                input.nextLine(); // clear newline
                Reservation reservation = reservationManager.searchReservationByCustomerID(customerID);
                if (reservation == null) {
                    System.out.println("No reservations found.");
                } else {
                    double total = reservation.getTotalCost();
                    System.out.println("Total cost for customer " + customerID + ": $" + total);
                }
            }
            default -> System.out.println("Invalid option.");
        }
    }
}
