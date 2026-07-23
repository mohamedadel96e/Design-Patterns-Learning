package after;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Apple Pay payment implementation
 * 
 * ANOTHER NEW PAYMENT METHOD: Also added by EXTENSION!
 * 
 * This demonstrates how easy it is to add new payment methods
 * without modifying any existing code.
 */
public class ApplePayPayment implements PaymentMethod {
    private String deviceAccountNumber;
    private String applePayToken;
    
    public ApplePayPayment(String deviceAccountNumber, String applePayToken) {
        this.deviceAccountNumber = deviceAccountNumber;
        this.applePayToken = applePayToken;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        System.out.println("💳 Processing Apple Pay Payment");
        System.out.println("   Device Account: " + maskDeviceAccount(deviceAccountNumber));
        System.out.println("   Amount: $" + amount);
        
        if (!isValid()) {
            return new PaymentResult(false, "Invalid Apple Pay token", null);
        }
        
        // Simulate payment processing
        System.out.println("   → Connecting to Apple Pay servers...");
        System.out.println("   → Validating token...");
        System.out.println("   → Processing secure payment...");
        
        String transactionId = "AP-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("✅ Apple Pay payment successful!");
        
        return new PaymentResult(true, "Payment processed via Apple Pay", transactionId);
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Apple Pay";
    }
    
    @Override
    public boolean isValid() {
        return deviceAccountNumber != null && 
               !deviceAccountNumber.isEmpty() &&
               applePayToken != null && 
               !applePayToken.isEmpty();
    }
    
    private String maskDeviceAccount(String account) {
        if (account.length() < 4) return "****";
        return "****" + account.substring(account.length() - 4);
    }
}
