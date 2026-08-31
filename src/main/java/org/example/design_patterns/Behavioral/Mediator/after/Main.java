package org.example.design_patterns.Behavioral.Mediator.after;

public class Main {
    public static void main(String[] args) {
        // 1. Create the independent components (they know nothing of each other)
        Checkbox hasDogCheckbox = new Checkbox();
        TextBox dogNameTextBox = new TextBox();
        Button submitButton = new Button();

        // 2. Create the mediator and tie components to it
        DialogMediator dialogMediator = new DialogMediator(hasDogCheckbox, dogNameTextBox, submitButton);

        // 3. User interaction simulating real-world workflow
        System.out.println("User is viewing the profile form...");
        System.out.println("\n-- User checks 'I have a dog' --");
        hasDogCheckbox.check();

        System.out.println("\n-- User enters their dog's name --");
        dogNameTextBox.setText("Rex");

        System.out.println("\n-- User clicks Submit --");
        submitButton.click();
    }
}
