package before;

import java.util.ArrayList;
import java.util.List;

/**
 * A sanctuary that takes care of birds
 * 
 * PROBLEM: This class expects all Birds to fly,
 * but not all birds can! This leads to runtime errors.
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
     * Feeds all birds in the sanctuary
     */
    public void feedAllBirds() {
        System.out.println("\n🍽️ Feeding time!");
        System.out.println("───────────────────────────────────");
        for (Bird bird : birds) {
            bird.eat();
        }
    }
    
    /**
     * Makes all birds fly for exercise
     * 
     * PROBLEM: This method assumes ALL birds can fly!
     * This will crash when it encounters penguins or ostriches.
     */
    public void makeAllBirdsFly() {
        System.out.println("\n✈️ Flight exercise time!");
        System.out.println("───────────────────────────────────");
        for (Bird bird : birds) {
            try {
                bird.fly(); // This will throw exception for Penguin and Ostrich!
            } catch (UnsupportedOperationException e) {
                // UGLY WORKAROUND: Have to catch exceptions!
                System.out.println("⚠️ " + bird.getName() + " cannot participate in flight exercise");
            }
        }
    }
    
    /**
     * BAD SOLUTION: Type checking before calling methods
     * 
     * This is a code smell! If you need to check types,
     * your inheritance hierarchy is probably wrong.
     */
    public void exerciseBirds() {
        System.out.println("\n🏋️ Exercise time!");
        System.out.println("───────────────────────────────────");
        for (Bird bird : birds) {
            // Type checking is a sign of LSP violation!
            if (bird instanceof Penguin) {
                ((Penguin) bird).swim();
            } else if (bird instanceof Ostrich) {
                ((Ostrich) bird).run();
            } else {
                bird.fly();
            }
        }
    }
}
