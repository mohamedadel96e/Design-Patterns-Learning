package after;

import java.math.BigDecimal;

/**
 * SOLUTION: Payment Processor following Open/Closed Principle
 * 
 * This class is:
 * - OPEN for extension: New payment methods can be added easily
 * - CLOSED for modification: This class never needs to change
 * 
 * Benefits:
 * - No need to modify this class when adding new payment methods
 * - No risk of breaking existing functionality
 * - Easy to test
 * - Follows polymorphism
 */
public class PaymentProcessor {
    
    /**
     * Processes a payment using ANY payment method
     * 
     * Notice: This method works with the PaymentMethod interface,
     * not concrete implementations. This is polymorphism in action!
     * 
     * We can add 100 new payment methods and this code NEVER changes!
     */
    public PaymentResult processPayment(PaymentMethod paymentMethod, BigDecimal amount) {
        System.out.println("\n💰 Payment Processor");
        System.out.println("   Method: " + paymentMethod.getPaymentMethodName());
        System.out.println("   Amount: $" + amount);
        System.out.println("───────────────────────────────────────────────");
        
        // Validate amount
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("❌ Invalid amount");
            return new PaymentResult(false, "Amount must be greater than zero", null);
        }
        
        // Validate payment method
        if (!paymentMethod.isValid()) {
            System.out.println("❌ Invalid payment method configuration");
            return new PaymentResult(false, "Invalid payment method", null);
        }
        
        // Process the payment (polymorphism at work!)
        PaymentResult result = paymentMethod.processPayment(amount);
        
        // Log the result
        if (result.isSuccess()) {
            System.out.println("✅ Transaction ID: " + result.getTransactionId());
        } else {
            System.out.println("❌ Payment failed: " + result.getMessage());
        }
        
        return result;
    }
    
    /**
     * Processes a refund - also works with any payment method!
     */
    public PaymentResult processRefund(PaymentMethod paymentMethod, BigDecimal amount, String originalTransactionId) {
        System.out.println("\n🔄 Processing Refund");
        System.out.println("   Method: " + paymentMethod.getPaymentMethodName());
        System.out.println("   Amount: $" + amount);
        System.out.println("   Original Transaction: " + originalTransactionId);
        
        // Refund logic here...
        return new PaymentResult(true, "Refund processed", "RF-" + originalTransactionId);
    }
}
