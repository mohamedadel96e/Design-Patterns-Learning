package after;

/**
 * DEMONSTRATION: Observer Pattern Solution
 * 
 * This demonstrates the Observer pattern solving the problems from the "before" package:
 * 
 * ✅ Loose coupling between Stock and observers
 * ✅ Easy to add new observers without modifying Stock class
 * ✅ Can attach/detach observers dynamically at runtime
 * ✅ Each observer is independently testable
 * ✅ Follows Open-Closed Principle
 * ✅ Automatic notification of all interested parties
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║    STOCK MARKET SYSTEM - WITH OBSERVER PATTERN (SOLUTION)     ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝\n");

        // Create the stock (Subject)
        Stock apple = new Stock("AAPL", "Apple Inc.", 150.00);
        apple.setVolume(25000000);

        System.out.println("Initial Stock: " + apple);
        System.out.println();

        // Create observers (they don't need to know about each other!)
        MobileAppDisplay mobileApp = new MobileAppDisplay("user123");
        EmailAlertSystem emailAlert = new EmailAlertSystem("trader@example.com", 2.0);
        SMSNotificationService smsService = new SMSNotificationService("+1-555-0123", 5.0);
        TradingBot tradingBot = new TradingBot("AlgoTrader-X", 3.0, 3.0);
        AnalyticsEngine analytics = new AnalyticsEngine();
        RiskManagementSystem riskManager = new RiskManagementSystem(100000, 4.0);

        System.out.println("📌 Attaching observers to AAPL stock...\n");
        
        // Attach observers to the stock
        apple.attach(mobileApp);
        apple.attach(emailAlert);
        apple.attach(smsService);
        apple.attach(tradingBot);
        apple.attach(analytics);
        apple.attach(riskManager);

        System.out.println("\n" + apple);
        System.out.println();

        // ========== Scenario 1: Small price increase ==========
        System.out.println("\n" + "▼".repeat(80));
        System.out.println("SCENARIO 1: Small price increase (+2.33%)");
        System.out.println("▼".repeat(80));
        apple.setPrice(153.50);

        pause(2000);

        // ========== Scenario 2: Significant price drop ==========
        System.out.println("\n" + "▼".repeat(80));
        System.out.println("SCENARIO 2: Significant price drop (-5.54%)");
        System.out.println("▼".repeat(80));
        apple.setPrice(145.00);

        pause(2000);

        // ========== Scenario 3: Dynamically detach an observer ==========
        System.out.println("\n" + "▼".repeat(80));
        System.out.println("SCENARIO 3: Dynamically removing SMS service");
        System.out.println("▼".repeat(80) + "\n");
        apple.detach(smsService);

        System.out.println("\nNew observer count: " + apple.getObserverCount());
        System.out.println("Triggering price update...\n");
        
        apple.setPrice(150.00);  // SMS won't be notified!

        pause(2000);

        // ========== Scenario 4: Dynamically add a new observer ==========
        System.out.println("\n" + "▼".repeat(80));
        System.out.println("SCENARIO 4: Adding a second mobile app display at runtime");
        System.out.println("▼".repeat(80) + "\n");
        
        MobileAppDisplay mobileApp2 = new MobileAppDisplay("user456");
        apple.attach(mobileApp2);

        System.out.println("\nTriggering price update...\n");
        apple.setPrice(155.00);

        pause(1000);

        // ========== Display analytics report ==========
        analytics.printReport();

        // ========== Summary ==========
        System.out.println("\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  BENEFITS OF OBSERVER PATTERN DEMONSTRATED:                   ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  ✅ Loose coupling: Stock doesn't know observer types         ║");
        System.out.println("║  ✅ Extensible: Added RiskManager without modifying Stock     ║");
        System.out.println("║  ✅ Flexible: Attached/detached observers at runtime          ║");
        System.out.println("║  ✅ Follows SRP: Each observer has single responsibility      ║");
        System.out.println("║  ✅ Testable: Can test observers independently                ║");
        System.out.println("║  ✅ Maintainable: Changes don't ripple through system         ║");
        System.out.println("║  ✅ Open-Closed: Open for extension, closed for modification  ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        System.out.println("\n🎯 Compare this with the 'before' package to see the difference!");
    }

    private static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
