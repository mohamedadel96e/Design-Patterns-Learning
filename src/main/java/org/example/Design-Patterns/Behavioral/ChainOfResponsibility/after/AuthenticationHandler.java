package after;

public class AuthenticationHandler extends SecurityHandler {

    @Override
    public boolean handle(HttpRequest request) {
        System.out.println("Processing authentication check for: " + request.getUsername());
        
        if (request.getUsername() == null || request.getPassword() == null || 
            !request.getPassword().equals("secret123")) {
            System.out.println("AuthenticationHandler: Authentication failed!");
            return false; // Break the chain
        }

        // Pass to next handler in the chain
        return handleNext(request);
    }
}
