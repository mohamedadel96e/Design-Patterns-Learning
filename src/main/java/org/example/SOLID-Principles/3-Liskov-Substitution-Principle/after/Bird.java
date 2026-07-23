package after;

/**
 * Base class for ALL birds
 * 
 * Contains only behavior that ALL birds share.
 * Does NOT assume all birds can fly!
 */
public abstract class Bird {
    private String name;
    private double weight;
    
    public Bird(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    
    public String getName() {
        return name;
    }
    
    public double getWeight() {
        return weight;
    }
    
    /**
     * All birds can eat - this is safe to include
     */
    public void eat() {
        System.out.println("🍽️ " + name + " is eating");
    }
    
    /**
     * All birds make sounds - this is safe to include
     */
    public abstract void makeSound();
    
    /**
     * All birds need exercise, but the type varies
     */
    public abstract void exercise();
    
    @Override
    public String toString() {
        return name + " (" + getClass().getSimpleName() + ", " + weight + "kg)";
    }
}
