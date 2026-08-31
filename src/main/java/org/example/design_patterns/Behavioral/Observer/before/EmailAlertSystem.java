package org.example.design_patterns.Behavioral.Observer.before;

/**
 * Email notification system for stock alerts
 */
public class EmailAlertSystem {
    private String email;
    private double alertThreshold;

    public EmailAlertSystem(String email, double alertThreshold) {
        this.email = email;
        this.alertThreshold = alertThreshold;
    }

    public void sendAlert(Stock stock, double oldPrice, double newPrice) {
        double changePercent = Math.abs((newPrice - oldPrice) / oldPrice) * 100;
        
        if (changePercent >= alertThreshold) {
            System.out.println("📧 [EMAIL ALERT] Sending to: " + email);
            System.out.println("   SIGNIFICANT PRICE MOVEMENT!");
            System.out.println("   " + stock.getSymbol() + " changed by " + String.format("%.2f%%", changePercent));
            System.out.println("   Current price: $" + String.format("%.2f", newPrice));
        }
    }
}
