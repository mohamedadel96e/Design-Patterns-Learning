package before;

/**
 * Analytics engine that logs price data for historical analysis
 */
public class AnalyticsEngine {
    
    public void logPriceData(Stock stock, double oldPrice, double newPrice) {
        System.out.println("📊 [ANALYTICS ENGINE] Logging data for " + stock.getSymbol());
        System.out.println("   Timestamp: " + java.time.LocalDateTime.now());
        System.out.println("   Old Price: $" + String.format("%.2f", oldPrice));
        System.out.println("   New Price: $" + String.format("%.2f", newPrice));
        System.out.println("   Volume: " + stock.getVolume());
        System.out.println("   Data saved to database ✓");
    }
}
