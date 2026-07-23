package before;

public class Main {
    public static void main(String[] args) {
        TextBox dogNameTextBox = new TextBox();
        Button submitButton = new Button();
        
        // Checkbox must specifically know about the TextBox and Button to function
        Checkbox hasDogCheckbox = new Checkbox(dogNameTextBox, submitButton);

        System.out.println("Clicking the 'I have a dog' checkbox...");
        hasDogCheckbox.check();
    }
}
