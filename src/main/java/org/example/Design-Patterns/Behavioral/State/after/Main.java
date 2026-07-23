package Behavioral.State.after;

public class Main {
    public static void main(String[] args) {
        Document document = new Document("State Pattern Draft");

        System.out.println("Current State: " + document.getStateName());

        document.approve(); // Fails, must be in moderation
        
        document.publish(); // Moves to Moderation
        System.out.println("Current State: " + document.getStateName());

        document.reject();  // Moves back to Draft
        System.out.println("Current State: " + document.getStateName());

        document.publish(); // Moves to Moderation
        document.approve(); // Moves to Published
        System.out.println("Current State: " + document.getStateName());

        document.publish(); // Fails, already published
    }
}
