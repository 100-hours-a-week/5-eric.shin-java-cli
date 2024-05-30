package org.ej31.booking;

import org.ej31.animal.Dog;
import org.ej31.person.DogOwner;
import org.ej31.person.Groomer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private DogOwner dogOwner;
    private Groomer groomer;
    private Dog dog;
    private double cost;
    private LocalDateTime bookingTime;
    private static List<Booking> bookingList = new ArrayList<>();

    public Booking(DogOwner dogOwner, Groomer groomer, Dog dog, double cost) {
        this.dogOwner = dogOwner;
        this.groomer = groomer;
        this.dog = dog;
        this.cost = cost;
        this.bookingTime = LocalDateTime.now();
        bookingList.add(this);
    }

    public Dog getDog() {
        return dog;
    }
    public Groomer getGroomer() {
        return groomer;
    }
    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public static void displayBookings() {
        if(bookingList.isEmpty()) {
            System.out.println("No bookings found");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for(Booking booking : bookingList) {
                System.out.println("Owner Name: " + booking.dogOwner.getName());
                System.out.println("Phone Number: " + booking.dogOwner.getPhoneNumber());
                System.out.println("Dog Name: " + booking.dog.getName());
                System.out.println("Dog Breed: " + booking.dog.getBreed());
                System.out.println("Groomer Name: " + booking.groomer.getName());
                System.out.println("Cost: $" + booking.cost);
                System.out.println("Booking Time: " + booking.bookingTime.format(formatter));
                System.out.println("---");
            }
        }
    }
}

