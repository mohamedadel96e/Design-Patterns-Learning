package after;

/**
 * Observer Interface - The contract all observers must implement
 * 
 * This defines the update method that will be called when the stock price changes.
 * Using an interface provides loose coupling - the Subject doesn't need to know
 * the concrete implementation details of observers.
 */
public interface StockObserver {
    /**
     * Called when the observed stock's price changes
     * 
     * @param stock The stock that changed
     * @param oldPrice The previous price
     * @param newPrice The new price
     */
    void update(Stock stock, double oldPrice, double newPrice);

    /**
     * Optional: Get observer name for display/logging purposes
     * Default implementation provides a generic name
     */
    default String getObserverName() {
        return this.getClass().getSimpleName();
    }
}
