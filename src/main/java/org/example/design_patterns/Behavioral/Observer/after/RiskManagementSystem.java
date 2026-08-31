package org.example.design_patterns.Behavioral.Observer.after;

/**
 * Concrete Observer - Risk Management System
 * 
 * This observer monitors portfolio risk based on price movements.
 * Demonstrates how new observers can be added without modifying existing code!
 */
public class RiskManagementSystem implements StockObserver {
    private double maxLossThreshold;
    private double portfolioValue;

    public RiskManagementSystem(double portfolioValue, double maxLossThreshold) {
        this.portfolioValue = portfolioValue;
        this.maxLossThreshold = maxLossThreshold;
    }

    @Override
    public void update(Stock stock, double oldPrice, double newPrice) {
        double changePercent = ((newPrice - oldPrice) / oldPrice) * 100;
        double portfolioImpact = (portfolioValue * changePercent) / 100;

        System.out.println("🛡️  [RISK MANAGEMENT] Assessing " + stock.getSymbol());
        System.out.println("   Portfolio Value: $" + String.format("%.2f", portfolioValue));
        System.out.println("   Price Change: " + String.format("%.2f%%", changePercent));
        System.out.println("   Portfolio Impact: $" + String.format("%.2f", portfolioImpact));

        if (Math.abs(changePercent) > maxLossThreshold) {
            System.out.println("   ⚠️  WARNING: Price movement exceeds risk threshold!");
            System.out.println("   Recommended: Review position and consider rebalancing");
        } else {
            System.out.println("   ✓ Risk level: ACCEPTABLE");
        }
        System.out.println();
    }

    @Override
    public String getObserverName() {
        return "RiskManagement";
    }
}
