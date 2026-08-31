package org.example.design_patterns.Structural.Bridge.after;

public class Projector implements Device {
    private boolean enabled;
    private int volume = 15;

    @Override
    public void enable() {
        enabled = true;
        System.out.println("Projector turned on.");
    }

    @Override
    public void disable() {
        enabled = false;
        System.out.println("Projector turned off.");
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public int getVolume() {
        return volume;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = Math.max(0, Math.min(volume, getMaxVolume()));
        System.out.println("Projector volume: " + this.volume);
    }

    @Override
    public int getMaxVolume() {
        return 30;
    }

    @Override
    public String getName() {
        return "Projector";
    }

    @Override
    public String getStatus() {
        return "Projector{on=" + enabled + ", volume=" + volume + "}";
    }
}
