package after;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete Subject - The Observable Stock
 * 
 * This class represents a stock that can be observed. It maintains a list of
 * observers and notifies them whenever the stock price changes.
 * 
 * This implements the Subject role in the Observer pattern:
 * - Maintains a list of observers
 * - Provides attach/detach methods
 * - Notifies all observers when state changes
 */
public class Stock {
    private String symbol;
    private String companyName;
    private double price;
    private double volume;
    
    // The list of observers watching this stock
    private List<StockObserver> observers;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
        this.volume = 0;
        this.observers = new ArrayList<>();
    }

    /**
     * Attach an observer to this stock
     * The observer will be notified of all future price changes
     */
    public void attach(StockObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("✅ Observer attached: " + observer.getObserverName());
        }
    }

    /**
     * Detach an observer from this stock
     * The observer will no longer receive notifications
     */
    public void detach(StockObserver observer) {
        if (observers.remove(observer)) {
            System.out.println("❌ Observer detached: " + observer.getObserverName());
        }
    }

    /**
     * Notify all attached observers of a price change
     * This is called automatically when setPrice() is called
     */
    private void notifyObservers(double oldPrice, double newPrice) {
        System.out.println("\n📢 Notifying " + observers.size() + " observer(s) of price change...\n");
        
        for (StockObserver observer : observers) {
            observer.update(this, oldPrice, newPrice);
        }
    }

    /**
     * Set a new price for the stock
     * This automatically notifies all observers
     */
    public void setPrice(double newPrice) {
        if (this.price != newPrice) {
            double oldPrice = this.price;
            this.price = newPrice;
            // Automatically notify all observers
            notifyObservers(oldPrice, newPrice);
        }
    }

    // Getters
    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getObserverCount() {
        return observers.size();
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f [%d observers]", 
            companyName, symbol, price, observers.size());
    }
}
