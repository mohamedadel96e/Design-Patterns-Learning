package org.example.design_patterns.Structural.Bridge.after;

public class Tv implements Device {
    private boolean enabled;
    private int volume = 10;

    @Override
    public void enable() {
        enabled = true;
        System.out.println("TV turned on.");
    }

    @Override
    public void disable() {
        enabled = false;
        System.out.println("TV turned off.");
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
        System.out.println("TV volume: " + this.volume);
    }

    @Override
    public int getMaxVolume() {
        return 100;
    }

    @Override
    public String getName() {
        return "TV";
    }

    @Override
    public String getStatus() {
        return "TV{on=" + enabled + ", volume=" + volume + "}";
    }
}
