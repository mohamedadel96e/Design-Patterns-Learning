package org.example.design_patterns.Behavioral.Observer.after;

/**
 * Concrete Observer - Automated Trading Bot
 * 
 * This observer automatically executes buy/sell orders based on price movements.
 * Demonstrates how observers can take autonomous actions based on updates.
 */
public class TradingBot implements StockObserver {
    private String botName;
    private double buyThreshold; // % drop to trigger buy
    private double sellThreshold; // % rise to trigger sell
    private boolean autoTradingEnabled;

    public TradingBot(String botName, double buyThreshold, double sellThreshold) {
        this.botName = botName;
        this.buyThreshold = buyThreshold;
        this.sellThreshold = sellThreshold;
        this.autoTradingEnabled = true;
    }

    @Override
    public void update(Stock stock, double oldPrice, double newPrice) {
        if (!autoTradingEnabled) {
            return;
        }

        System.out.println("🤖 [TRADING BOT: " + botName + "] Analyzing " + stock.getSymbol());
        
        double changePercent = ((newPrice - oldPrice) / oldPrice) * 100;
        
        if (changePercent <= -buyThreshold) {
            executeBuyOrder(stock, newPrice, Math.abs(changePercent));
        } else if (changePercent >= sellThreshold) {
            executeSellOrder(stock, newPrice, changePercent);
        } else {
            System.out.println("   ⏸ HOLD: Price change (" + String.format("%.2f%%", changePercent) + 
                             ") within acceptable range");
            System.out.println();
        }
    }

    private void executeBuyOrder(Stock stock, double price, double dropPercent) {
        System.out.println("   💰 BUY SIGNAL TRIGGERED!");
        System.out.println("   Reason: Price dropped " + String.format("%.2f%%", dropPercent));
        System.out.println("   Action: Buying 100 shares at $" + String.format("%.2f", price));
        System.out.println("   Total: $" + String.format("%.2f", price * 100));
        System.out.println("   ✅ BUY ORDER EXECUTED");
        System.out.println();
    }

    private void executeSellOrder(Stock stock, double price, double risePercent) {
        System.out.println("   📈 SELL SIGNAL TRIGGERED!");
        System.out.println("   Reason: Price rose " + String.format("%.2f%%", risePercent));
        System.out.println("   Action: Selling 100 shares at $" + String.format("%.2f", price));
        System.out.println("   Total: $" + String.format("%.2f", price * 100));
        System.out.println("   ✅ SELL ORDER EXECUTED");
        System.out.println();
    }

    @Override
    public String getObserverName() {
        return "TradingBot(" + botName + ")";
    }

    public void setAutoTradingEnabled(boolean enabled) {
        this.autoTradingEnabled = enabled;
        System.out.println("🤖 [" + botName + "] Auto-trading " + (enabled ? "ENABLED" : "DISABLED"));
    }
}
