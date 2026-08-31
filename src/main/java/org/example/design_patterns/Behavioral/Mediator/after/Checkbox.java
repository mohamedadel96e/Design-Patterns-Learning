package org.example.design_patterns.Behavioral.Mediator.after;

public class Checkbox extends Component {
    private boolean checked = false;

    public void check() {
        checked = true;
        System.out.println("Checkbox checked.");
        // Notify the mediator that an event has occurred
        if (mediator != null) {
            mediator.notify(this, "check");
        }
    }

    public boolean isChecked() {
        return checked;
    }
}
