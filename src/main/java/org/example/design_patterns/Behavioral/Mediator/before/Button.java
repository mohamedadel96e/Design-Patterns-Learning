package org.example.design_patterns.Behavioral.Mediator.before;

public class Button {
    private boolean enabled = false;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        System.out.println("Button is now " + (enabled ? "enabled" : "disabled"));
    }

    public void click() {
        if (enabled) {
            System.out.println("Button clicked!");
        } else {
            System.out.println("Button is disabled and cannot be clicked.");
        }
    }
}
