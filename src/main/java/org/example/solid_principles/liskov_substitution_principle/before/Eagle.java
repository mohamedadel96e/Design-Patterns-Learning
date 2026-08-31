package org.example.solid_principles.liskov_substitution_principle.before;

/**
 * Eagle - a bird that CAN fly
 * This works fine with the base class contract
 */
public class Eagle extends Bird {
    private double wingSpan;
    
    public Eagle(String name, double weight, double wingSpan) {
        super(name, weight);
        this.wingSpan = wingSpan;
    }
    
    @Override
    public void fly() {
        System.out.println("🦅 " + getName() + " soars majestically with " + wingSpan + "m wingspan!");
    }
    
    @Override
    public void makeSound() {
        System.out.println("🔊 " + getName() + " screeches: Screech!");
    }
    
    public void hunt() {
        System.out.println("🎯 " + getName() + " is hunting for prey");
    }
}
