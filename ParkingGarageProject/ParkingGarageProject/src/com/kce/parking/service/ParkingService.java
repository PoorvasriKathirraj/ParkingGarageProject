package com.kce.parking.service;

import com.kce.parking.model.*;
import com.kce.parking.exception.*;
import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import java.time.LocalDateTime;

public class ParkingService {
    public List<ParkingSpot> spots = new ArrayList<>();
    public List<Ticket> tickets = new ArrayList<>();
    public int ticketCount = 1;

    public void setupSpots(int n) {
        for (int i = 1; i <= n; i++) {
            spots.add(new ParkingSpot(i));
        }
    }

    public Ticket park(Vehicle v) throws SpotUnavailableException {
        for (ParkingSpot spot : spots) {
            if (!spot.occupied) {
                spot.occupied = true;
                Ticket t = new Ticket(ticketCount++, v, spot);
                tickets.add(t);
                return t;
            }
        }
        throw new SpotUnavailableException("No spot available!");
    }

    public double exit(int ticketId, Tariff tariff) throws SpotUnavailableException {
        for (Ticket t : tickets) {
            if (t.id == ticketId) {
                t.spot.occupied = false;
                tickets.remove(t);
                long hours = Duration.between(t.entryTime, LocalDateTime.now()).toHours() + 1;
                return hours * tariff.ratePerHour;
            }
        }
        throw new SpotUnavailableException("Ticket not found!");
    }

    public void showOccupancy() {
        System.out.println("Spot\tStatus");
        for (ParkingSpot s : spots) {
            System.out.println(s.number + "\t" + (s.occupied ? "Occupied" : "Available"));
        }
    }
}

