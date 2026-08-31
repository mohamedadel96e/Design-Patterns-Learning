package org.example.solid_principles.open_closed_principle.after;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Cryptocurrency payment implementation
 * 
 * NEW PAYMENT METHOD: Added by EXTENSION, not by MODIFICATION!
 * 
 * Notice that we didn't need to modify:
 * - PaymentProcessor class
 * - Other payment method classes
 * - Existing tests
 * 
 * We simply created a new class that implements PaymentMethod interface
 * This is the Open/Closed Principle in action!
 */
public class CryptocurrencyPayment implements PaymentMethod {
    private String walletAddress;
    private String privateKey;
    private String cryptoType; // BTC, ETH, etc.
    
    public CryptocurrencyPayment(String walletAddress, String privateKey, String cryptoType) {
        this.walletAddress = walletAddress;
        this.privateKey = privateKey;
        this.cryptoType = cryptoType;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        System.out.println("💳 Processing Cryptocurrency Payment");
        System.out.println("   Wallet: " + walletAddress);
        System.out.println("   Type: " + cryptoType);
        System.out.println("   Amount: " + amount + " " + cryptoType);
        
        if (!isValid()) {
            return new PaymentResult(false, "Invalid cryptocurrency wallet information", null);
        }
        
        // Simulate payment processing
        System.out.println("   → Connecting to blockchain network...");
        System.out.println("   → Verifying wallet...");
        System.out.println("   → Broadcasting transaction...");
        System.out.println("   → Waiting for confirmations...");
        
        String transactionId = "CRYPTO-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("✅ Cryptocurrency payment successful!");
        
        return new PaymentResult(true, "Cryptocurrency payment processed", transactionId);
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Cryptocurrency (" + cryptoType + ")";
    }
    
    @Override
    public boolean isValid() {
        return walletAddress != null && 
               walletAddress.length() >= 26 &&
               privateKey != null && 
               !privateKey.isEmpty() &&
               cryptoType != null;
    }
}
