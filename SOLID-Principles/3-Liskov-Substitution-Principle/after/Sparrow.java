package after;

/**
 * Sparrow - a flying bird
 * 
 * Can safely substitute FlyingBird or Bird without issues!
 */
public class Sparrow extends FlyingBird {
    public Sparrow(String name, double weight, double wingSpan) {
        super(name, weight, wingSpan);
    }
    
    @Override
    public void fly() {
        System.out.println("🐦 " + getName() + " flutters around quickly!");
    }
    
    @Override
    public void makeSound() {
        System.out.println("🔊 " + getName() + " chirps: Chirp chirp!");
    }
}
