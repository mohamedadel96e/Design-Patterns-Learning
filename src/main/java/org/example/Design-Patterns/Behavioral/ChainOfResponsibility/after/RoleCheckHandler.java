package after;

public class RoleCheckHandler extends SecurityHandler {

    @Override
    public boolean handle(HttpRequest request) {
        System.out.println("Processing authorization check for role: " + request.getRole());
        
        if (request.getRole() == null || !request.getRole().equals("admin")) {
            System.out.println("RoleCheckHandler: Authorization failed! Admin role required.");
            return false; // Break the chain
        }

        // Pass to next handler in the chain
        return handleNext(request);
    }
}
