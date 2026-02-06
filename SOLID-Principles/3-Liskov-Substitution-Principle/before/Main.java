package before;

/**
 * BEFORE: Violating Liskov Substitution Principle
 * 
 * Problems:
 * - Penguin and Ostrich cannot substitute Bird without breaking code
 * - Client code must use try-catch or type checking
 * - Subclasses throw exceptions where base class doesn't
 * - The inheritance hierarchy is fundamentally flawed
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== BEFORE: Violating Liskov Substitution Principle ===\n");
        
        BirdSanctuary sanctuary = new BirdSanctuary();
        
        // Add different types of birds
        Bird eagle = new Eagle("Eddie the Eagle", 6.5, 2.3);
        Bird sparrow = new Sparrow("Sammy the Sparrow", 0.03);
        Bird penguin = new Penguin("Pete the Penguin", 25.0, 8.0);
        Bird ostrich = new Ostrich("Oscar the Ostrich", 140.0, 70.0);
        
        sanctuary.addBird(eagle);
        sanctuary.addBird(sparrow);
        sanctuary.addBird(penguin);
        sanctuary.addBird(ostrich);
        
        // Feeding works fine - all birds can eat
        sanctuary.feedAllBirds();
        
        // Flight exercise - THIS IS WHERE THE PROBLEM APPEARS!
        System.out.println("\n❌ ATTEMPTING FLIGHT EXERCISE (will cause exceptions):");
        System.out.println("═══════════════════════════════════════════════════════");
        sanctuary.makeAllBirdsFly();
        
        // Bad workaround - type checking
        System.out.println("\n⚠️ BAD WORKAROUND: Type checking before calling methods:");
        System.out.println("═══════════════════════════════════════════════════════");
        sanctuary.exerciseBirds();
        
        // Direct demonstration of LSP violation
        System.out.println("\n❌ DEMONSTRATING LSP VIOLATION:");
        System.out.println("═══════════════════════════════════════════════════════");
        demonstrateLSPViolation(eagle);
        System.out.println();
        demonstrateLSPViolation(sparrow);
        System.out.println();
        demonstrateLSPViolation(penguin); // This will throw exception!
        
        System.out.println("\n\n❌ PROBLEMS WITH THIS APPROACH:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("• Penguin cannot substitute Bird without breaking code");
        System.out.println("• Clients must use try-catch or type checking");
        System.out.println("• Subclasses throw exceptions where superclass doesn't");
        System.out.println("• Inheritance hierarchy is fundamentally flawed");
        System.out.println("• Violates Liskov Substitution Principle!");
    }
    
    /**
     * This method expects ANY Bird to be able to fly
     * 
     * PROBLEM: Works with Eagle and Sparrow, but crashes with Penguin!
     * This violates LSP because Penguin cannot substitute Bird.
     */
    private static void demonstrateLSPViolation(Bird bird) {
        System.out.println("Making " + bird.getName() + " fly...");
        try {
            bird.fly(); // This expectation is reasonable for Bird class!
        } catch (UnsupportedOperationException e) {
            System.out.println("💥 EXCEPTION: " + e.getMessage());
        }
    }
}
