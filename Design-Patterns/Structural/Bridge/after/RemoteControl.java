package Structural.Bridge.after;

public abstract class RemoteControl {
    protected final Device device;

    protected RemoteControl(Device device) {
        this.device = device;
    }

    public void togglePower() {
        if (device.isEnabled()) {
            device.disable();
        } else {
            device.enable();
        }
    }

    public void volumeUp() {
        device.setVolume(Math.min(device.getMaxVolume(), device.getVolume() + 1));
    }

    public void volumeDown() {
        device.setVolume(Math.max(0, device.getVolume() - 1));
    }

    public String getDeviceStatus() {
        return device.getStatus();
    }
}
