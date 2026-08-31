package org.example.solid_principles.interface_segregation_principle.before;

/**
 * BEFORE: Violating Interface Segregation Principle
 * 
 * Problems:
 * - Fat interface forces all implementations to provide all methods
 * - RobotWorker forced to implement eat() and sleep()
 * - ContractWorker forced to implement attendMeeting() and submitReport()
 * - Leads to empty implementations or meaningless method calls
 * - Clients depend on methods they don't use
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== BEFORE: Violating Interface Segregation Principle ===\n");
        
        WorkerManager manager = new WorkerManager();
        
        Worker human = new HumanWorker("Alice");
        Worker robot = new RobotWorker("R2D2");
        Worker contractor = new ContractWorker("Bob");
        
        manager.addWorker(human);
        manager.addWorker(robot);
        manager.addWorker(contractor);
        
        // Start work day - this works fine for everyone
        manager.startWorkDay();
        
        // Lunch break - PROBLEM: Robots are told to eat!
        manager.lunchBreak();
        
        // Meeting - PROBLEM: Contract workers are told to attend!
        manager.holdMeeting();
        
        // Reports - PROBLEM: Contract workers are told to submit!
        manager.collectReports();
        
        System.out.println("\n\n❌ PROBLEMS WITH THIS APPROACH:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("• Worker interface is too fat (too many methods)");
        System.out.println("• RobotWorker forced to implement eat() and sleep()");
        System.out.println("• ContractWorker forced to implement meeting and report methods");
        System.out.println("• Leads to meaningless method implementations");
        System.out.println("• Clients (WorkerManager) depend on methods workers don't use");
        System.out.println("• Hard to add new worker types with different capabilities");
        System.out.println("• Violates Interface Segregation Principle!");
    }
}
