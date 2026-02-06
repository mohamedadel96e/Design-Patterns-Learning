package after;

/**
 * Contract Worker - implements ONLY what they do
 * 
 * SOLUTION: Contract workers work, eat, and sleep
 * But they DON'T attend meetings or submit reports!
 * 
 * Benefits:
 * - No forced implementation of meeting or report methods
 * - Clear contract of what contractor does
 * - Matches real-world behavior
 */
public class ContractWorker implements Workable, Eatable, Sleepable {
    private String name;
    
    public ContractWorker(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    @Override
    public void work() {
        System.out.println("👔 Contract worker " + name + " is working on project");
    }
    
    @Override
    public void eat() {
        System.out.println("🍽️ " + name + " is eating");
    }
    
    @Override
    public void sleep() {
        System.out.println("😴 " + name + " is resting");
    }
    
    // NO attendMeeting() - contractors don't attend company meetings!
    // NO submitReport() - contractors don't submit internal reports!
}
