package after;

/**
 * NEW: Slack Channel Implementation
 * 
 * BENEFIT: Added without modifying NotificationService!
 * This is possible because we depend on abstractions.
 */
public class SlackChannel implements NotificationChannel {
    private String webhookUrl;
    private boolean available;
    
    public SlackChannel(String webhookUrl) {
        this.webhookUrl = webhookUrl;
        this.available = true;
    }
    
    @Override
    public boolean send(String recipient, String title, String message) {
        if (!available) {
            System.out.println("❌ Slack channel is not available");
            return false;
        }
        
        System.out.println("💬 Sending Slack message...");
        System.out.println("   Webhook: " + webhookUrl);
        System.out.println("   Channel: " + recipient);
        System.out.println("   Title: " + title);
        System.out.println("   Message: " + message);
        System.out.println("✅ Slack message sent successfully!");
        return true;
    }
    
    @Override
    public String getChannelName() {
        return "Slack";
    }
    
    @Override
    public boolean isAvailable() {
        return available;
    }
    
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
