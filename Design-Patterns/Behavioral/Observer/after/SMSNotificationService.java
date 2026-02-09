package after;

/**
 * Concrete Observer - SMS Notification Service
 * 
 * This observer sends SMS alerts for critical price movements.
 * Only triggers for very significant changes (higher threshold than email).
 */
public class SMSNotificationService implements StockObserver {
    private String phoneNumber;
    private double criticalThreshold; // Higher threshold for SMS

    public SMSNotificationService(String phoneNumber, double criticalThreshold) {
        this.phoneNumber = phoneNumber;
        this.criticalThreshold = criticalThreshold;
    }

    @Override
    public void update(Stock stock, double oldPrice, double newPrice) {
        double changePercent = Math.abs((newPrice - oldPrice) / oldPrice) * 100;
        
        if (changePercent >= criticalThreshold) {
            sendSMS(stock, changePercent, newPrice > oldPrice);
        }
    }

    private void sendSMS(Stock stock, double changePercent, boolean isIncrease) {
        System.out.println("📲 [SMS ALERT] Sending to: " + phoneNumber);
        System.out.println("   🚨 CRITICAL PRICE MOVEMENT!");
        System.out.println("   " + stock.getSymbol() + " " + 
                         (isIncrease ? "SURGED" : "DROPPED") + 
                         " by " + String.format("%.2f%%", changePercent));
        System.out.println("   Current: $" + String.format("%.2f", stock.getPrice()));
        System.out.println("   SMS delivered ✓");
        System.out.println();
    }

    @Override
    public String getObserverName() {
        return "SMSService(" + phoneNumber + ")";
    }
}
