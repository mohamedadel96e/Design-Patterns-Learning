package after;

public class RateLimitHandler extends SecurityHandler {

    @Override
    public boolean handle(HttpRequest request) {
        System.out.println("Processing rate limit check for: " + request.getUsername());
        
        if (request.getUsername() != null && request.getUsername().equals("spammer")) {
            System.out.println("RateLimitHandler: Rate limit exceeded for user!");
            return false; // Break the chain
        }

        // Pass to next handler in the chain
        return handleNext(request);
    }
}
