package org.example.solid_principles.liskov_substitution_principle.after;

/**
 * Abstract class for birds that CANNOT fly
 * 
 * This gives flightless birds their own proper hierarchy.
 * These birds have alternative locomotion methods.
 */
public abstract class NonFlyingBird extends Bird {
    public NonFlyingBird(String name, double weight) {
        super(name, weight);
    }
    
    /**
     * Non-flying birds have their own forms of movement
     */
    public abstract void move();
    
    /**
     * Movement is the exercise for non-flying birds
     */
    @Override
    public void exercise() {
        move();
    }
}
