package after;

import java.util.UUID;

/**
 * CONCRETE STRATEGY: Cryptocurrency Payment
 * 
 * Encapsulates ALL cryptocurrency payment logic in one place.
 * Fee: 1% (lower fee, higher volatility)
 * 
 * Benefits:
 * - Isolated: Crypto-specific logic doesn't pollute other classes
 * - Maintainable: Easy to update when crypto APIs change
 * - Extensible: Can easily support multiple crypto types
 */
public class CryptoStrategy implements PaymentStrategy {
    
    private static final double FEE_PERCENTAGE = 0.01; // 1% network fee
    
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("₿ Processing Cryptocurrency Payment...");
        
        // Validate
        if (!validate(request)) {
            return new PaymentResult(false, "Invalid cryptocurrency information");
        }
        
        // Calculate fee
        double fee = calculateFee(request.getAmount());
        double total = request.getAmount() + fee;
        
        // Display information
        System.out.println("   Crypto Type: " + request.getCryptoType());
        System.out.println("   Wallet: " + request.getWalletAddress().substring(0, 10) + "...");
        System.out.println("   Amount: $" + String.format("%.2f", request.getAmount()));
        System.out.println("   Network Fee: $" + String.format("%.2f", fee) + " (1%)");
        System.out.println("   Total: $" + String.format("%.2f", total));
        
        // Simulate crypto processing
        System.out.println("   → Broadcasting transaction to blockchain...");
        System.out.println("   → Waiting for confirmations...");
        System.out.println("   → Transaction confirmed!");
        System.out.println("   ✓ Payment successful!");
        
        // Create result
        PaymentResult result = new PaymentResult(true, "Cryptocurrency payment processed successfully");
        result.setTransactionId(generateTransactionId());
        result.setPaymentMethod(getPaymentMethodName());
        result.setAmount(request.getAmount());
        result.setFee(fee);
        result.setTotalCharged(total);
        
        return result;
    }
    
    @Override
    public double calculateFee(double amount) {
        return amount * FEE_PERCENTAGE;
    }
    
    @Override
    public boolean validate(PaymentRequest request) {
        if (request.getWalletAddress() == null || request.getWalletAddress().length() < 26) {
            System.out.println("   ✗ Invalid wallet address");
            return false;
        }
        if (request.getCryptoType() == null || request.getCryptoType().trim().isEmpty()) {
            System.out.println("   ✗ Invalid crypto type");
            return false;
        }
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "CRYPTOCURRENCY";
    }
    
    private String generateTransactionId() {
        return "CRYPTO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
