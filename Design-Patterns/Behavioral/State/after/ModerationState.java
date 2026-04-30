package Behavioral.State.after;

public class ModerationState extends State {

    public ModerationState(Document document) {
        super(document);
    }

    @Override
    public void publish() {
        System.out.println("Document is currently under moderation. Cannot publish again yet.");
    }

    @Override
    public void approve() {
        System.out.println("Document approved! Now it is published.");
        document.setState(new PublishedState(document));
    }

    @Override
    public void reject() {
        System.out.println("Document rejected! Sent back to drafts.");
        document.setState(new DraftState(document));
    }
}
