package after;

/**
 * ABSTRACTION: Notification Channel Interface
 * 
 * SOLUTION: Define an abstraction that both high-level and low-level modules depend on
 * 
 * This interface:
 * - Defines what high-level modules need
 * - Is implemented by low-level modules
 * - Inverts the dependency direction!
 */
public interface NotificationChannel {
    /**
     * Send a notification through this channel
     * 
     * @param recipient The recipient identifier (email, phone, token, etc.)
     * @param title The notification title
     * @param message The notification message
     * @return true if sent successfully, false otherwise
     */
    boolean send(String recipient, String title, String message);
    
    /**
     * Get the name of this notification channel
     */
    String getChannelName();
    
    /**
     * Check if the channel is available
     */
    boolean isAvailable();
}
