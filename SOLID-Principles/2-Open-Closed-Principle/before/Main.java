package before;

import java.math.BigDecimal;

/**
 * BEFORE: Violating Open/Closed Principle
 *
 * The PaymentProcessor class must be MODIFIED every time we add a new payment method.
 * This violates the Open/Closed Principle which states that classes should be:
 * - OPEN for extension
 * - CLOSED for modification
 *
 * Problems:
 * - Adding new payment methods requires modifying existing code
 * - Risk of breaking existing functionality
 * - Hard to maintain as more payment methods are added
 * - Difficult to test new features without affecting existing ones
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== BEFORE: Violating Open/Closed Principle ===\n");
        
        PaymentProcessor processor = new PaymentProcessor();
        
        System.out.println("Scenario 1: Processing Credit Card Payment");
        System.out.println("══════════════════════════════════════════════");
        processor.processPayment("CREDIT_CARD", new BigDecimal("99.99"), "1234567890123456");
        
        System.out.println("\nScenario 2: Processing PayPal Payment");
        System.out.println("══════════════════════════════════════════════");
        processor.processPayment("PAYPAL", new BigDecimal("149.50"), "user@example.com");
        
        System.out.println("\nScenario 3: Processing Bank Transfer");
        System.out.println("══════════════════════════════════════════════");
        processor.processPayment("BANK_TRANSFER", new BigDecimal("500.00"), "12345678");
        
        System.out.println("\nScenario 4: Processing Cryptocurrency Payment");
        System.out.println("══════════════════════════════════════════════");
        processor.processPayment("CRYPTOCURRENCY", new BigDecimal("0.05"), "1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        
        System.out.println("\nScenario 5: Unknown Payment Method");
        System.out.println("══════════════════════════════════════════════");
        processor.processPayment("APPLE_PAY", new BigDecimal("75.00"), "apple-pay-token");
        
        System.out.println("\n\n❌ PROBLEMS WITH THIS APPROACH:");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("• PaymentProcessor must be MODIFIED to add new payment methods");
        System.out.println("• If-else chain grows with each new payment type");
        System.out.println("• Risk of breaking existing payment methods when adding new ones");
        System.out.println("• All payment methods must be retested after each modification");
        System.out.println("• Violates Open/Closed Principle!");
        System.out.println("\n💡 WHAT IF we need to add:");
        System.out.println("   - Apple Pay");
        System.out.println("   - Google Pay");
        System.out.println("   - Gift Cards");
        System.out.println("   - Wire Transfer");
        System.out.println("   → We'd have to keep modifying PaymentProcessor!");
    }
}
