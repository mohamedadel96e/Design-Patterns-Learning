package org.example.solid_principles.interface_segregation_principle.after;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages workers in the company
 * 
 * SOLUTION: Works with specific interfaces instead of fat interface
 * 
 * Benefits:
 * - Only calls methods that workers actually support
 * - No meaningless method calls
 * - Type-safe operations
 * - Clear intent
 */
public class WorkerManager {
    private List<Workable> allWorkers;
    
    public WorkerManager() {
        this.allWorkers = new ArrayList<>();
    }
    
    public void addWorker(Workable worker) {
        allWorkers.add(worker);
    }
    
    /**
     * Make all workers work
     * Works with Workable interface - ALL workers can work
     */
    public void startWorkDay() {
        System.out.println("\n🌅 Starting work day...");
        System.out.println("───────────────────────────────────");
        for (Workable worker : allWorkers) {
            worker.work();
        }
    }
    
    /**
     * Lunch break for workers who eat
     * 
     * SOLUTION: Only calls eat() on Eatable workers!
     * Type-safe - won't call eat() on robots
     */
    public void lunchBreak() {
        System.out.println("\n🍽️ Lunch break...");
        System.out.println("───────────────────────────────────");
        for (Workable worker : allWorkers) {
            if (worker instanceof Eatable) {
                ((Eatable) worker).eat();
            } else {
                System.out.println("⏩ Skipping lunch for non-eating worker");
            }
        }
    }
    
    /**
     * Meeting time for workers who attend meetings
     * 
     * SOLUTION: Only calls attendMeeting() on Meetable workers!
     */
    public void holdMeeting() {
        System.out.println("\n📅 Team meeting...");
        System.out.println("───────────────────────────────────");
        for (Workable worker : allWorkers) {
            if (worker instanceof Meetable) {
                ((Meetable) worker).attendMeeting();
            } else {
                System.out.println("⏩ Skipping meeting for non-meeting worker");
            }
        }
    }
    
    /**
     * Collect reports from workers who submit reports
     * 
     * SOLUTION: Only calls submitReport() on Reportable workers!
     */
    public void collectReports() {
        System.out.println("\n📊 Collecting reports...");
        System.out.println("───────────────────────────────────");
        for (Workable worker : allWorkers) {
            if (worker instanceof Reportable) {
                ((Reportable) worker).submitReport();
            } else {
                System.out.println("⏩ Skipping report collection for non-reporting worker");
            }
        }
    }
    
    /**
     * Alternative approach: Separate lists for different capabilities
     */
    public void conductMeeting(List<Meetable> attendees) {
        System.out.println("\n📅 Focused meeting (type-safe)...");
        System.out.println("───────────────────────────────────");
        for (Meetable attendee : attendees) {
            attendee.attendMeeting();
        }
    }
    
    public void requestReports(List<Reportable> reporters) {
        System.out.println("\n📊 Requesting reports (type-safe)...");
        System.out.println("───────────────────────────────────");
        for (Reportable reporter : reporters) {
            reporter.submitReport();
        }
    }
}
