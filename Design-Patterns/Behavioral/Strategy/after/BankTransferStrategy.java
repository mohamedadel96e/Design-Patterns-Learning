package after;

import java.util.UUID;

/**
 * CONCRETE STRATEGY: Bank Transfer Payment
 * 
 * Encapsulates ALL bank transfer logic in one place.
 * Fee: $5.00 flat fee (typical ACH fee)
 * 
 * Benefits:
 * - Clear: Bank transfer logic is not mixed with other payment methods
 * - Modular: Can be modified independently
 * - Reusable: Can be used by different contexts
 */
public class BankTransferStrategy implements PaymentStrategy {
    
    private static final double FLAT_FEE = 5.00; // $5.00 flat ACH fee
    
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("🏦 Processing Bank Transfer...");
        
        // Validate
        if (!validate(request)) {
            return new PaymentResult(false, "Invalid bank account information");
        }
        
        // Calculate fee
        double fee = calculateFee(request.getAmount());
        double total = request.getAmount() + fee;
        
        // Display information
        System.out.println("   Bank: " + request.getBankName());
        System.out.println("   Account: *****" + request.getAccountNumber().substring(request.getAccountNumber().length() - 4));
        System.out.println("   Amount: $" + String.format("%.2f", request.getAmount()));
        System.out.println("   Transfer Fee: $" + String.format("%.2f", fee) + " (flat)");
        System.out.println("   Total: $" + String.format("%.2f", total));
        
        // Simulate bank transfer
        System.out.println("   → Initiating ACH transfer...");
        System.out.println("   → Verifying account...");
        System.out.println("   → Processing transfer (may take 1-3 business days)...");
        System.out.println("   ✓ Transfer initiated!");
        
        // Create result
        PaymentResult result = new PaymentResult(true, "Bank transfer initiated successfully");
        result.setTransactionId(generateTransactionId());
        result.setPaymentMethod(getPaymentMethodName());
        result.setAmount(request.getAmount());
        result.setFee(fee);
        result.setTotalCharged(total);
        
        return result;
    }
    
    @Override
    public double calculateFee(double amount) {
        return FLAT_FEE; // Flat fee regardless of amount
    }
    
    @Override
    public boolean validate(PaymentRequest request) {
        if (request.getAccountNumber() == null || request.getAccountNumber().length() < 8) {
            System.out.println("   ✗ Invalid account number");
            return false;
        }
        if (request.getRoutingNumber() == null || request.getRoutingNumber().length() != 9) {
            System.out.println("   ✗ Invalid routing number");
            return false;
        }
        if (request.getBankName() == null || request.getBankName().trim().isEmpty()) {
            System.out.println("   ✗ Invalid bank name");
            return false;
        }
        return true;
    }
    
    @Override
    public String getPaymentMethodName() {
        return "BANK_TRANSFER";
    }
    
    private String generateTransactionId() {
        return "ACH-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
