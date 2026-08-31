package org.example.solid_principles.dependency_inversion_principle.before;

/**
 * Low-level module: SMS sending implementation
 */
public class SMSSender {
    private String apiKey;
    private String provider;
    
    public SMSSender(String provider, String apiKey) {
        this.provider = provider;
        this.apiKey = apiKey;
    }
    
    public void sendSMS(String phoneNumber, String message) {
        System.out.println("📱 Sending SMS...");
        System.out.println("   Provider: " + provider);
        System.out.println("   To: " + phoneNumber);
        System.out.println("   Message: " + message);
        System.out.println("✅ SMS sent successfully!");
    }
}
