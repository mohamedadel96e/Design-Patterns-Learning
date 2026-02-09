package before;

/**
 * Mobile app component that displays stock prices
 */
public class MobileAppDisplay {
    private String userId;

    public MobileAppDisplay(String userId) {
        this.userId = userId;
    }

    public void updateDisplay(Stock stock, double oldPrice, double newPrice) {
        double changePercent = ((newPrice - oldPrice) / oldPrice) * 100;
        String direction = newPrice > oldPrice ? "📈" : "📉";
        
        System.out.println("📱 [MOBILE APP - User: " + userId + "] " + direction);
        System.out.println("   " + stock.getSymbol() + " price updated:");
        System.out.println("   $" + String.format("%.2f", oldPrice) + " → $" + String.format("%.2f", newPrice));
        System.out.println("   Change: " + String.format("%.2f%%", changePercent));
    }
}
