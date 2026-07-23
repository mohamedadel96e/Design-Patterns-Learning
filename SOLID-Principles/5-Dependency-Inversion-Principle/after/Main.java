package after;

import java.util.ArrayList;
import java.util.List;

/**
 * AFTER: Following Dependency Inversion Principle
 * 
 * Benefits:
 * - NotificationService depends on abstraction (NotificationChannel)
 * - Low-level modules implement the abstraction
 * - Dependencies injected from outside (IoC)
 * - Easy to test with mocks
 * - Easy to extend (add Slack without modifying NotificationService)
 * - Can swap implementations at runtime
 * - Loose coupling throughout
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== AFTER: Following Dependency Inversion Principle ===\n");
        
        // Create concrete implementations (low-level modules)
        NotificationChannel emailChannel = new EmailChannel("smtp.gmail.com", 587);
        NotificationChannel smsChannel = new SMSChannel("Twilio", "api-key-12345");
        NotificationChannel pushChannel = new PushNotificationChannel("https://fcm.googleapis.com");
        NotificationChannel slackChannel = new SlackChannel("https://hooks.slack.com/services/xxx");
        
        // Inject dependencies into high-level module
        List<NotificationChannel> channels = new ArrayList<>();
        channels.add(emailChannel);
        channels.add(smsChannel);
        channels.add(pushChannel);
        
        NotificationService notificationService = new NotificationService(channels);
        
        System.out.println("Scenario 1: Send via specific channel (Email)");
        System.out.println("═══════════════════════════════════════════════════════");
        notificationService.sendNotification(emailChannel, "user@example.com", "Order Update", "Your order has shipped!");
        
        System.out.println("\n\nScenario 2: Send via specific channel (SMS)");
        System.out.println("═══════════════════════════════════════════════════════");
        notificationService.sendNotification(smsChannel, "+1234567890", "Delivery Alert", "Your delivery is arriving soon!");
        
        System.out.println("\n\nScenario 3: Broadcast to all channels");
        System.out.println("═══════════════════════════════════════════════════════");
        notificationService.sendToAllChannels(
            "recipient-identifier",
            "System Update",
            "Important system maintenance scheduled!"
        );
        
        // Demonstrate dynamic channel addition
        System.out.println("\n\nScenario 4: Add Slack channel dynamically (no code modification!)");
        System.out.println("═══════════════════════════════════════════════════════");
        notificationService.addChannel(slackChannel);
        notificationService.sendNotification(slackChannel, "#general", "New Feature", "Check out our new feature!");
        
        // Demonstrate swapping implementations
        System.out.println("\n\nScenario 5: Swap email provider (Gmail → SendGrid)");
        System.out.println("═══════════════════════════════════════════════════════");
        NotificationChannel sendGridChannel = new EmailChannel("smtp.sendgrid.net", 587);
        notificationService.removeChannel(emailChannel);
        notificationService.addChannel(sendGridChannel);
        notificationService.sendNotification(sendGridChannel, "user@example.com", "Test", "Testing SendGrid!");
        
        // Demonstrate testability
        System.out.println("\n\nScenario 6: Using mock channel for testing");
        System.out.println("═══════════════════════════════════════════════════════");
        NotificationChannel mockChannel = new MockNotificationChannel();
        NotificationService testService = new NotificationService(mockChannel);
        testService.sendNotification(mockChannel, "test@test.com", "Test", "This is a test!");
        
        System.out.println("\n\n✅ BENEFITS OF THIS APPROACH:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("✓ NotificationService depends on abstraction, not concrete classes");
        System.out.println("✓ High-level and low-level modules both depend on abstraction");
        System.out.println("✓ Dependencies injected from outside (Inversion of Control)");
        System.out.println("✓ Easy to test with mock implementations");
        System.out.println("✓ Easy to swap email providers (Gmail → SendGrid)");
        System.out.println("✓ Added Slack without modifying NotificationService!");
        System.out.println("✓ Can add/remove channels dynamically at runtime");
        System.out.println("✓ Loose coupling makes code flexible and maintainable");
        System.out.println("✓ Follows Dependency Inversion Principle!");
        
        System.out.println("\n🎓 KEY LEARNINGS:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("• High-level modules define abstractions they need");
        System.out.println("• Low-level modules implement those abstractions");
        System.out.println("• Both depend on abstractions, not each other");
        System.out.println("• Use Dependency Injection to provide implementations");
        System.out.println("• This inverts the traditional dependency direction");
        System.out.println("• Makes code testable, flexible, and maintainable");
    }
}

/**
 * Mock channel for testing purposes
 * 
 * BENEFIT: Easy to create mocks because we depend on abstractions!
 */
class MockNotificationChannel implements NotificationChannel {
    @Override
    public boolean send(String recipient, String title, String message) {
        System.out.println("🧪 MOCK: Simulating notification send");
        System.out.println("   Recipient: " + recipient);
        System.out.println("   Title: " + title);
        System.out.println("   Message: " + message);
        return true;
    }
    
    @Override
    public String getChannelName() {
        return "Mock Channel (for testing)";
    }
    
    @Override
    public boolean isAvailable() {
        return true;
    }
}
