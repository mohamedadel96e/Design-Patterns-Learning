package org.example.solid_principles.interface_segregation_principle.before;

/**
 * PROBLEM: This is a "fat" interface!
 * 
 * It contains ALL possible worker operations.
 * This forces ALL implementations to provide ALL methods,
 * even if they don't need them!
 * 
 * This violates the Interface Segregation Principle.
 */
public interface Worker {
    /**
     * Perform work
     */
    void work();
    
    /**
     * Take a lunch break
     * PROBLEM: Robots don't eat!
     */
    void eat();
    
    /**
     * Take a rest
     * PROBLEM: Robots don't sleep!
     */
    void sleep();
    
    /**
     * Attend a meeting
     * PROBLEM: Some workers don't attend meetings!
     */
    void attendMeeting();
    
    /**
     * Submit work report
     * PROBLEM: Not all workers submit reports!
     */
    void submitReport();
}
