package after;

/**
 * Low-level module: Push Notification Channel Implementation
 * 
 * SOLUTION: Implements the NotificationChannel abstraction
 */
public class PushNotificationChannel implements NotificationChannel {
    private String apiEndpoint;
    private boolean available;
    
    public PushNotificationChannel(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
        this.available = true;
    }
    
    @Override
    public boolean send(String recipient, String title, String message) {
        if (!available) {
            System.out.println("❌ Push notification channel is not available");
            return false;
        }
        
        System.out.println("🔔 Sending push notification...");
        System.out.println("   Endpoint: " + apiEndpoint);
        System.out.println("   Device: " + recipient);
        System.out.println("   Title: " + title);
        System.out.println("   Body: " + message);
        System.out.println("✅ Push notification sent successfully!");
        return true;
    }
    
    @Override
    public String getChannelName() {
        return "Push Notification";
    }
    
    @Override
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
