package org.example.design_patterns.Behavioral.ChainOfResponsibility.after;

public class IpFilterHandler extends SecurityHandler {

    @Override
    public boolean handle(HttpRequest request) {
        System.out.println("Processing IP check for: " + request.getIpAddress());
        
        if (request.getIpAddress().startsWith("192.168.")) {
            System.out.println("IpFilterHandler: IP Blocked!");
            return false; // Break the chain
        }

        // Pass to next handler in the chain
        return handleNext(request);
    }
}
