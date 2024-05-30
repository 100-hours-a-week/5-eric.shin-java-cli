package org.ej31;

import org.ej31.animal.Dog;
import org.ej31.booking.Booking;
import org.ej31.booking.BookingConfirmThread;
import org.ej31.booking.BookingManagerThread;
import org.ej31.booking.BookingQueue;
import org.ej31.person.DogOwner;
import org.ej31.person.Groomer;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BookingManagerThread bookingManagerThread = new BookingManagerThread();
        bookingManagerThread.start();

        BookingConfirmThread bookingConfirmThread = new BookingConfirmThread();
        bookingConfirmThread.start();

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

            System.out.println("\nWould you like to book the grooming service? (Y/N)");
            String bookingResponse = scanner.next().trim().toUpperCase();

            Booking booking = null;
            if (bookingResponse.equals("Y")) {
                try {
                    booking = new Booking(dogOwner, selectedGroomer, myDog, totalCost);
                    BookingQueue.addBooking(booking);
                    System.out.println("Grooming service has been successfully booked!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else if (bookingResponse.equals("N")) {
                System.out.println("Grooming service booking has been cancelled.");
                selectedGroomer.setBooked(false);
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
                selectedGroomer.setBooked(false);
            }

            System.out.println("\nWould you like to see booking information? (Y/N)");
            String viewBookingsResponse = scanner.next().trim().toUpperCase();

            if (viewBookingsResponse.equals("Y")) {
                Booking.displayBookings();
            } else if (viewBookingsResponse.equals("N")) {
                System.out.println("Thank you for using our Grooming Service!");
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }

            if (booking != null) {
                System.out.println("Would you like to cancel this booking? (Y/N)");
                String cancelResponse = scanner.next().trim().toUpperCase();

                if (cancelResponse.equals("Y")) {
                    try {
                        BookingQueue.addCancellation(booking);
                        System.out.println("Cancellation request has been submitted.");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            System.out.println("Would you like to add another booking or exit? (A/E)");
            String continueResponse = scanner.next().trim().toUpperCase();

            if (continueResponse.equals("E")) {
                System.out.println("Thank you for using our Grooming Service!");
                bookingManagerThread.stopRunning();
                bookingConfirmThread.stopRunning();
                break;
            }
        }
        scanner.close();
    }
}





