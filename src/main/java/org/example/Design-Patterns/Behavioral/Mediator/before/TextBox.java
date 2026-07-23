package before;

public class TextBox {
    private boolean visible = false;
    private String text = "";

    public void setVisible(boolean visible) {
        this.visible = visible;
        System.out.println("TextBox is now " + (visible ? "visible" : "hidden"));
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
