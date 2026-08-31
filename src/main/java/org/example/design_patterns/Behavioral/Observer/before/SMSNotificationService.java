package org.example.design_patterns.Behavioral.Observer.before;

/**
 * SMS notification service for critical alerts
 */
public class SMSNotificationService {
    private String phoneNumber;
    private double criticalThreshold;

    public SMSNotificationService(String phoneNumber, double criticalThreshold) {
        this.phoneNumber = phoneNumber;
        this.criticalThreshold = criticalThreshold;
    }

    public void sendSMS(Stock stock, double oldPrice, double newPrice) {
        double changePercent = Math.abs((newPrice - oldPrice) / oldPrice) * 100;
        
        if (changePercent >= criticalThreshold) {
            System.out.println("📲 [SMS ALERT] Sending to: " + phoneNumber);
            System.out.println("   CRITICAL: " + stock.getSymbol() + " moved " + String.format("%.2f%%", changePercent));
        }
    }
}
