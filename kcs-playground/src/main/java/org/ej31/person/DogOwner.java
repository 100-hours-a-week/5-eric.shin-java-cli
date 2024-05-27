package org.ej31.person;

import org.ej31.animal.Dog;

import java.util.Scanner;
import java.util.InputMismatchException;

public class DogOwner extends Person {
    private String phoneNumber;
    private Dog dog;

    public DogOwner(String name, String phoneNumber, Dog dog) {
        super(name);
        this.phoneNumber = phoneNumber;
        this.dog = dog;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static DogOwner getOwnerInfo(Scanner scanner, Dog dog) {
        String ownerName = "";
        String ownerPhoneNumber = "";

        while (true) {
            try {
                System.out.println("\nPlease enter the owner's name:");
                ownerName = scanner.nextLine();
                if (ownerName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Owner's name cannot be empty.");
                }

                System.out.println("Please enter the owner's phone number (010-1234-5678):");
                ownerPhoneNumber = scanner.nextLine();
                if (ownerPhoneNumber.trim().isEmpty()) {
                    throw new IllegalArgumentException("Owner's phone number cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return new DogOwner(ownerName, ownerPhoneNumber, dog);
    }

    public static Dog getDogInfo(Scanner scanner) {
        String dogName = "";
        String dogBreed = "";
        String furLength = "";

        while (true) {
            try {
                System.out.println("\nPlease enter the dog's name:");
                dogName = scanner.nextLine();
                if (dogName.trim().isEmpty()) {
                    throw new IllegalArgumentException("Dog's name cannot be empty.");
                }

                System.out.println("Please enter the dog's breed:");
                dogBreed = scanner.nextLine();
                if (dogBreed.trim().isEmpty()) {
                    throw new IllegalArgumentException("Dog's breed cannot be empty.");
                }

                System.out.println("Please enter the dog's fur length (Short 0$ / Long 10$):");
                furLength = scanner.nextLine();
                if (!(furLength.equalsIgnoreCase("Short") || furLength.equalsIgnoreCase("Long"))) {
                    throw new IllegalArgumentException("Fur length must be 'Short' or 'Long'.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return new Dog(dogName, dogBreed, furLength);
    }

    public static double calculateCost(Groomer groomer, Dog dog) {
        double baseCost = groomer.getBaseCost();
        double additionalCost = dog.getFurLength().equalsIgnoreCase("Long") ? 10 : 0;
        return baseCost + additionalCost;
    }
}


