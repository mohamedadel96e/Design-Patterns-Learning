package org.example.design_patterns.Structural.Bridge.before;

public class TvRemoteControl {
    private final Tv tv;

    public TvRemoteControl(Tv tv) {
        this.tv = tv;
    }

    public void togglePower() {
        if (tv.isOn()) {
            tv.turnOff();
        } else {
            tv.turnOn();
        }
    }

    public void volumeUp() {
        tv.volumeUp();
    }

    public void volumeDown() {
        tv.volumeDown();
    }

    public String getStatus() {
        return tv.getStatus();
    }
}
