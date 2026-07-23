package after;

/**
 * Low-level module: SMS Channel Implementation
 * 
 * SOLUTION: Implements the NotificationChannel abstraction
 */
public class SMSChannel implements NotificationChannel {
    private String provider;
    private String apiKey;
    private boolean available;
    
    public SMSChannel(String provider, String apiKey) {
        this.provider = provider;
        this.apiKey = apiKey;
        this.available = true;
    }
    
    @Override
    public boolean send(String recipient, String title, String message) {
        if (!available) {
            System.out.println("❌ SMS channel is not available");
            return false;
        }
        
        System.out.println("📱 Sending SMS...");
        System.out.println("   Provider: " + provider);
        System.out.println("   To: " + recipient);
        System.out.println("   Message: " + title + " - " + message);
        System.out.println("✅ SMS sent successfully!");
        return true;
    }
    
    @Override
    public String getChannelName() {
        return "SMS";
    }
    
    @Override
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
