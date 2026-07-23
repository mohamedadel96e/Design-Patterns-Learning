package Structural.Bridge.before;

public class RadioRemoteControl {
    private final Radio radio;

    public RadioRemoteControl(Radio radio) {
        this.radio = radio;
    }

    public void togglePower() {
        if (radio.isOn()) {
            radio.turnOff();
        } else {
            radio.turnOn();
        }
    }

    public void volumeUp() {
        radio.volumeUp();
    }

    public void volumeDown() {
        radio.volumeDown();
    }

    public String getStatus() {
        return radio.getStatus();
    }
}
