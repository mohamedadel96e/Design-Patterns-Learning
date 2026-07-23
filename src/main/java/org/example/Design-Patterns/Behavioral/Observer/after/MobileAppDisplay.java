package after;

/**
 * Concrete Observer - Mobile App Display
 * 
 * This observer updates the mobile app UI when stock prices change.
 * It implements the StockObserver interface and reacts to notifications.
 */
public class MobileAppDisplay implements StockObserver {
    private String userId;
    private boolean notificationsEnabled;

    public MobileAppDisplay(String userId) {
        this.userId = userId;
        this.notificationsEnabled = true;
    }

    @Override
    public void update(Stock stock, double oldPrice, double newPrice) {
        if (!notificationsEnabled) {
            return;
        }

        double changePercent = ((newPrice - oldPrice) / oldPrice) * 100;
        String direction = newPrice > oldPrice ? "📈" : "📉";
        
        System.out.println("📱 [MOBILE APP - User: " + userId + "] " + direction);
        System.out.println("   " + stock.getSymbol() + " price updated:");
        System.out.println("   $" + String.format("%.2f", oldPrice) + " → $" + String.format("%.2f", newPrice));
        System.out.println("   Change: " + String.format("%.2f%%", changePercent));
        System.out.println();
    }

    @Override
    public String getObserverName() {
        return "MobileApp(" + userId + ")";
    }

    public void setNotificationsEnabled(boolean enabled) {
        this.notificationsEnabled = enabled;
    }
}
