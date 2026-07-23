package Behavioral.State.after;

public class DraftState extends State {

    public DraftState(Document document) {
        super(document);
    }

    @Override
    public void publish() {
        System.out.println("Document goes to moderation.");
        document.setState(new ModerationState(document));
    }

    @Override
    public void approve() {
        System.out.println("Drafts cannot be approved. They must be published for moderation first.");
    }

    @Override
    public void reject() {
        System.out.println("Drafts cannot be rejected.");
    }
}
