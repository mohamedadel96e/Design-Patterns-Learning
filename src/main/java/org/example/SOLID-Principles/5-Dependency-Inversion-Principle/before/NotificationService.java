package before;

/**
 * High-level module: Notification Service
 * 
 * PROBLEM: Violates Dependency Inversion Principle!
 * 
 * This class directly depends on concrete low-level implementations:
 * - EmailSender
 * - SMSSender
 * - PushNotificationSender
 * 
 * Issues:
 * - Tightly coupled to concrete classes
 * - Hard to test (can't easily mock dependencies)
 * - Hard to extend (adding Slack requires modifying this class)
 * - Can't swap implementations
 * - High-level module depends on low-level modules
 */
public class NotificationService {
    private EmailSender emailSender;
    private SMSSender smsSender;
    private PushNotificationSender pushSender;
    
    /**
     * VIOLATION: Constructor creates concrete dependencies!
     * 
     * The high-level NotificationService is responsible for
     * creating low-level implementations. This is backwards!
     */
    public NotificationService() {
        // Creating concrete dependencies directly
        this.emailSender = new EmailSender("smtp.gmail.com", 587);
        this.smsSender = new SMSSender("Twilio", "api-key-12345");
        this.pushSender = new PushNotificationSender("https://fcm.googleapis.com");
    }
    
    /**
     * Send notification via email
     * 
     * PROBLEM: Directly depends on EmailSender concrete class
     */
    public void notifyByEmail(String recipient, String message) {
        System.out.println("\n📬 NotificationService: Sending email notification");
        System.out.println("─────────────────────────────────────────────────");
        emailSender.sendEmail(recipient, "Notification", message);
    }
    
    /**
     * Send notification via SMS
     * 
     * PROBLEM: Directly depends on SMSSender concrete class
     */
    public void notifyBySMS(String phoneNumber, String message) {
        System.out.println("\n📬 NotificationService: Sending SMS notification");
        System.out.println("─────────────────────────────────────────────────");
        smsSender.sendSMS(phoneNumber, message);
    }
    
    /**
     * Send notification via push
     * 
     * PROBLEM: Directly depends on PushNotificationSender concrete class
     */
    public void notifyByPush(String deviceToken, String message) {
        System.out.println("\n📬 NotificationService: Sending push notification");
        System.out.println("─────────────────────────────────────────────────");
        pushSender.sendPushNotification(deviceToken, "Notification", message);
    }
    
    /**
     * Send notification via all channels
     * 
     * PROBLEM: Tightly coupled to all three concrete implementations!
     * Adding a new channel (Slack, WhatsApp) requires modifying this method.
     */
    public void notifyAll(String email, String phone, String deviceToken, String message) {
        System.out.println("\n📬 NotificationService: Sending to all channels");
        System.out.println("═════════════════════════════════════════════════");
        notifyByEmail(email, message);
        notifyBySMS(phone, message);
        notifyByPush(deviceToken, message);
    }
}
