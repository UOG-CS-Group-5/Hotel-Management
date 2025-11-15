package com.example.hotelmanagement.models;

// Room details
public class Room {
    // Variables
    private int roomID;
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean availability;

    // Constructor
    public Room(int roomID, String roomNumber, String roomType,
                double pricePerNight, boolean availability) {
        this.roomID = roomID;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.availability = availability;
    }

    // Getters
    public int getRoomID() {
        return roomID;
    }
    public String getRoomNumber() {
        return roomNumber;
    }
    public String getRoomType() {
        return roomType;
    }
    public double getPricePerNight() {
        return pricePerNight;
    }
    public boolean isAvailability() {
        return availability;
    }


    // toString to display info
    @Override
    public String toString() {
        return "Room ID: " + roomID +
                "\nRoom Number: " + roomNumber +
                "\nRoom Type: " + roomType +
                "\nPrice per Night: $" + pricePerNight +
                "\nAvailibility: " + availability;
    }

}
