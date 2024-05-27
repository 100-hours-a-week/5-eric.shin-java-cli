package org.ej31.animal;

public class Pet extends Animal {
    protected boolean groomed;

    public Pet(String name, String breed) {
        super(name, breed);
        this.groomed = false;
    }
}
