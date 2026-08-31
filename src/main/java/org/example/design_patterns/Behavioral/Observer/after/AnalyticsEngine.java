package org.example.design_patterns.Behavioral.Observer.after;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Observer - Analytics Engine
 * 
 * This observer logs all price changes for historical analysis and reporting.
 * Demonstrates how observers can maintain their own state based on updates.
 */
public class AnalyticsEngine implements StockObserver {
    private List<PriceDataPoint> priceHistory;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public AnalyticsEngine() {
        this.priceHistory = new ArrayList<>();
    }

    @Override
    public void update(Stock stock, double oldPrice, double newPrice) {
        PriceDataPoint dataPoint = new PriceDataPoint(
            stock.getSymbol(),
            oldPrice,
            newPrice,
            stock.getVolume(),
            LocalDateTime.now()
        );
        
        priceHistory.add(dataPoint);
        logToConsole(stock, dataPoint);
    }

    private void logToConsole(Stock stock, PriceDataPoint dataPoint) {
        System.out.println("📊 [ANALYTICS ENGINE] Logging data for " + stock.getSymbol());
        System.out.println("   Timestamp: " + dataPoint.timestamp.format(formatter));
        System.out.println("   Old Price: $" + String.format("%.2f", dataPoint.oldPrice));
        System.out.println("   New Price: $" + String.format("%.2f", dataPoint.newPrice));
        System.out.println("   Change: " + String.format("%.2f%%", dataPoint.getChangePercent()));
        System.out.println("   Volume: " + String.format("%.0f", dataPoint.volume));
        System.out.println("   Total records: " + priceHistory.size());
        System.out.println("   ✓ Data persisted to analytics database");
        System.out.println();
    }

    public void printReport() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ANALYTICS REPORT - Price History");
        System.out.println("=".repeat(70));
        System.out.println("Total data points: " + priceHistory.size());
        System.out.println();
        
        for (int i = 0; i < priceHistory.size(); i++) {
            PriceDataPoint dp = priceHistory.get(i);
            System.out.println((i + 1) + ". " + dp.symbol + " at " + 
                             dp.timestamp.format(formatter) + 
                             " | $" + String.format("%.2f", dp.oldPrice) + 
                             " → $" + String.format("%.2f", dp.newPrice) + 
                             " (" + String.format("%.2f%%", dp.getChangePercent()) + ")");
        }
        System.out.println("=".repeat(70) + "\n");
    }

    @Override
    public String getObserverName() {
        return "AnalyticsEngine";
    }

    /**
     * Inner class to represent a price data point
     */
    private static class PriceDataPoint {
        String symbol;
        double oldPrice;
        double newPrice;
        double volume;
        LocalDateTime timestamp;

        PriceDataPoint(String symbol, double oldPrice, double newPrice, double volume, LocalDateTime timestamp) {
            this.symbol = symbol;
            this.oldPrice = oldPrice;
            this.newPrice = newPrice;
            this.volume = volume;
            this.timestamp = timestamp;
        }

        double getChangePercent() {
            return ((newPrice - oldPrice) / oldPrice) * 100;
        }
    }
}
