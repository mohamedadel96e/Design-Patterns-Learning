package after;

import java.math.BigDecimal;

/**
 * AFTER: Following Open/Closed Principle
 * 
 * The PaymentProcessor class is:
 * - OPEN for extension: New payment methods can be added
 * - CLOSED for modification: PaymentProcessor never needs to change
 * 
 * Benefits:
 * - Adding new payment methods doesn't require modifying existing code
 * - No risk of breaking existing functionality
 * - Easy to test new payment methods in isolation
 * - Clear separation of concerns
 * - Follows polymorphism and strategy pattern
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== AFTER: Following Open/Closed Principle ===\n");
        
        PaymentProcessor processor = new PaymentProcessor();
        
        // Scenario 1: Credit Card Payment
        System.out.println("Scenario 1: Credit Card Payment");
        System.out.println("══════════════════════════════════════════════════════");
        PaymentMethod creditCard = new CreditCardPayment(
            "4532123456789012",
            "John Doe",
            "12/25",
            "123"
        );
        processor.processPayment(creditCard, new BigDecimal("99.99"));
        
        // Scenario 2: PayPal Payment
        System.out.println("\n\nScenario 2: PayPal Payment");
        System.out.println("══════════════════════════════════════════════════════");
        PaymentMethod paypal = new PayPalPayment(
            "user@example.com",
            "securepassword"
        );
        processor.processPayment(paypal, new BigDecimal("149.50"));
        
        // Scenario 3: Bank Transfer
        System.out.println("\n\nScenario 3: Bank Transfer");
        System.out.println("══════════════════════════════════════════════════════");
        PaymentMethod bankTransfer = new BankTransferPayment(
            "12345678",
            "987654321",
            "Jane Smith"
        );
        processor.processPayment(bankTransfer, new BigDecimal("500.00"));
        
        // Scenario 4: Cryptocurrency Payment (NEW - Added without modifying PaymentProcessor!)
        System.out.println("\n\nScenario 4: Cryptocurrency Payment");
        System.out.println("══════════════════════════════════════════════════════");
        PaymentMethod crypto = new CryptocurrencyPayment(
            "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa",
            "private-key-here",
            "BTC"
        );
        processor.processPayment(crypto, new BigDecimal("0.05"));
        
        // Scenario 5: Apple Pay Payment (ANOTHER NEW - Still no modification to PaymentProcessor!)
        System.out.println("\n\nScenario 5: Apple Pay Payment");
        System.out.println("══════════════════════════════════════════════════════");
        PaymentMethod applePay = new ApplePayPayment(
            "device-account-1234",
            "apple-pay-token-xyz"
        );
        processor.processPayment(applePay, new BigDecimal("75.00"));
        
        // Scenario 6: Invalid Payment
        System.out.println("\n\nScenario 6: Invalid Credit Card");
        System.out.println("══════════════════════════════════════════════════════");
        PaymentMethod invalidCard = new CreditCardPayment(
            "123", // Invalid card number
            "Bob Wilson",
            "12/25",
            "12"  // Invalid CVV
        );
        processor.processPayment(invalidCard, new BigDecimal("50.00"));
        
        System.out.println("\n\n✅ BENEFITS OF THIS APPROACH:");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("✓ PaymentProcessor is CLOSED for modification");
        System.out.println("✓ System is OPEN for extension (new payment methods)");
        System.out.println("✓ Added Cryptocurrency and Apple Pay without changing existing code");
        System.out.println("✓ No risk of breaking existing payment methods");
        System.out.println("✓ Each payment method is tested independently");
        System.out.println("✓ Easy to add more payment methods (Google Pay, Gift Cards, etc.)");
        System.out.println("✓ Follows Open/Closed Principle!");
        
        System.out.println("\n🎓 KEY LEARNINGS:");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("• Use interfaces to define contracts");
        System.out.println("• Use polymorphism instead of if-else/switch statements");
        System.out.println("• Design for extension from the beginning");
        System.out.println("• New features = new classes, not modified classes");
        System.out.println("• This is also the Strategy Pattern!");
    }
}
