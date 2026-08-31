package org.example.design_patterns.Behavioral.ChainOfResponsibility.after;

public abstract class SecurityHandler {
    private SecurityHandler nextHandler;

    /**
     * Set the next handler in the chain.
     * Returns the next handler to allow easy chain building: handler1.setNext(handler2).setNext(handler3)
     */
    public SecurityHandler setNext(SecurityHandler nextHandler) {
        this.nextHandler = nextHandler;
        return nextHandler;
    }

    /**
     * Subclasses must implement this method to handle the request.
     * Use handleNext(request) to pass the request to the next handler if validation passes.
     */
    public abstract boolean handle(HttpRequest request);

    /**
     * Passes the request to the next handler if one exists.
     */
    protected boolean handleNext(HttpRequest request) {
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        // If there's no next handler, the entire chain succeeded.
        return true;
    }
}
