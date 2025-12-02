package com.example.hotelmanagement.ui;

import com.example.hotelmanagement.managers.*;
import com.example.hotelmanagement.models.*;

import java.util.Scanner;

// Handles console UI
public class ConsoleUI {
    private Scanner input = new Scanner(System.in);
    private CustomerManager customerManager = new CustomerManager();
    private ReservationManager reservationManager = new ReservationManager();
    private RoomManager roomManager = new RoomManager();
    private PaymentManager paymentManager = new PaymentManager();

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
            System.out.println("3. Room Menu");
            System.out.println("4. Payment Menu");
            System.out.println("5. Search Menu");
            System.out.println("6. Exit");
            System.out.print("Select option: ");
            int option = input.nextInt();
            input.nextLine(); // clear newline

            switch (option) {
                case 1 -> customerMenu();
                case 2 -> reservationMenu();
                case 3 -> roomMenu();
                case 4 -> paymentMenu();
                case 5-> searchMenu();
                case 6 -> {
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

    // Room menu
    private void roomMenu() {
        System.out.println("\n| Room Menu |");
        System.out.println("1. Add Room");
        System.out.println("2. Delete Room");
        System.out.println("3. View All Rooms");
        System.out.print("Select: ");
        int option = input.nextInt();
        input.nextLine(); // clear newline

        switch (option) {
            case 1 -> {
                System.out.print("Enter Room ID: ");
                int roomID = input.nextInt();

                System.out.print("Enter Room Number: ");
                String roomNumber = input.next();
                input.nextLine(); // clear newline before reading String

                System.out.print("Enter Room Type: ");
                String roomType = input.next();
                input.nextLine(); // clear newline before reading String

                System.out.print("Enter Price per Night: ");
                double pricePerNight = input.nextDouble();

                System.out.print("Enter Availability (true/false): ");
                boolean isAvailable = input.nextBoolean();

                roomManager.addRoom(roomID, roomNumber, roomType, pricePerNight, isAvailable);
            }
            case 2 -> {
                System.out.print("Enter Room ID: ");
                int id = input.nextInt();
                input.nextLine(); // clear newline
                roomManager.deleteRoom(id);
            }
            case 3 -> roomManager.getAllRooms();
            default -> System.out.println("Invalid option.");
        }
    }

    // Payment menu
    private void paymentMenu() {
        System.out.println("\n| Payment Menu |");
        System.out.println("1. Add Payment");
        System.out.println("2. Delete Payment");
        System.out.println("3. Update Payment");
        System.out.println("4. View All Payments");
        System.out.print("Select: ");
        int option = input.nextInt();
        input.nextLine(); // clear newline

        switch (option) {
            case 1 -> {
                System.out.print("Enter Reservation ID: ");
                int reservationID = input.nextInt();

                System.out.print("Enter Payment Date (YYYY-MM-DD): ");
                String paymentDateText = input.next();
                input.nextLine(); // clear newline before reading String

                System.out.print("Enter Amount Paid: ");
                double amountPaid = input.nextDouble();

                System.out.print("Enter Payment Method: ");
                String paymentMethod = input.next();
                input.nextLine(); // clear newline before reading String

                try {
                    // Convert String to java.sql.Date
                    java.sql.Date paymentDate = java.sql.Date.valueOf(paymentDateText);

                    paymentManager.addPayment(reservationID, paymentDate, amountPaid, paymentMethod);
                } catch (IllegalArgumentException e) {
                    System.out.println("Date format must be YYYY-MM-DD.");
                }
            }
            case 2 -> {
                System.out.print("Enter Payment ID: ");
                int id = input.nextInt();
                input.nextLine(); // clear newline
                paymentManager.deletePayment(id);
            }
            case 3 ->
            {
                System.out.print("Enter Payment ID: ");
                int paymentID = input.nextInt();

                System.out.print("Enter Reservation ID: ");
                int reservationID = input.nextInt();

                System.out.print("Enter Payment Date (YYYY-MM-DD): ");
                String paymentDateText = input.next();
                input.nextLine(); // clear newline before reading String


                System.out.print("Enter Amount Paid: ");
                double amountPaid = input.nextDouble();

                System.out.print("Enter Payment Method: ");
                String paymentMethod = input.next();
                input.nextLine(); // clear newline before reading String


                try {
                    // Convert String to java.sql.Date
                    java.sql.Date paymentDate = java.sql.Date.valueOf(paymentDateText);

                    paymentManager.updatePayment(paymentID, reservationID, paymentDate, amountPaid, paymentMethod);
                } catch (IllegalArgumentException e) {
                    System.out.println("Date format must be YYYY-MM-DD.");
                }
            }
            case 4 -> paymentManager.displayAllPayments();
            default -> System.out.println("Invalid option.");
        }
    }

    // Searches for customers and reservations
    private void searchMenu() {
        System.out.println("\n| Search Menu |");
        System.out.println("1. Search Customer by Name");
        System.out.println("2. Search Reservation by Customer ID");
        System.out.println("3. Search Room by Room ID");
        System.out.println("4. Search Payment by Payment ID");
        System.out.println("5. Show Total Cost for Customer ID");
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
                System.out.print("Enter Room ID: ");
                int roomID = input.nextInt();
                input.nextLine(); // clear newline
                Room room = roomManager.getRoomByID(roomID);
                if (room == null) {
                    System.out.println("No rooms found.");
                } else {
                    System.out.println(room);
                }
            }
            case 4 -> {
                System.out.print("Enter Payment ID: ");
                int paymentID = input.nextInt();
                input.nextLine(); // clear newline
                Payment payment = paymentManager.searchPayment(paymentID);
                if (payment == null) {
                    System.out.println("No payments found.");
                } else {
                    System.out.println(payment);
                }
            }
            case 5 -> {
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
