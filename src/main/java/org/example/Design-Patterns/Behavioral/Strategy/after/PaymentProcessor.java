package after;

/**
 * CONTEXT CLASS: Payment Processor
 * 
 * This is the context that uses the Strategy pattern.
 * Notice how SIMPLE this class is compared to the "before" version!
 * 
 * Key Improvements:
 * - No if-else statements
 * - No knowledge of specific payment methods
 * - Can change strategy at runtime
 * - Delegates all work to the strategy
 * - Single Responsibility: Just coordinates, doesn't implement
 * 
 * Compare this to the "before" version's 250+ lines of conditional logic!
 */
public class PaymentProcessor {
    
    private PaymentStrategy strategy;
    
    /**
     * Constructor injection: Provide strategy at creation
     */
    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Setter injection: Allow changing strategy at runtime
     * This is a key benefit of the Strategy pattern!
     */
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
        System.out.println("🔄 Payment method changed to: " + strategy.getPaymentMethodName());
    }
    
    /**
     * Process payment using the current strategy
     * No conditional logic needed - polymorphism handles it!
     */
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PROCESSING PAYMENT: " + strategy.getPaymentMethodName());
        System.out.println("=".repeat(80));
        
        // Simply delegate to the strategy
        return strategy.processPayment(request);
    }
    
    /**
     * Calculate fee using the current strategy
     * We can easily access strategy behavior without processing!
     */
    public double calculateFee(double amount) {
        return strategy.calculateFee(amount);
    }
    
    /**
     * Validate using the current strategy
     */
    public boolean validate(PaymentRequest request) {
        return strategy.validate(request);
    }
    
    /**
     * Get current payment method name
     */
    public String getCurrentPaymentMethod() {
        return strategy.getPaymentMethodName();
    }
}
