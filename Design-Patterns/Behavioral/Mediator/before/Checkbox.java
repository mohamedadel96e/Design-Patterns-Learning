package before;

public class Checkbox {
    private boolean checked = false;

    // The Checkbox is tightly coupled to other specific components
    private TextBox dogNameTextBox;
    private Button submitButton;

    public Checkbox(TextBox textBox, Button button) {
        this.dogNameTextBox = textBox;
        this.submitButton = button;
    }

    public void check() {
        checked = true;
        System.out.println("Checkbox checked.");

        // Hard-coded presentation and routing logic inside the component
        dogNameTextBox.setVisible(true);
        submitButton.setEnabled(true);
    }
}
