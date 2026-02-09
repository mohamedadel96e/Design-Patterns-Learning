package before;

/**
 * DEMONSTRATION: The Problem Without Observer Pattern
 * 
 * This demonstrates a tightly coupled system where the stock market
 * system directly depends on all observer implementations.
 * 
 * Key Problems Demonstrated:
 * 1. Can't add new observers without modifying StockMarketSystem
 * 2. Can't remove observers at runtime
 * 3. All observers must be instantiated upfront
 * 4. Difficult to test individual components in isolation
 * 5. Violates Open-Closed Principle
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  STOCK MARKET SYSTEM - WITHOUT OBSERVER PATTERN (PROBLEM)     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

        // Create stock
        Stock apple = new Stock("AAPL", "Apple I    nc.", 150.00);
        apple.setVolume(25000000);

        // PROBLEM: Must create all observers upfront and pass to system
        MobileAppDisplay mobileApp = new MobileAppDisplay("user123");
        EmailAlertSystem emailAlert = new EmailAlertSystem("trader@example.com", 2.0);
        SMSNotificationService smsService = new SMSNotificationService("+1-555-0123", 5.0);
        TradingBot tradingBot = new TradingBot("AlgoTrader-X", 3.0, 3.0);
        AnalyticsEngine analytics = new AnalyticsEngine();

        // PROBLEM: System is tightly coupled to all these dependencies
        StockMarketSystem system = new StockMarketSystem(
            mobileApp, emailAlert, smsService, tradingBot, analytics
        );

        System.out.println("Initial Stock: " + apple);
        System.out.println();

        // Simulate price updates
        System.out.println("\n🔄 Simulating price increase...\n");
        system.updateStockPrice(apple, 153.50);  // +2.33% increase

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n🔄 Simulating significant price drop...\n");
        system.updateStockPrice(apple, 145.00);  // -5.54% decrease

        System.out.println("\n\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  PROBLEMS WITH THIS APPROACH:                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  ❌ Tightly coupled: System knows all observer types          ║");
        System.out.println("║  ❌ Not extensible: Adding observers requires code changes    ║");
        System.out.println("║  ❌ Not flexible: Can't add/remove observers at runtime       ║");
        System.out.println("║  ❌ Violates SRP: System manages updates AND observers        ║");
        System.out.println("║  ❌ Hard to test: Can't mock individual observers easily      ║");
        System.out.println("║  ❌ Maintenance: Every new feature needs system modification  ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        System.out.println("\n💡 See the 'after' package for the Observer pattern solution!");
    }
}
