package org.example.design_patterns.Behavioral.ChainOfResponsibility.before;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting SECURITY SERVICE (Before) ===");
        SecurityService securityService = new SecurityService();

        // 1. IP Blocked
        System.out.println("\n--- Request 1 ---");
        HttpRequest req1 = new HttpRequest("192.168.1.50", "user", "secret123", "admin");
        securityService.processRequest(req1);

        // 2. Rate Limited
        System.out.println("\n--- Request 2 ---");
        HttpRequest req2 = new HttpRequest("10.0.0.1", "spammer", "secret123", "admin");
        securityService.processRequest(req2);

        // 3. Failed Authentication
        System.out.println("\n--- Request 3 ---");
        HttpRequest req3 = new HttpRequest("10.0.0.2", "john", "wrongpass", "admin");
        securityService.processRequest(req3);

        // 4. Failed Authorization
        System.out.println("\n--- Request 4 ---");
        HttpRequest req4 = new HttpRequest("10.0.0.3", "jane", "secret123", "user");
        securityService.processRequest(req4);

        // 5. Success
        System.out.println("\n--- Request 5 ---");
        HttpRequest req5 = new HttpRequest("10.0.0.4", "adminUser", "secret123", "admin");
        securityService.processRequest(req5);
    }
}
