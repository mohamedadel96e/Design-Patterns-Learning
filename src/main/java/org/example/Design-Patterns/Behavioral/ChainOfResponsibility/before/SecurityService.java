package before;

public class SecurityService {

    public boolean processRequest(HttpRequest request) {
        System.out.println("Processing request from IP: " + request.getIpAddress());

        // 1. IP Range Check
        if (request.getIpAddress().startsWith("192.168.")) {
            System.out.println("SecurityService: IP blocked!");
            return false;
        }

        // 2. Rate Limiting Check
        if (request.getUsername() != null && request.getUsername().equals("spammer")) {
            System.out.println("SecurityService: Rate limit exceeded for user!");
            return false;
        }

        // 3. Authentication Check
        if (request.getUsername() == null || request.getPassword() == null || 
            !request.getPassword().equals("secret123")) {
            System.out.println("SecurityService: Authentication failed!");
            return false;
        }

        // 4. Authorization Check
        if (request.getRole() == null || !request.getRole().equals("admin")) {
            System.out.println("SecurityService: Authorization failed! Admin role required.");
            return false;
        }

        System.out.println("SecurityService: Request successfully processed.");
        return true;
    }
}
