package org.example.solid_principles.dependency_inversion_principle.before;

/**
 * Low-level module: Push notification implementation
 */
public class PushNotificationSender {
    private String apiEndpoint;
    
    public PushNotificationSender(String apiEndpoint) {
        this.apiEndpoint = apiEndpoint;
    }
    
    public void sendPushNotification(String deviceToken, String title, String body) {
        System.out.println("🔔 Sending push notification...");
        System.out.println("   Endpoint: " + apiEndpoint);
        System.out.println("   Device: " + deviceToken);
        System.out.println("   Title: " + title);
        System.out.println("   Body: " + body);
        System.out.println("✅ Push notification sent successfully!");
    }
}
