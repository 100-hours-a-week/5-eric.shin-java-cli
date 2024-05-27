package org.ej31.animal;

public class Dog extends Pet {
    private String furLength;

    public Dog(String name, String breed, String furLength) {
        super(name, breed);
        this.furLength = furLength;
    }

    public String getFurLength() {
        return furLength;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Breed: " + breed);
        System.out.println("Fur Length: " + furLength);
    }
}

