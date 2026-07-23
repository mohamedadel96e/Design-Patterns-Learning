package Behavioral.State.before;

public class Document {
    private DocumentState state;
    private String content;

    public Document(String content) {
        this.content = content;
        this.state = DocumentState.DRAFT;
    }

    public void publish() {
        switch (state) {
            case DRAFT:
                System.out.println("Document goes to moderation.");
                state = DocumentState.MODERATION;
                break;
            case MODERATION:
                System.out.println("Document is currently under moderation. Cannot publish again yet.");
                break;
            case PUBLISHED:
                System.out.println("Document is already published.");
                break;
        }
    }

    public void approve() {
        switch (state) {
            case DRAFT:
                System.out.println("Drafts cannot be approved. They must be published for moderation first.");
                break;
            case MODERATION:
                System.out.println("Document approved! Now it is published.");
                state = DocumentState.PUBLISHED;
                break;
            case PUBLISHED:
                System.out.println("Document is already published. No further approval needed.");
                break;
        }
    }

    public void reject() {
        switch (state) {
            case DRAFT:
                System.out.println("Drafts cannot be rejected.");
                break;
            case MODERATION:
                System.out.println("Document rejected! Sent back to drafts.");
                state = DocumentState.DRAFT;
                break;
            case PUBLISHED:
                System.out.println("Published documents cannot be rejected. Unpublish them instead.");
                break;
        }
    }

    public DocumentState getState() {
        return state;
    }

    public String getContent() {
        return content;
    }
}
