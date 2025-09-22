package com.kce.parking.model;

import java.time.LocalDateTime;

public class Ticket {
    public int id;          // Make it public
    public Vehicle vehicle;
    public ParkingSpot spot;
    public LocalDateTime entryTime;

    public Ticket(int id, Vehicle vehicle, ParkingSpot spot) {
        this.id = id;
        this.vehicle = vehicle;
        this.spot = spot;
        this.entryTime = LocalDateTime.now();
    }
}
