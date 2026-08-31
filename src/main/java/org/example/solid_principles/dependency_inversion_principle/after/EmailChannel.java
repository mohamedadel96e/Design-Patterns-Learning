package org.example.solid_principles.dependency_inversion_principle.after;

/**
 * Low-level module: Email Channel Implementation
 * 
 * SOLUTION: Implements the abstraction defined by high-level module
 * The dependency is inverted - this class depends on NotificationChannel interface!
 */
public class EmailChannel implements NotificationChannel {
    private String smtpServer;
    private int port;
    private boolean available;
    
    public EmailChannel(String smtpServer, int port) {
        this.smtpServer = smtpServer;
        this.port = port;
        this.available = true;
    }
    
    @Override
    public boolean send(String recipient, String title, String message) {
        if (!available) {
            System.out.println("❌ Email channel is not available");
            return false;
        }
        
        System.out.println("📧 Sending email...");
        System.out.println("   Server: " + smtpServer + ":" + port);
        System.out.println("   To: " + recipient);
        System.out.println("   Subject: " + title);
        System.out.println("   Message: " + message);
        System.out.println("✅ Email sent successfully!");
        return true;
    }
    
    @Override
    public String getChannelName() {
        return "Email";
    }
    
    @Override
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
