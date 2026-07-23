package after;

/**
 * Eagle - a flying bird
 * 
 * Can safely substitute FlyingBird or Bird without issues!
 */
public class Eagle extends FlyingBird {
    public Eagle(String name, double weight, double wingSpan) {
        super(name, weight, wingSpan);
    }
    
    @Override
    public void fly() {
        System.out.println("🦅 " + getName() + " soars majestically with " + getWingSpan() + "m wingspan!");
    }
    
    @Override
    public void makeSound() {
        System.out.println("🔊 " + getName() + " screeches: Screech!");
    }
    
    public void hunt() {
        System.out.println("🎯 " + getName() + " is hunting for prey");
    }
}
