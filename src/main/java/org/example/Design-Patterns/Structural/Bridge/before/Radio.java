package Structural.Bridge.before;

public class Radio {
    private boolean on;
    private int volume = 5;

    public void turnOn() {
        on = true;
        System.out.println("Radio turned on.");
    }

    public void turnOff() {
        on = false;
        System.out.println("Radio turned off.");
    }

    public void volumeUp() {
        if (on) {
            volume = Math.min(20, volume + 1);
            System.out.println("Radio volume: " + volume);
        }
    }

    public void volumeDown() {
        if (on) {
            volume = Math.max(0, volume - 1);
            System.out.println("Radio volume: " + volume);
        }
    }

    public boolean isOn() {
        return on;
    }

    public String getStatus() {
        return "Radio{on=" + on + ", volume=" + volume + "}";
    }
}
