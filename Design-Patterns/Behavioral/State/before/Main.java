package Behavioral.State.before;

public class Main {
    public static void main(String[] args) {
        Document document = new Document("State Pattern Draft");

        System.out.println("Current State: " + document.getState());

        document.approve(); // Fails, must be in moderation
        
        document.publish(); // Moves to Moderation
        System.out.println("Current State: " + document.getState());

        document.reject();  // Moves back to Draft
        System.out.println("Current State: " + document.getState());

        document.publish(); // Moves to Moderation
        document.approve(); // Moves to Published
        System.out.println("Current State: " + document.getState());

        document.publish(); // Fails, already published
    }
}
