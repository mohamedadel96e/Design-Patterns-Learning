package org.example.solid_principles.dependency_inversion_principle.after;

import java.util.ArrayList;
import java.util.List;

/**
 * High-level module: Notification Service
 * 
 * SOLUTION: Follows Dependency Inversion Principle!
 * 
 * This class:
 * - Depends on NotificationChannel abstraction, NOT concrete implementations
 * - Receives dependencies via constructor (Dependency Injection)
 * - Can work with ANY NotificationChannel implementation
 * - Is closed for modification but open for extension
 * 
 * Benefits:
 * - Loosely coupled
 * - Easy to test (inject mocks)
 * - Easy to extend (add new channels without modifying this class)
 * - Flexible (swap implementations at runtime)
 */
public class NotificationService {
    private List<NotificationChannel> channels;
    
    /**
     * SOLUTION: Constructor accepts abstractions via Dependency Injection!
     * 
     * The service doesn't create its dependencies - they're injected from outside.
     * This is Inversion of Control (IoC).
     */
    public NotificationService(List<NotificationChannel> channels) {
        this.channels = channels;
    }
    
    /**
     * Alternative constructor for single channel
     */
    public NotificationService(NotificationChannel channel) {
        this.channels = new ArrayList<>();
        this.channels.add(channel);
    }
    
    /**
     * Send notification through a specific channel
     * 
     * BENEFIT: Works with ANY NotificationChannel implementation!
     * Don't need to know if it's Email, SMS, Push, Slack, etc.
     */
    public boolean sendNotification(NotificationChannel channel, String recipient, String title, String message) {
        System.out.println("\n📬 NotificationService: Sending via " + channel.getChannelName());
        System.out.println("─────────────────────────────────────────────────");
        
        if (!channel.isAvailable()) {
            System.out.println("❌ Channel is not available");
            return false;
        }
        
        return channel.send(recipient, title, message);
    }
    
    /**
     * Send notification through all configured channels
     * 
     * BENEFIT: Automatically works with any number and type of channels!
     * New channels are automatically included.
     */
    public void sendToAllChannels(String recipient, String title, String message) {
        System.out.println("\n📬 NotificationService: Broadcasting to all channels");
        System.out.println("═════════════════════════════════════════════════");
        
        int successCount = 0;
        for (NotificationChannel channel : channels) {
            System.out.println("\n➤ Channel: " + channel.getChannelName());
            if (sendNotification(channel, recipient, title, message)) {
                successCount++;
            }
        }
        
        System.out.println("\n📊 Summary: " + successCount + "/" + channels.size() + " channels succeeded");
    }
    
    /**
     * Add a new channel dynamically
     * 
     * BENEFIT: Can add channels at runtime!
     */
    public void addChannel(NotificationChannel channel) {
        channels.add(channel);
        System.out.println("➕ Added channel: " + channel.getChannelName());
    }
    
    /**
     * Remove a channel
     */
    public void removeChannel(NotificationChannel channel) {
        channels.remove(channel);
        System.out.println("➖ Removed channel: " + channel.getChannelName());
    }
    
    /**
     * Get available channels
     */
    public List<NotificationChannel> getAvailableChannels() {
        List<NotificationChannel> available = new ArrayList<>();
        for (NotificationChannel channel : channels) {
            if (channel.isAvailable()) {
                available.add(channel);
            }
        }
        return available;
    }
}
