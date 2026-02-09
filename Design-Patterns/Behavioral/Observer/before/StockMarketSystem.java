package before;

/**
 * PROBLEM: Tightly Coupled Stock Market System
 * 
 * This class demonstrates the issues WITHOUT the Observer pattern:
 * 
 * 1. TIGHT COUPLING: StockMarketSystem directly depends on ALL components
 * 2. SCALABILITY: Adding a new display/notification requires modifying this class
 * 3. INFLEXIBILITY: Can't add/remove observers at runtime
 * 4. MAINTENANCE NIGHTMARE: Every change requires updating multiple methods
 * 5. VIOLATES OPEN-CLOSED PRINCIPLE: Not open for extension, requires modification
 * 6. VIOLATES SINGLE RESPONSIBILITY: Manages stocks AND knows about all observers
 */
public class StockMarketSystem {
    // PROBLEM: Direct dependencies on all components
    private MobileAppDisplay mobileApp;
    private EmailAlertSystem emailAlert;
    private SMSNotificationService smsService;
    private TradingBot tradingBot;
    private AnalyticsEngine analyticsEngine;
    // What if we need to add more? We'll have to modify this class!

    public StockMarketSystem(
            MobileAppDisplay mobileApp,
            EmailAlertSystem emailAlert,
            SMSNotificationService smsService,
            TradingBot tradingBot,
            AnalyticsEngine analyticsEngine) {
        this.mobileApp = mobileApp;
        this.emailAlert = emailAlert;
        this.smsService = smsService;
        this.tradingBot = tradingBot;
        this.analyticsEngine = analyticsEngine;
    }

    /**
     * PROBLEM: This method is tightly coupled to all observer implementations
     * Every new observer type requires modifying this method!
     */
    public void updateStockPrice(Stock stock, double newPrice) {
        double oldPrice = stock.getPrice();
        stock.setPrice(newPrice);

        System.out.println("\n" + "=".repeat(80));
        System.out.println("STOCK PRICE UPDATE: " + stock.getSymbol());
        System.out.println("=".repeat(80));

        // PROBLEM: Manually calling each component
        // What if we want to disable one temporarily? 
        // What if we want to add a new one at runtime?
        mobileApp.updateDisplay(stock, oldPrice, newPrice);
        System.out.println();
        
        emailAlert.sendAlert(stock, oldPrice, newPrice);
        System.out.println();
        
        smsService.sendSMS(stock, oldPrice, newPrice);
        System.out.println();
        
        tradingBot.checkAndExecuteTrades(stock, oldPrice, newPrice);
        System.out.println();
        
        analyticsEngine.logPriceData(stock, oldPrice, newPrice);
        System.out.println("=".repeat(80));
    }

    // PROBLEM: Can't add or remove observers dynamically
    // No attach() or detach() methods possible without major refactoring
}
