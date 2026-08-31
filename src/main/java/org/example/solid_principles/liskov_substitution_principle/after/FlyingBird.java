package org.example.solid_principles.liskov_substitution_principle.after;

/**
 * Abstract class for birds that CAN fly
 * 
 * This separates flying behavior into its own hierarchy.
 * Only birds that can actually fly extend this class!
 */
public abstract class FlyingBird extends Bird {
    private double wingSpan;
    
    public FlyingBird(String name, double weight, double wingSpan) {
        super(name, weight);
        this.wingSpan = wingSpan;
    }
    
    public double getWingSpan() {
        return wingSpan;
    }
    
    /**
     * All flying birds can fly - this is guaranteed!
     * No exceptions will be thrown here.
     */
    public void fly() {
        System.out.println("🦅 " + getName() + " is flying!");
    }
    
    /**
     * Flying is the exercise for flying birds
     */
    @Override
    public void exercise() {
        fly();
    }
}
