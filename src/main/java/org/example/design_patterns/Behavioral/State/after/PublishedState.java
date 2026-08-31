package org.example.design_patterns.Behavioral.State.after;

public class PublishedState extends State {

    public PublishedState(Document document) {
        super(document);
    }

    @Override
    public void publish() {
        System.out.println("Document is already published.");
    }

    @Override
    public void approve() {
        System.out.println("Document is already published. No further approval needed.");
    }

    @Override
    public void reject() {
        System.out.println("Published documents cannot be rejected. Unpublish them instead.");
    }
}
