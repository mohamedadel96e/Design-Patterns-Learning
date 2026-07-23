package after;

/**
 * Robot Worker - implements ONLY what it needs
 * 
 * SOLUTION: Robots work, attend meetings, and submit reports
 * But they DON'T eat or sleep, so they don't implement those interfaces!
 * 
 * Benefits:
 * - No forced implementation of eat() or sleep()
 * - Clear indication of robot capabilities
 * - No meaningless method calls
 */
public class RobotWorker implements Workable, Meetable, Reportable {
    private String id;
    
    public RobotWorker(String id) {
        this.id = id;
    }
    
    public String getId() {
        return id;
    }
    
    @Override
    public void work() {
        System.out.println("🤖 Robot " + id + " is processing tasks");
    }
    
    @Override
    public void attendMeeting() {
        System.out.println("📅 Robot " + id + " is logging meeting data");
    }
    
    @Override
    public void submitReport() {
        System.out.println("📊 Robot " + id + " is transmitting report");
    }
    
    // NO eat() method - robots don't eat!
    // NO sleep() method - robots don't sleep!
}
