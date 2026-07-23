package after;

/**
 * Human Worker - implements ALL capabilities
 * 
 * SOLUTION: Implements only the interfaces it needs
 * Humans work, eat, sleep, attend meetings, and submit reports
 */
public class HumanWorker implements Workable, Eatable, Sleepable, Meetable, Reportable {
    private String name;
    
    public HumanWorker(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public void work() {
        System.out.println("👷 " + name + " is working on tasks");
    }
    
    @Override
    public void eat() {
        System.out.println("🍽️ " + name + " is eating lunch");
    }
    
    @Override
    public void sleep() {
        System.out.println("😴 " + name + " is sleeping");
    }
    
    @Override
    public void attendMeeting() {
        System.out.println("📅 " + name + " is attending a meeting");
    }
    
    @Override
    public void submitReport() {
        System.out.println("📊 " + name + " is submitting a report");
    }
}
