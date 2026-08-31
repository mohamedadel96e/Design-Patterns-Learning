package org.example.solid_principles.open_closed_principle.after;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Bank Transfer payment implementation
 * 
 * This class is CLOSED for modification but can be used
 * to EXTEND the system's payment capabilities
 */
public class BankTransferPayment implements PaymentMethod {
    private String accountNumber;
    private String routingNumber;
    private String accountHolderName;
    
    public BankTransferPayment(String accountNumber, String routingNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
        this.accountHolderName = accountHolderName;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        System.out.println("💳 Processing Bank Transfer");
        System.out.println("   Account: " + maskAccountNumber(accountNumber));
        System.out.println("   Holder: " + accountHolderName);
        System.out.println("   Amount: $" + amount);
        
        if (!isValid()) {
            return new PaymentResult(false, "Invalid bank account information", null);
        }
        
        // Simulate payment processing
        System.out.println("   → Connecting to banking network...");
        System.out.println("   → Verifying account...");
        System.out.println("   → Initiating transfer...");
        
        String transactionId = "BT-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("✅ Bank transfer initiated!");
        
        return new PaymentResult(true, "Bank transfer initiated successfully", transactionId);
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Bank Transfer";
    }
    
    @Override
    public boolean isValid() {
        return accountNumber != null && 
               accountNumber.length() >= 8 &&
               routingNumber != null && 
               routingNumber.length() >= 9;
    }
    
    private String maskAccountNumber(String accountNumber) {
        if (accountNumber.length() < 4) return "****";
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }
}
