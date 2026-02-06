package before;

/**
 * Robot Worker - FORCED to implement methods it doesn't need!
 * 
 * PROBLEM: Violates Interface Segregation Principle!
 * 
 * Robots don't eat or sleep, but we're forced to implement
 * these methods because of the fat Worker interface.
 */
public class RobotWorker implements Worker {
    private String id;
    
    public RobotWorker(String id) {
        this.id = id;
    }
    
    @Override
    public void work() {
        System.out.println("🤖 Robot " + id + " is processing tasks");
    }
    
    /**
     * VIOLATION: Forced to implement eat() even though robots don't eat!
     * 
     * Options (all bad):
     * 1. Throw exception (violates LSP)
     * 2. Empty implementation (confusing, still callable)
     * 3. Print "not applicable" (messy)
     */
    @Override
    public void eat() {
        // Empty implementation - robots don't eat!
        // But we're FORCED to implement this method!
        System.out.println("⚠️ Robot " + id + " doesn't eat (interface violation!)");
    }
    
    /**
     * VIOLATION: Forced to implement sleep() even though robots don't sleep!
     */
    @Override
    public void sleep() {
        // Empty implementation - robots don't sleep!
        System.out.println("⚠️ Robot " + id + " doesn't sleep (interface violation!)");
    }
    
    @Override
    public void attendMeeting() {
        System.out.println("📅 Robot " + id + " is logging meeting data");
    }
    
    @Override
    public void submitReport() {
        System.out.println("📊 Robot " + id + " is transmitting report");
    }
}
