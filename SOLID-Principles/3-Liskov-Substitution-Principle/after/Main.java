package after;

/**
 * AFTER: Following Liskov Substitution Principle
 * 
 * Benefits:
 * - All subtypes can safely substitute their base types
 * - No unexpected exceptions
 * - No need for type checking
 * - Clear and logical inheritance hierarchy
 * - Easy to extend with new bird types
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== AFTER: Following Liskov Substitution Principle ===\n");
        
        BirdSanctuary sanctuary = new BirdSanctuary();
        
        // Add different types of birds
        FlyingBird eagle = new Eagle("Eddie the Eagle", 6.5, 2.3);
        FlyingBird sparrow = new Sparrow("Sammy the Sparrow", 0.03, 0.15);
        NonFlyingBird penguin = new Penguin("Pete the Penguin", 25.0, 8.0);
        NonFlyingBird ostrich = new Ostrich("Oscar the Ostrich", 140.0, 70.0);
        
        sanctuary.addBird(eagle);
        sanctuary.addBird(sparrow);
        sanctuary.addBird(penguin);
        sanctuary.addBird(ostrich);
        
        // All birds can eat - works for everyone!
        sanctuary.feedAllBirds();
        
        // All birds make sounds - works for everyone!
        sanctuary.hearBirdSounds();
        
        // Exercise - each bird exercises appropriately!
        System.out.println("\n✅ POLYMORPHIC EXERCISE (no exceptions!):");
        System.out.println("═══════════════════════════════════════════════════════");
        sanctuary.exerciseAllBirds();
        
        // Specialized training for flying birds only
        System.out.println("\n✅ FLIGHT TRAINING (type-safe):");
        System.out.println("═══════════════════════════════════════════════════════");
        for (FlyingBird bird : sanctuary.getFlyingBirds()) {
            sanctuary.conductFlightTraining(bird);
        }
        
        // Specialized training for non-flying birds
        System.out.println("\n✅ GROUND TRAINING (type-safe):");
        System.out.println("═══════════════════════════════════════════════════════");
        for (NonFlyingBird bird : sanctuary.getNonFlyingBirds()) {
            sanctuary.conductGroundTraining(bird);
        }
        
        // Demonstrate LSP compliance
        System.out.println("\n✅ DEMONSTRATING LSP COMPLIANCE:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Any Bird can be used for basic operations:");
        demonstrateBirdOperations(eagle);
        demonstrateBirdOperations(penguin);
        
        System.out.println("\nAny FlyingBird can fly safely:");
        makeFlyingBirdFly(eagle);
        makeFlyingBirdFly(sparrow);
        // makeFlyingBirdFly(penguin); // Won't compile! Type safety!
        
        System.out.println("\nAny NonFlyingBird can move safely:");
        makeNonFlyingBirdMove(penguin);
        makeNonFlyingBirdMove(ostrich);
        // makeNonFlyingBirdMove(eagle); // Won't compile! Type safety!
        
        System.out.println("\n\n✅ BENEFITS OF THIS APPROACH:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("✓ All subtypes can safely substitute their base types");
        System.out.println("✓ No UnsupportedOperationException thrown");
        System.out.println("✓ No need for try-catch blocks");
        System.out.println("✓ No need for type checking (instanceof)");
        System.out.println("✓ Clear and logical class hierarchy");
        System.out.println("✓ Easy to add new bird types");
        System.out.println("✓ Follows Liskov Substitution Principle!");
        
        System.out.println("\n🎓 KEY LEARNINGS:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("• Design inheritance based on actual behavior");
        System.out.println("• Don't force behaviors that don't apply to all subclasses");
        System.out.println("• Use proper abstraction levels (Bird → FlyingBird/NonFlyingBird)");
        System.out.println("• Subclasses should strengthen, not weaken contracts");
        System.out.println("• Use composition or separate hierarchies for varying behavior");
    }
    
    /**
     * Works with ANY Bird - demonstrating proper substitution
     */
    private static void demonstrateBirdOperations(Bird bird) {
        System.out.println("\nOperating on: " + bird.toString());
        bird.eat();
        bird.makeSound();
    }
    
    /**
     * Works with ANY FlyingBird - guaranteed to work!
     */
    private static void makeFlyingBirdFly(FlyingBird bird) {
        System.out.println("\nMaking " + bird.getName() + " fly:");
        bird.fly(); // This is GUARANTEED to work!
    }
    
    /**
     * Works with ANY NonFlyingBird - guaranteed to work!
     */
    private static void makeNonFlyingBirdMove(NonFlyingBird bird) {
        System.out.println("\nMaking " + bird.getName() + " move:");
        bird.move(); // This is GUARANTEED to work!
    }
}
