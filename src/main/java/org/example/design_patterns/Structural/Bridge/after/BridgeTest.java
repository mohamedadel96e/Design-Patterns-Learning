package org.example.design_patterns.Structural.Bridge.after;

public class BridgeTest {
    public static void main(String[] args) {
        Tv tv = new Tv();
        BasicRemoteControl tvRemote = new BasicRemoteControl(tv);
        tvRemote.togglePower();
        tvRemote.volumeUp();
        tvRemote.volumeUp();
        assert tv.isEnabled();
        assert tv.getVolume() == 12;
        tvRemote.togglePower();
        assert !tv.isEnabled();

        Radio radio = new Radio();
        AdvancedRemoteControl radioRemote = new AdvancedRemoteControl(radio);
        radioRemote.togglePower();
        radioRemote.volumeUp();
        radioRemote.mute();
        assert radio.isEnabled();
        assert radio.getVolume() == 0;

        Projector projector = new Projector();
        BasicRemoteControl projectorRemote = new BasicRemoteControl(projector);
        projectorRemote.togglePower();
        projectorRemote.volumeDown();
        assert projector.isEnabled();
        assert projector.getVolume() == 14;

        System.out.println("BridgeTest passed.");
    }
}
