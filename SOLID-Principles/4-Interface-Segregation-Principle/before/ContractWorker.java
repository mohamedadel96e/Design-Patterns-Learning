package before;

/**
 * Contract Worker - FORCED to implement methods for full-time operations!
 * 
 * PROBLEM: Contract workers don't attend meetings or submit reports
 * but are forced to implement these methods!
 */
public class ContractWorker implements Worker {
    private String name;
    
    public ContractWorker(String name) {
        this.name = name;
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
    
    /**
     * VIOLATION: Contract workers don't attend company meetings!
     */
    @Override
    public void attendMeeting() {
        System.out.println("⚠️ Contract worker " + name + " doesn't attend meetings (interface violation!)");
    }
    
    /**
     * VIOLATION: Contract workers don't submit internal reports!
     */
    @Override
    public void submitReport() {
        System.out.println("⚠️ Contract worker " + name + " doesn't submit reports (interface violation!)");
    }
}
