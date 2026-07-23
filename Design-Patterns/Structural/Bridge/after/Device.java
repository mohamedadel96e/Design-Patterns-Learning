package Structural.Bridge.after;

public interface Device {
    void enable();

    void disable();

    boolean isEnabled();

    int getVolume();

    void setVolume(int volume);

    int getMaxVolume();

    String getName();

    String getStatus();
}
