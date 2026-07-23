package before;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages workers in the company
 * 
 * PROBLEM: This class is forced to deal with the fat interface
 */
public class WorkerManager {
    private List<Worker> workers;
    
    public WorkerManager() {
        this.workers = new ArrayList<>();
    }
    
    public void addWorker(Worker worker) {
        workers.add(worker);
    }
    
    /**
     * Make all workers work
     */
    public void startWorkDay() {
        System.out.println("\n🌅 Starting work day...");
        System.out.println("───────────────────────────────────");
        for (Worker worker : workers) {
            worker.work();
        }
    }
    
    /**
     * Lunch break for everyone
     * 
     * PROBLEM: This calls eat() on ALL workers, including robots!
     * This leads to meaningless method calls.
     */
    public void lunchBreak() {
        System.out.println("\n🍽️ Lunch break...");
        System.out.println("───────────────────────────────────");
        for (Worker worker : workers) {
            worker.eat(); // Robots will also be told to eat!
        }
    }
    
    /**
     * Meeting time
     * 
     * PROBLEM: This calls attendMeeting() on contract workers
     * who don't attend meetings!
     */
    public void holdMeeting() {
        System.out.println("\n📅 Team meeting...");
        System.out.println("───────────────────────────────────");
        for (Worker worker : workers) {
            worker.attendMeeting(); // Contract workers too!
        }
    }
    
    /**
     * End of day reports
     * 
     * PROBLEM: Contract workers are asked to submit reports!
     */
    public void collectReports() {
        System.out.println("\n📊 Collecting reports...");
        System.out.println("───────────────────────────────────");
        for (Worker worker : workers) {
            worker.submitReport(); // Contract workers too!
        }
    }
}
