package Structural.Bridge.after;

public class Main {
    public static void main(String[] args) {
        RemoteControl tvRemote = new BasicRemoteControl(new Tv());
        tvRemote.togglePower();
        tvRemote.volumeUp();
        tvRemote.volumeUp();
        System.out.println(tvRemote.getDeviceStatus());
        tvRemote.togglePower();

        AdvancedRemoteControl radioRemote = new AdvancedRemoteControl(new Radio());
        radioRemote.togglePower();
        radioRemote.volumeUp();
        radioRemote.mute();
        System.out.println(radioRemote.getDeviceStatus());
        radioRemote.togglePower();

        RemoteControl projectorRemote = new BasicRemoteControl(new Projector());
        projectorRemote.togglePower();
        projectorRemote.volumeUp();
        System.out.println(projectorRemote.getDeviceStatus());
    }
}
