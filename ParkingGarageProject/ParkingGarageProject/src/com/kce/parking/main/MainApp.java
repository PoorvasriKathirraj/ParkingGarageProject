package com.kce.parking.main;

import com.kce.parking.model.*;
import com.kce.parking.service.*;
import com.kce.parking.exception.*;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ParkingService service = new ParkingService();
        Tariff tariff = new Tariff(50); // Fee per hour

        System.out.println("Enter number of parking spots:");
        int n = sc.nextInt();
        service.setupSpots(n);

        while(true) {
            System.out.println("\n1. Vehicle Entry");
            System.out.println("2. Vehicle Exit");
            System.out.println("3. Show Occupancy");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1: // Vehicle Entry
                    sc.nextLine(); // consume leftover newline
                    System.out.println("Enter vehicle type:");
                    String type = sc.nextLine();
                    System.out.println("Enter vehicle license:");
                    String license = sc.nextLine();
                    Vehicle v = new Vehicle(license, type);
                    try {
                        Ticket t = service.park(v);
                        System.out.println("Ticket issued ID: " + t.id);
                    } catch(SpotUnavailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 2: // Vehicle Exit
                    System.out.println("Enter ticket ID:");
                    int id = sc.nextInt();
                    try {
                        double fee = service.exit(id, tariff);
                        System.out.println("Fee: " + fee);
                    } catch(SpotUnavailableException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3: // Show occupancy
                    service.showOccupancy();
                    break;

                case 4: // Exit program
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}

