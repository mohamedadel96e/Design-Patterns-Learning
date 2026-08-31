package org.example.design_patterns.Behavioral.Observer.before;

/**
 * Stock entity - represents a single stock with its data
 * In the "before" version, this is just a data holder
 */
public class Stock {
    private String symbol;
    private String companyName;
    private double price;
    private double volume;

    public Stock(String symbol, String companyName, double price) {
        this.symbol = symbol;
        this.companyName = companyName;
        this.price = price;
        this.volume = 0;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - $%.2f", companyName, symbol, price);
    }
}
