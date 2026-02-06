package after;

/**
 * Ostrich - a non-flying bird
 * 
 * SOLUTION: Ostrich now extends NonFlyingBird!
 * No more exceptions thrown!
 */
public class Ostrich extends NonFlyingBird {
    private double runningSpeed;
    
    public Ostrich(String name, double weight, double runningSpeed) {
        super(name, weight);
        this.runningSpeed = runningSpeed;
    }
    
    @Override
    public void makeSound() {
        System.out.println("🔊 " + getName() + " booms: Boom boom!");
    }
    
    /**
     * Ostriches move by running!
     */
    @Override
    public void move() {
        run();
    }
    
    public void run() {
        System.out.println("🏃 " + getName() + " runs at " + runningSpeed + " km/h!");
    }
}
