package org.example.design_patterns.Behavioral.Mediator.after;

public class DialogMediator implements Mediator {
    private Checkbox hasDogCheckbox;
    private TextBox dogNameTextBox;
    private Button submitButton;

    public DialogMediator(Checkbox hasDogCheckbox, TextBox dogNameTextBox, Button submitButton) {
        // Wiring up components
        this.hasDogCheckbox = hasDogCheckbox;
        this.hasDogCheckbox.setMediator(this);

        this.dogNameTextBox = dogNameTextBox;
        this.dogNameTextBox.setMediator(this);

        this.submitButton = submitButton;
        this.submitButton.setMediator(this);
    }

    @Override
    public void notify(Component sender, String event) {
        // Control logic for the 'hasDogCheckbox'
        if (sender == hasDogCheckbox && event.equals("check")) {
            if (hasDogCheckbox.isChecked()) {
                dogNameTextBox.setVisible(true);
            } else {
                dogNameTextBox.setVisible(false);
            }
        }

        // Control logic for the 'dogNameTextBox'
        if (sender == dogNameTextBox && event.equals("textChanged")) {
            if (!dogNameTextBox.isEmpty()) {
                submitButton.setEnabled(true);
            } else {
                submitButton.setEnabled(false);
            }
        }

        // Control logic for the 'submitButton'
        if (sender == submitButton && event.equals("click")) {
            System.out.println("Form sent securely. Data saved!");
        }
    }
}
