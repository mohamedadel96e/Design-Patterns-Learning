package org.example.design_patterns.Structural.Bridge.after;

public class Radio implements Device {
    private boolean enabled;
    private int volume = 5;

    @Override
    public void enable() {
        enabled = true;
        System.out.println("Radio turned on.");
    }

    @Override
    public void disable() {
        enabled = false;
        System.out.println("Radio turned off.");
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
        System.out.println("Radio volume: " + this.volume);
    }

    @Override
    public int getMaxVolume() {
        return 20;
    }

    @Override
    public String getName() {
        return "Radio";
    }

    @Override
    public String getStatus() {
        return "Radio{on=" + enabled + ", volume=" + volume + "}";
    }
}
