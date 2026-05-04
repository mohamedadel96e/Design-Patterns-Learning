package after;

public class TextBox extends Component {
    private boolean visible = false;
    private String text = "";

    public void setVisible(boolean visible) {
        this.visible = visible;
        System.out.println("TextBox is now " + (visible ? "visible" : "hidden"));
    }

    public void setText(String text) {
        this.text = text;
        System.out.println("TextBox text set to: " + text);
        // Important: notify the mediator when state changes
        if (mediator != null) {
            mediator.notify(this, "textChanged");
        }
    }

    public boolean isEmpty() {
        return text == null || text.trim().isEmpty();
    }
}
