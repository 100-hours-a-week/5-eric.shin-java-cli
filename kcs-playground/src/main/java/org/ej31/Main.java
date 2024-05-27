package org.ej31;

import org.ej31.animal.Dog;
import org.ej31.booking.Booking;
import org.ej31.booking.BookingManagerThread;
import org.ej31.person.DogOwner;
import org.ej31.person.Groomer;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BookingManagerThread bookingManagerThread = new BookingManagerThread();
        bookingManagerThread.start();

        Groomer[] groomers = {
                new Groomer("Elpeo", "Hair trimming", 20),
                new Groomer("Quess", "Bathing", 25),
                new Groomer("Beltorchika", "Nail trimming", 15),
                new Groomer("Lalah", "Massage", 30)
        };

        System.out.println("Welcome to the Dog Grooming Program!");

        while (true) {
            Dog myDog = DogOwner.getDogInfo(scanner);
            DogOwner dogOwner = DogOwner.getOwnerInfo(scanner, myDog);

            Groomer selectedGroomer = Groomer.selectGroomer(scanner, groomers);

            selectedGroomer.performGrooming(myDog);

            double totalCost = DogOwner.calculateCost(selectedGroomer, myDog);
            System.out.println("\nGrooming Cost: $" + totalCost);

            while (true) {
                try {
                    System.out.println("\nWould you like to book the grooming service? (Y/N)");
                    String bookingResponse = scanner.next().trim().toUpperCase();

                    if (bookingResponse.equals("Y")) {
                        new Booking(dogOwner, selectedGroomer, myDog, totalCost);
                        System.out.println("Grooming service has been successfully booked!");
                        break;
                    } else if (bookingResponse.equals("N")) {
                        System.out.println("Grooming service booking has been cancelled.");
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter Y or N.");
                    }
                } catch (InputMismatchException e) {
                    scanner.next(); // Clear the invalid input
                }
            }
            while (true) {
                try {
                    System.out.println("\nWould you like to see booking information? (Y/N)");
                    String viewBookingsResponse = scanner.next().trim().toUpperCase();

                    if (viewBookingsResponse.equals("Y")) {
                        Booking.displayBookings();
                        break;
                    } else if (viewBookingsResponse.equals("N")) {
                        System.out.println("Thank you for using our Grooming Service!");
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter Y or N.");
                    }
                } catch (InputMismatchException e) {
                    scanner.next(); // Clear the invalid input
                }
            }

            System.out.println("Would you like to add another booking or exit? (A/E)");
            String continueResponse = scanner.next().trim().toUpperCase();

            if (continueResponse.equals("E")) {
                System.out.println("Thank you for using our Grooming Service!");
                bookingManagerThread.stopRunning();
                break;
            }
        }
        scanner.close();
    }
}




