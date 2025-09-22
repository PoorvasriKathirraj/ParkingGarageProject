package com.kce.parking.model;

public class ParkingSpot {
    public int number;      // public
    public boolean occupied; // public

    public ParkingSpot(int number) {
        this.number = number;
        this.occupied = false;
    }
}
