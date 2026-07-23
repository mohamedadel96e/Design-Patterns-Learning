package after;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting WEB SERVER (After) ===");

        // 1. Instantiate the individual handlers
        SecurityHandler ipFilter = new IpFilterHandler();
        SecurityHandler rateLimiter = new RateLimitHandler();
        SecurityHandler authHandler = new AuthenticationHandler();
        SecurityHandler roleCheck = new RoleCheckHandler();

        // 2. Create the chain (Pipeline builder)
        // ipFilter -> rateLimiter -> authHandler -> roleCheck
        ipFilter.setNext(rateLimiter)
                .setNext(authHandler)
                .setNext(roleCheck);

        // 3. Start the WebServer and assign the chain
        WebServer server = new WebServer();
        server.setSecurityChain(ipFilter); // Start at the beginning of the chain

        // --- Demo Execution ---

        // Test 1: IP Blocked (fails immediately)
        HttpRequest req1 = new HttpRequest("192.168.1.50", "user", "secret123", "admin");
        server.processRequest(req1);

        // Test 2: Rate Limited (passes IP check, fails Rate Limit)
        HttpRequest req2 = new HttpRequest("10.0.0.1", "spammer", "secret123", "admin");
        server.processRequest(req2);

        // Test 3: Failed Authentication (passes IP, Rate, fails Auth)
        HttpRequest req3 = new HttpRequest("10.0.0.2", "john", "wrongpass", "admin");
        server.processRequest(req3);

        // Test 4: Failed Authorization (passes IP, Rate, Auth, fails Role)
        HttpRequest req4 = new HttpRequest("10.0.0.3", "jane", "secret123", "user");
        server.processRequest(req4);

        // Test 5: Success (passes all)
        HttpRequest req5 = new HttpRequest("10.0.0.4", "adminUser", "secret123", "admin");
        server.processRequest(req5);
    }
}
