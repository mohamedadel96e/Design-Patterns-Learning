package after;

public class Button extends Component {
    private boolean enabled = false;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        System.out.println("Button is now " + (enabled ? "enabled" : "disabled"));
    }

    public void click() {
        if (enabled) {
            System.out.println("Button clicked!");
            if (mediator != null) {
                mediator.notify(this, "click");
            }
        } else {
            System.out.println("Button is disabled and cannot be clicked.");
        }
    }
}
