package after;

import java.util.ArrayList;
import java.util.List;

/**
 * A sanctuary that takes care of birds
 * 
 * SOLUTION: This class now works with the proper Bird hierarchy.
 * No need for try-catch or type checking!
 */
public class BirdSanctuary {
    private List<Bird> birds;
    
    public BirdSanctuary() {
        this.birds = new ArrayList<>();
    }
    
    public void addBird(Bird bird) {
        birds.add(bird);
        System.out.println("➕ Added " + bird.getName() + " to sanctuary");
    }
    
    /**
     * Feeds all birds - works for ALL birds!
     */
    public void feedAllBirds() {
        System.out.println("\n🍽️ Feeding time!");
        System.out.println("───────────────────────────────────");
        for (Bird bird : birds) {
            bird.eat();
        }
    }
    
    /**
     * Makes all birds vocalize - works for ALL birds!
     */
    public void hearBirdSounds() {
        System.out.println("\n🔊 Bird sounds!");
        System.out.println("───────────────────────────────────");
        for (Bird bird : birds) {
            bird.makeSound();
        }
    }
    
    /**
     * Exercise time for all birds!
     * 
     * SOLUTION: Using polymorphism properly!
     * Each bird exercises in its own appropriate way:
     * - Flying birds fly
     * - Non-flying birds use their movement method
     * 
     * No exceptions, no type checking needed!
     */
    public void exerciseAllBirds() {
        System.out.println("\n🏋️ Exercise time!");
        System.out.println("───────────────────────────────────");
        for (Bird bird : birds) {
            bird.exercise(); // Polymorphism at work!
        }
    }
    
    /**
     * Specialized exercise for flying birds only!
     * 
     * This method accepts only FlyingBird objects,
     * so it's guaranteed safe to call fly().
     */
    public void conductFlightTraining(FlyingBird flyingBird) {
        System.out.println("\n✈️ Flight training for " + flyingBird.getName());
        System.out.println("───────────────────────────────────");
        flyingBird.fly();
        System.out.println("🎯 Great job! Wingspan: " + flyingBird.getWingSpan() + "m");
    }
    
    /**
     * Specialized training for non-flying birds!
     * 
     * This method accepts only NonFlyingBird objects.
     */
    public void conductGroundTraining(NonFlyingBird nonFlyingBird) {
        System.out.println("\n🏃 Ground training for " + nonFlyingBird.getName());
        System.out.println("───────────────────────────────────");
        nonFlyingBird.move();
        System.out.println("🎯 Excellent ground movement!");
    }
    
    public List<Bird> getAllBirds() {
        return new ArrayList<>(birds);
    }
    
    public List<FlyingBird> getFlyingBirds() {
        List<FlyingBird> flyingBirds = new ArrayList<>();
        for (Bird bird : birds) {
            if (bird instanceof FlyingBird) {
                flyingBirds.add((FlyingBird) bird);
            }
        }
        return flyingBirds;
    }
    
    public List<NonFlyingBird> getNonFlyingBirds() {
        List<NonFlyingBird> nonFlyingBirds = new ArrayList<>();
        for (Bird bird : birds) {
            if (bird instanceof NonFlyingBird) {
                nonFlyingBirds.add((NonFlyingBird) bird);
            }
        }
        return nonFlyingBirds;
    }
}
