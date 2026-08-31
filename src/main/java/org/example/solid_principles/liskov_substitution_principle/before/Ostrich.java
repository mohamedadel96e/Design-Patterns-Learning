package org.example.solid_principles.liskov_substitution_principle.before;

/**
 * Ostrich - another bird that CANNOT fly
 * 
 * PROBLEM: Also violates Liskov Substitution Principle!
 */
public class Ostrich extends Bird {
    private double runningSpeed;
    
    public Ostrich(String name, double weight, double runningSpeed) {
        super(name, weight);
        this.runningSpeed = runningSpeed;
    }
    
    /**
     * VIOLATION: Another exception thrown where base class doesn't expect it
     */
    @Override
    public void fly() {
        throw new UnsupportedOperationException("❌ " + getName() + " can't fly! Ostriches are the largest flightless birds!");
    }
    
    @Override
    public void makeSound() {
        System.out.println("🔊 " + getName() + " booms: Boom boom!");
    }
    
    public void run() {
        System.out.println("🏃 " + getName() + " runs at " + runningSpeed + " km/h!");
    }
}
