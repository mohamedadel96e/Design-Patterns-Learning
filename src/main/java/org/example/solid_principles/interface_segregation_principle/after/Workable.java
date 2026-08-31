package org.example.solid_principles.interface_segregation_principle.after;

/**
 * Small, focused interface for work capability
 * 
 * SOLUTION: Split the fat interface into smaller, specific interfaces
 * Each interface represents ONE capability
 */
public interface Workable {
    void work();
}
