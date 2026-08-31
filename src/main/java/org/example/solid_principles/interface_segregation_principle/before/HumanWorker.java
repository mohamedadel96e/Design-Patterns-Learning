package org.example.solid_principles.interface_segregation_principle.before;

/**
 * Human Worker - uses ALL interface methods
 * This works fine because humans do all these things
 */
public class HumanWorker implements Worker {
    private String name;
    
    public HumanWorker(String name) {
        this.name = name;
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
