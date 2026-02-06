package after;

/**
 * Penguin - a non-flying bird
 * 
 * SOLUTION: Penguin now extends NonFlyingBird!
 * 
 * Benefits:
 * - No more UnsupportedOperationException
 * - Can safely substitute NonFlyingBird or Bird
 * - Clients know what to expect based on the type
 * - Honors LSP!
 */
public class Penguin extends NonFlyingBird {
    private double swimmingSpeed;
    
    public Penguin(String name, double weight, double swimmingSpeed) {
        super(name, weight);
        this.swimmingSpeed = swimmingSpeed;
    }
    
    @Override
    public void makeSound() {
        System.out.println("🔊 " + getName() + " calls: Squawk!");
    }
    
    /**
     * Penguins move by swimming!
     */
    @Override
    public void move() {
        swim();
    }
    
    public void swim() {
        System.out.println("🏊 " + getName() + " swims at " + swimmingSpeed + " km/h!");
    }
    
    public void slideOnIce() {
        System.out.println("⛸️ " + getName() + " slides on ice!");
    }
}
