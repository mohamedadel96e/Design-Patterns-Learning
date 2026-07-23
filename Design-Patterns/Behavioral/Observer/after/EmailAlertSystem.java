package after;

/**
 * Concrete Observer - Email Alert System
 * 
 * This observer sends email alerts when stock prices change significantly.
 * It only sends alerts when the price change exceeds the threshold.
 */
public class EmailAlertSystem implements StockObserver {
    private String email;
    private double alertThreshold; // Percentage change to trigger alert

    public EmailAlertSystem(String email, double alertThreshold) {
        this.email = email;
        this.alertThreshold = alertThreshold;
    }

    @Override
    public void update(Stock stock, double oldPrice, double newPrice) {
        double changePercent = Math.abs((newPrice - oldPrice) / oldPrice) * 100;
        
        if (changePercent >= alertThreshold) {
            sendEmail(stock, oldPrice, newPrice, changePercent);
        }
    }

    private void sendEmail(Stock stock, double oldPrice, double newPrice, double changePercent) {
        System.out.println("📧 [EMAIL ALERT] Sending to: " + email);
        System.out.println("   Subject: SIGNIFICANT PRICE MOVEMENT - " + stock.getSymbol());
        System.out.println("   Body:");
        System.out.println("   ---");
        System.out.println("   Stock: " + stock.getCompanyName() + " (" + stock.getSymbol() + ")");
        System.out.println("   Price change: $" + String.format("%.2f", oldPrice) + 
                         " → $" + String.format("%.2f", newPrice));
        System.out.println("   Percentage: " + String.format("%.2f%%", changePercent));
        System.out.println("   Status: " + (newPrice > oldPrice ? "GAIN 📈" : "LOSS 📉"));
        System.out.println("   ---");
        System.out.println("   Email sent successfully ✓");
        System.out.println();
    }

    @Override
    public String getObserverName() {
        return "EmailAlert(" + email + ")";
    }

    public void setAlertThreshold(double threshold) {
        this.alertThreshold = threshold;
    }
}
