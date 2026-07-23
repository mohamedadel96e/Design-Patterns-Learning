package after;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * CONCRETE STRATEGY: Credit Card Payment
 * 
 * Encapsulates ALL credit card payment logic in one place.
 * Fee: 2.9% + $0.30 (typical credit card processing fee)
 * 
 * Benefits:
 * - Single Responsibility: Only handles credit card payments
 * - Easy to test: Can test this class in isolation
 * - Easy to modify: Changes here don't affect other payment methods
 */
public class CreditCardStrategy implements PaymentStrategy {
    
    private static final Pattern CARD_PATTERN = Pattern.compile("^[0-9]{16}$");
    private static final Pattern CVV_PATTERN = Pattern.compile("^[0-9]{3,4}$");
    private static final double FEE_PERCENTAGE = 0.029; // 2.9%
    private static final double FEE_FIXED = 0.30;       // $0.30
    
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("💳 Processing Credit Card Payment...");
        
        // Validate
        if (!validate(request)) {
            return new PaymentResult(false, "Invalid credit card information");
        }
        
        // Calculate fee
        double fee = calculateFee(request.getAmount());
        double total = request.getAmount() + fee;
        
        // Display information
        System.out.println("   Card Number: **** **** **** " + request.getCardNumber().substring(12));
        System.out.println("   Holder: " + request.getCardHolderName());
        System.out.println("   Amount: $" + String.format("%.2f", request.getAmount()));
        System.out.println("   Processing Fee: $" + String.format("%.2f", fee) + " (2.9% + $0.30)");
        System.out.println("   Total Charged: $" + String.format("%.2f", total));
        
        // Simulate payment processing
        System.out.println("   → Connecting to payment gateway...");
        System.out.println("   → Authorizing transaction...");
        System.out.println("   → Charging card...");
        System.out.println("   ✓ Payment successful!");
        
        // Create result
        PaymentResult result = new PaymentResult(true, "Credit card payment processed successfully");
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
        if (request.getCardNumber() == null || !CARD_PATTERN.matcher(request.getCardNumber()).matches()) {
            System.out.println("   ✗ Invalid card number");
            return false;
        }
        if (request.getCvv() == null || !CVV_PATTERN.matcher(request.getCvv()).matches()) {
            System.out.println("   ✗ Invalid CVV");
            return false;
        }
        if (request.getCardHolderName() == null || request.getCardHolderName().trim().isEmpty()) {
            System.out.println("   ✗ Invalid card holder name");
            return false;
        }
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "CREDIT_CARD";
    }
    
    private String generateTransactionId() {
        return "CC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
