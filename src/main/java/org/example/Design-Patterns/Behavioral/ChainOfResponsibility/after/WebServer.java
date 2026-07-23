package after;

public class WebServer {
    private SecurityHandler securityChain;

    public void setSecurityChain(SecurityHandler securityChain) {
        this.securityChain = securityChain;
    }

    public boolean processRequest(HttpRequest request) {
        System.out.println("\n--- WebServer: Starting Security Pipeline ---");
        
        // Ensure there is a chain to process
        if (securityChain != null) {
            boolean success = securityChain.handle(request);
            if (success) {
                System.out.println("WebServer: Request successfully passed security pipeline.");
            } else {
                System.out.println("WebServer: Request was REJECTED by the pipeline.");
            }
            return success;
        }

        // Default behavior if no chain is configured
        System.out.println("WebServer: No security configured, request processed.");
        return true;
    }
}
