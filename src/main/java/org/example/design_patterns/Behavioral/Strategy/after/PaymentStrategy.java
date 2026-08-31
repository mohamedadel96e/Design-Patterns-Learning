package org.example.design_patterns.Behavioral.Strategy.after;

/**
 * STRATEGY INTERFACE
 * 
 * Defines the contract that all payment strategies must implement.
 * This is the key to the Strategy pattern - all concrete strategies
 * implement this interface, making them interchangeable.
 * 
 * Benefits:
 * - Type safety: All strategies have the same methods
 * - Polymorphism: Context can treat all strategies uniformly
 * - Extensibility: New strategies just implement this interface
 */
public interface PaymentStrategy {
    
    /**
     * Process the payment using this strategy's algorithm
     * @param request Payment request containing all necessary data
     * @return Result of the payment processing
     */
    PaymentResult processPayment(PaymentRequest request);
    
    /**
     * Calculate the processing fee for this payment method
     * @param amount The payment amount
     * @return The fee amount
     */
    double calculateFee(double amount);
    
    /**
     * Validate the payment request for this payment method
     * @param request Payment request to validate
     * @return true if valid, false otherwise
     */
    boolean validate(PaymentRequest request);
    
    /**
     * Get the name of this payment method
     * @return Payment method name
     */
    String getPaymentMethodName();
}
