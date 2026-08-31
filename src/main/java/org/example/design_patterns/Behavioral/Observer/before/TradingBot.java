package org.example.design_patterns.Behavioral.Observer.before;

/**
 * Automated trading bot that executes trades based on price movements
 */
public class TradingBot {
    private String botName;
    private double buyThreshold;
    private double sellThreshold;

    public TradingBot(String botName, double buyThreshold, double sellThreshold) {
        this.botName = botName;
        this.buyThreshold = buyThreshold;
        this.sellThreshold = sellThreshold;
    }

    public void checkAndExecuteTrades(Stock stock, double oldPrice, double newPrice) {
        System.out.println("🤖 [TRADING BOT: " + botName + "] Analyzing " + stock.getSymbol());
        
        if (newPrice < oldPrice * (1 - buyThreshold / 100)) {
            System.out.println("   ✅ BUY ORDER EXECUTED: Price dropped to $" + String.format("%.2f", newPrice));
            System.out.println("   Quantity: 100 shares");
        } else if (newPrice > oldPrice * (1 + sellThreshold / 100)) {
            System.out.println("   💰 SELL ORDER EXECUTED: Price rose to $" + String.format("%.2f", newPrice));
            System.out.println("   Quantity: 100 shares");
        } else {
            System.out.println("   ⏸ HOLD: No action needed");
        }
    }
}
