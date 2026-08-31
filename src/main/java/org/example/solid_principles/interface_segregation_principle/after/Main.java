package org.example.solid_principles.interface_segregation_principle.after;

import java.util.ArrayList;
import java.util.List;

/**
 * AFTER: Following Interface Segregation Principle
 * 
 * Benefits:
 * - Small, focused interfaces
 * - No forced implementation of unused methods
 * - Type-safe operations
 * - Clear indication of capabilities
 * - Easy to add new worker types
 * - Better code organization
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== AFTER: Following Interface Segregation Principle ===\n");
        
        WorkerManager manager = new WorkerManager();
        
        HumanWorker human = new HumanWorker("Alice");
        RobotWorker robot = new RobotWorker("R2D2");
        ContractWorker contractor = new ContractWorker("Bob");
        
        manager.addWorker(human);
        manager.addWorker(robot);
        manager.addWorker(contractor);
        
        // Start work day - everyone can work!
        manager.startWorkDay();
        
        // Lunch break - only those who eat will eat!
        manager.lunchBreak();
        
        // Meeting - only those who attend meetings will attend!
        manager.holdMeeting();
        
        // Reports - only those who report will submit!
        manager.collectReports();
        
        // Demonstrate type-safe operations
        System.out.println("\n\n✅ TYPE-SAFE OPERATIONS:");
        System.out.println("═══════════════════════════════════════════════════════");
        
        // Meeting with only those who can attend
        List<Meetable> meetingAttendees = new ArrayList<>();
        meetingAttendees.add(human);  // Humans attend meetings
        meetingAttendees.add(robot);  // Robots attend meetings
        // meetingAttendees.add(contractor); // Won't compile! Type safety!
        
        manager.conductMeeting(meetingAttendees);
        
        // Reports from only those who can report
        List<Reportable> reporters = new ArrayList<>();
        reporters.add(human);  // Humans submit reports
        reporters.add(robot);  // Robots submit reports
        // reporters.add(contractor); // Won't compile! Type safety!
        
        manager.requestReports(reporters);
        
        // Demonstrate interface composition
        System.out.println("\n\n✅ INTERFACE COMPOSITION:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("Human implements: Workable, Eatable, Sleepable, Meetable, Reportable");
        System.out.println("Robot implements: Workable, Meetable, Reportable");
        System.out.println("Contractor implements: Workable, Eatable, Sleepable");
        
        System.out.println("\nDemonstrating capability checks:");
        checkCapabilities("Alice (Human)", human);
        checkCapabilities("R2D2 (Robot)", robot);
        checkCapabilities("Bob (Contractor)", contractor);
        
        System.out.println("\n\n✅ BENEFITS OF THIS APPROACH:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("✓ Small, focused interfaces (single responsibility)");
        System.out.println("✓ No forced implementation of unused methods");
        System.out.println("✓ Type-safe - won't call methods that don't exist");
        System.out.println("✓ Clear indication of each worker's capabilities");
        System.out.println("✓ Easy to add new worker types with different capabilities");
        System.out.println("✓ Follows Interface Segregation Principle!");
        
        System.out.println("\n🎓 KEY LEARNINGS:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("• Prefer many small interfaces over one large interface");
        System.out.println("• Don't force clients to depend on methods they don't use");
        System.out.println("• Use interface composition to build complex behavior");
        System.out.println("• Design from the client's perspective");
        System.out.println("• Keep interfaces focused and cohesive");
    }
    
    private static void checkCapabilities(String workerName, Object worker) {
        System.out.println("\n" + workerName + " can:");
        
        if (worker instanceof Workable) {
            System.out.println("  ✓ Work");
        }
        if (worker instanceof Eatable) {
            System.out.println("  ✓ Eat");
        }
        if (worker instanceof Sleepable) {
            System.out.println("  ✓ Sleep");
        }
        if (worker instanceof Meetable) {
            System.out.println("  ✓ Attend meetings");
        }
        if (worker instanceof Reportable) {
            System.out.println("  ✓ Submit reports");
        }
    }
}
