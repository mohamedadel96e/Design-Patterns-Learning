package before;

/**
 * Base class for all birds
 * 
 * PROBLEM: This class assumes ALL birds can fly!
 * This creates a contract that not all birds can fulfill.
 */
public class Bird {
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
     * Makes the bird fly
     * 
     * PROBLEM: This creates an expectation that ALL birds can fly!
     */
    public void fly() {
        System.out.println("🦅 " + name + " is flying!");
    }
    
    public void eat() {
        System.out.println("🍽️ " + name + " is eating");
    }
    
    public void makeSound() {
        System.out.println("🔊 " + name + " makes a sound");
    }
}
