package after;

import java.math.BigDecimal;

/**
 * Interface defining the contract for all payment methods
 * 
 * By using an interface, we enable:
 * - Open for Extension: New payment methods can be added
 * - Closed for Modification: Existing code doesn't need to change
 * - Polymorphism: All payment methods can be treated uniformly
 */
public interface PaymentMethod {
    /**
     * Processes a payment
     * 
     * @param amount The amount to charge
     * @return PaymentResult containing success status and details
     */
    PaymentResult processPayment(BigDecimal amount);
    
    /**
     * Gets the name of this payment method
     */
    String getPaymentMethodName();
    
    /**
     * Validates the payment method configuration
     */
    boolean isValid();
}
