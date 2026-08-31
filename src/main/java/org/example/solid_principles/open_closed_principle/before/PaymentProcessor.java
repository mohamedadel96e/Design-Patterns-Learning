package org.example.solid_principles.open_closed_principle.before;

import java.math.BigDecimal;

/**
 * PROBLEM: This class violates the Open/Closed Principle!
 * 
 * Every time we need to add a new payment method, we must:
 * 1. MODIFY this class (violates "closed for modification")
 * 2. Add new if-else conditions
 * 3. Risk breaking existing payment methods
 * 4. Retest ALL payment methods
 * 
 * This leads to:
 * - High maintenance cost
 * - Risk of introducing bugs
 * - Code becomes harder to understand as it grows
 * - Difficult to extend
 */
public class PaymentProcessor {
    
    /**
     * Processes a payment based on the payment method type
     * 
     * PROBLEM: This method needs to be MODIFIED every time
     * we add a new payment method!
     */
    public boolean processPayment(String paymentMethod, BigDecimal amount, String accountInfo) {
        System.out.println("\n💳 Processing " + paymentMethod + " payment...");
        System.out.println("   Amount: $" + amount);
        
        // PROBLEM: Using if-else/switch for different payment types
        // This requires modification when adding new payment methods!
        
        if (paymentMethod.equals("CREDIT_CARD")) {
            return processCreditCardPayment(amount, accountInfo);
            
        } else if (paymentMethod.equals("PAYPAL")) {
            return processPayPalPayment(amount, accountInfo);
            
        } else if (paymentMethod.equals("BANK_TRANSFER")) {
            return processBankTransferPayment(amount, accountInfo);
            
        } else if (paymentMethod.equals("CRYPTOCURRENCY")) {
            // NEW: Had to MODIFY existing code to add this!
            return processCryptoPayment(amount, accountInfo);
            
        } else {
            System.out.println("❌ Unknown payment method: " + paymentMethod);
            return false;
        }
        
        // What if we need to add:
        // - Apple Pay?
        // - Google Pay?
        // - Wire Transfer?
        // - Gift Card?
        // We'd have to keep modifying this method!
    }
    
    /**
     * Credit Card payment processing
     */
    private boolean processCreditCardPayment(BigDecimal amount, String cardNumber) {
        System.out.println("   Card Number: " + maskCardNumber(cardNumber));
        
        // Validate card number
        if (!isValidCardNumber(cardNumber)) {
            System.out.println("❌ Invalid card number");
            return false;
        }
        
        // Process payment
        System.out.println("   → Contacting payment gateway...");
        System.out.println("   → Authorizing transaction...");
        System.out.println("   → Processing charge...");
        
        // Simulate success
        System.out.println("✅ Credit card payment successful!");
        return true;
    }
    
    /**
     * PayPal payment processing
     */
    private boolean processPayPalPayment(BigDecimal amount, String email) {
        System.out.println("   PayPal Email: " + email);
        
        // Validate email
        if (!isValidEmail(email)) {
            System.out.println("❌ Invalid email address");
            return false;
        }
        
        // Process payment
        System.out.println("   → Connecting to PayPal API...");
        System.out.println("   → Authenticating user...");
        System.out.println("   → Processing payment...");
        
        // Simulate success
        System.out.println("✅ PayPal payment successful!");
        return true;
    }
    
    /**
     * Bank Transfer payment processing
     */
    private boolean processBankTransferPayment(BigDecimal amount, String accountNumber) {
        System.out.println("   Account Number: " + maskAccountNumber(accountNumber));
        
        // Validate account
        if (!isValidAccountNumber(accountNumber)) {
            System.out.println("❌ Invalid account number");
            return false;
        }
        
        // Process payment
        System.out.println("   → Connecting to banking network...");
        System.out.println("   → Verifying account...");
        System.out.println("   → Initiating transfer...");
        
        // Simulate success
        System.out.println("✅ Bank transfer initiated!");
        return true;
    }
    
    /**
     * Cryptocurrency payment processing
     * NEW METHOD: Had to add this method and modify processPayment()!
     */
    private boolean processCryptoPayment(BigDecimal amount, String walletAddress) {
        System.out.println("   Wallet Address: " + walletAddress);
        
        // Validate wallet
        if (!isValidWalletAddress(walletAddress)) {
            System.out.println("❌ Invalid wallet address");
            return false;
        }
        
        // Process payment
        System.out.println("   → Connecting to blockchain network...");
        System.out.println("   → Verifying wallet...");
        System.out.println("   → Broadcasting transaction...");
        
        // Simulate success
        System.out.println("✅ Cryptocurrency payment successful!");
        return true;
    }
    
    // Validation helper methods
    private boolean isValidCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.length() >= 13 && cardNumber.length() <= 19;
    }
    
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
    
    private boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && accountNumber.length() >= 8;
    }
    
    private boolean isValidWalletAddress(String walletAddress) {
        return walletAddress != null && walletAddress.length() >= 26;
    }
    
    // Masking helper methods
    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) return "****";
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
    
    private String maskAccountNumber(String accountNumber) {
        if (accountNumber.length() < 4) return "****";
        return "****" + accountNumber.substring(accountNumber.length() - 4);
    }
}
