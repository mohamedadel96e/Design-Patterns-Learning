package org.example.design_patterns.Structural.Bridge.before;

public class Main {
    public static void main(String[] args) {
        TvRemoteControl tvRemote = new TvRemoteControl(new Tv());
        tvRemote.togglePower();
        tvRemote.volumeUp();
        tvRemote.volumeUp();
        System.out.println(tvRemote.getStatus());
        tvRemote.togglePower();

        RadioRemoteControl radioRemote = new RadioRemoteControl(new Radio());
        radioRemote.togglePower();
        radioRemote.volumeDown();
        System.out.println(radioRemote.getStatus());
        radioRemote.togglePower();
    }
}
