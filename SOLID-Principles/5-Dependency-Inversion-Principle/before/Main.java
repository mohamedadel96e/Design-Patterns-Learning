package before;

/**
 * BEFORE: Violating Dependency Inversion Principle
 * 
 * Problems:
 * - NotificationService depends on concrete implementations
 * - High-level module depends on low-level modules
 * - Hard to test (can't mock dependencies)
 * - Hard to extend (adding Slack requires modifying NotificationService)
 * - Can't swap implementations at runtime
 * - Tight coupling throughout
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== BEFORE: Violating Dependency Inversion Principle ===\n");
        
        // Create service - it creates its own dependencies!
        NotificationService notificationService = new NotificationService();
        
        System.out.println("Scenario 1: Send email notification");
        System.out.println("═══════════════════════════════════════════════════════");
        notificationService.notifyByEmail("user@example.com", "Your order has shipped!");
        
        System.out.println("\n\nScenario 2: Send SMS notification");
        System.out.println("═══════════════════════════════════════════════════════");
        notificationService.notifyBySMS("+1234567890", "Your delivery is arriving soon!");
        
        System.out.println("\n\nScenario 3: Send push notification");
        System.out.println("═══════════════════════════════════════════════════════");
        notificationService.notifyByPush("device-token-abc123", "You have a new message!");
        
        System.out.println("\n\nScenario 4: Send to all channels");
        System.out.println("═══════════════════════════════════════════════════════");
        notificationService.notifyAll(
            "user@example.com",
            "+1234567890",
            "device-token-abc123",
            "Important system update!"
        );
        
        System.out.println("\n\n❌ PROBLEMS WITH THIS APPROACH:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("• NotificationService depends on concrete implementations");
        System.out.println("• High-level module (NotificationService) depends on low-level modules");
        System.out.println("• Service creates its own dependencies (violates IoC)");
        System.out.println("• Can't test with mocks easily");
        System.out.println("• Can't swap email provider without modifying NotificationService");
        System.out.println("• Adding Slack/WhatsApp requires modifying NotificationService");
        System.out.println("• Tight coupling makes the code rigid and fragile");
        System.out.println("• Violates Dependency Inversion Principle!");
        
        System.out.println("\n💡 WHAT IF we need to:");
        System.out.println("   - Switch from Gmail to SendGrid?");
        System.out.println("   - Switch from Twilio to AWS SNS?");
        System.out.println("   - Add Slack notifications?");
        System.out.println("   - Test with mock implementations?");
        System.out.println("   → We'd have to modify NotificationService!");
    }
}
