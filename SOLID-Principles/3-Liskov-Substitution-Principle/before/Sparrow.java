package before;

/**
 * Sparrow - a bird that CAN fly
 * This also works fine with the base class contract
 */
public class Sparrow extends Bird {
    public Sparrow(String name, double weight) {
        super(name, weight);
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
