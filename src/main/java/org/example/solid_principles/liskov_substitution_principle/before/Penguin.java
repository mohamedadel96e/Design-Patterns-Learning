package org.example.solid_principles.liskov_substitution_principle.before;

/**
 * Penguin - a bird that CANNOT fly
 * 
 * PROBLEM: Violates Liskov Substitution Principle!
 * 
 * This class inherits from Bird but cannot fulfill the fly() contract.
 * It breaks the substitution principle because:
 * 1. It throws an exception where the base class doesn't
 * 2. Cannot be used wherever Bird is expected
 * 3. Forces clients to check the type before calling fly()
 */
public class Penguin extends Bird {
    private double swimmingSpeed;
    
    public Penguin(String name, double weight, double swimmingSpeed) {
        super(name, weight);
        this.swimmingSpeed = swimmingSpeed;
    }
    
    /**
     * VIOLATION: Throwing an exception breaks the LSP!
     * 
     * The base class doesn't throw this exception, but the subclass does.
     * This violates the contract and breaks client code that expects
     * all Birds to fly successfully.
     */
    @Override
    public void fly() {
        throw new UnsupportedOperationException("❌ " + getName() + " can't fly! Penguins are flightless birds!");
    }
    
    @Override
    public void makeSound() {
        System.out.println("🔊 " + getName() + " calls: Squawk!");
    }
    
    public void swim() {
        System.out.println("🏊 " + getName() + " swims at " + swimmingSpeed + " km/h!");
    }
    
    public void slideOnIce() {
        System.out.println("⛸️ " + getName() + " slides on ice!");
    }
}
