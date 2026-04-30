package Behavioral.State.after;

public class Document {
    private State state;
    private String content;

    public Document(String content) {
        this.content = content;
        this.state = new DraftState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public void publish() {
        state.publish();
    }

    public void approve() {
        state.approve();
    }

    public void reject() {
        state.reject();
    }

    public String getContent() {
        return content;
    }

    public String getStateName() {
        return state.getClass().getSimpleName();
    }
}
