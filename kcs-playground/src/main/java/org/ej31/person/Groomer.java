package org.ej31.person;

import org.ej31.animal.Dog;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Groomer extends Person {
    private String specialization;
    private double baseCost;
    private boolean isBooked;

    public Groomer(String name, String specialization, double baseCost) {
        super(name);
        this.specialization = specialization;
        this.baseCost = baseCost;
        this.isBooked = false;
    }

    public void performGrooming(Dog dog) {
        System.out.println(name + " will give " + dog.getName() + " a " + specialization);
    }

    public double getBaseCost() {
        return baseCost;
    }

    public boolean isBooked() {
        return isBooked;
    }
    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public static Groomer selectGroomer(Scanner scanner, Groomer[] groomers) {
        while (true) {
            try {
                System.out.println("\nAvailable groomers:");
                for (int i = 0; i < groomers.length; i++) {
                    if (!groomers[i].isBooked()) {
                        System.out.println((i + 1) + ". " + groomers[i].getName() + " " + groomers[i].getBaseCost() + "$");
                    }
                }
                System.out.println("Please select a groomer by entering the corresponding number:");
                int selectedGroomerIndex = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (selectedGroomerIndex > 0 && selectedGroomerIndex <= groomers.length && !groomers[selectedGroomerIndex - 1].isBooked()) {
                    groomers[selectedGroomerIndex - 1].setBooked(true);
                    return groomers[selectedGroomerIndex - 1];
                } else {
                    System.out.println("Invalid selection or groomer is already booked. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear the invalid input
            }
        }
    }
}

