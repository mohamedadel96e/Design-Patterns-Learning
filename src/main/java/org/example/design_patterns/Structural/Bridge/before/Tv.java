package org.example.design_patterns.Structural.Bridge.before;

public class Tv {
    private boolean on;
    private int volume = 10;

    public void turnOn() {
        on = true;
        System.out.println("TV turned on.");
    }

    public void turnOff() {
        on = false;
        System.out.println("TV turned off.");
    }

    public void volumeUp() {
        if (on) {
            volume = Math.min(100, volume + 1);
            System.out.println("TV volume: " + volume);
        }
    }

    public void volumeDown() {
        if (on) {
            volume = Math.max(0, volume - 1);
            System.out.println("TV volume: " + volume);
        }
    }

    public boolean isOn() {
        return on;
    }

    public String getStatus() {
        return "TV{on=" + on + ", volume=" + volume + "}";
    }
}
