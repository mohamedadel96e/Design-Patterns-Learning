package Behavioral.State.after;

public abstract class State {
    protected Document document;

    public State(Document document) {
        this.document = document;
    }

    public abstract void publish();
    public abstract void approve();
    public abstract void reject();
}
