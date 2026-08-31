package org.example.design_patterns.Behavioral.Strategy.after;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * CONCRETE STRATEGY: PayPal Payment
 * 
 * Encapsulates ALL PayPal payment logic in one place.
 * Fee: 3.4% + $0.30 (typical PayPal fee)
 * 
 * Benefits:
 * - Independent: Changes to PayPal don't affect other strategies
 * - Testable: Easy to unit test in isolation
 * - Focused: Only contains PayPal-specific logic
 */
public class PayPalStrategy implements PaymentStrategy {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final double FEE_PERCENTAGE = 0.034; // 3.4%
    private static final double FEE_FIXED = 0.30;       // $0.30
    
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("🅿️ Processing PayPal Payment...");
        
        // Validate
        if (!validate(request)) {
            return new PaymentResult(false, "Invalid PayPal information");
        }
        
        // Calculate fee
        double fee = calculateFee(request.getAmount());
        double total = request.getAmount() + fee;
        
        // Display information
        System.out.println("   PayPal Account: " + request.getPaypalEmail());
        System.out.println("   Amount: $" + String.format("%.2f", request.getAmount()));
        System.out.println("   Processing Fee: $" + String.format("%.2f", fee) + " (3.4% + $0.30)");
        System.out.println("   Total Charged: $" + String.format("%.2f", total));
        
        // Simulate PayPal processing
        System.out.println("   → Redirecting to PayPal...");
        System.out.println("   → Authenticating user...");
        System.out.println("   → Processing transaction...");
        System.out.println("   ✓ Payment successful!");
        
        // Create result
        PaymentResult result = new PaymentResult(true, "PayPal payment processed successfully");
        result.setTransactionId(generateTransactionId());
        result.setPaymentMethod(getPaymentMethodName());
        result.setAmount(request.getAmount());
        result.setFee(fee);
        result.setTotalCharged(total);
        
        return result;
    }
    
    @Override
    public double calculateFee(double amount) {
        return amount * FEE_PERCENTAGE + FEE_FIXED;
    }
    
    @Override
    public boolean validate(PaymentRequest request) {
        if (request.getPaypalEmail() == null || !EMAIL_PATTERN.matcher(request.getPaypalEmail()).matches()) {
            System.out.println("   ✗ Invalid PayPal email");
            return false;
        }
        if (request.getPaypalPassword() == null || request.getPaypalPassword().length() < 6) {
            System.out.println("   ✗ Invalid PayPal password");
            return false;
        }
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "PAYPAL";
    }
    
    private String generateTransactionId() {
        return "PP-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
