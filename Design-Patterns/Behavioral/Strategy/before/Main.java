package before;

/**
 * DEMONSTRATION: The Problem Without Strategy Pattern
 * 
 * This demonstrates a payment processing system with all logic
 * contained in one monolithic PaymentProcessor class.
 * 
 * Key Problems Demonstrated:
 * 1. Giant if-else chain that grows with each payment method
 * 2. Violates Open-Closed Principle (must modify to add new methods)
 * 3. Violates Single Responsibility Principle (does too much)
 * 4. Hard to test individual payment methods
 * 5. Code duplication across payment methods
 * 6. Cannot switch payment algorithms at runtime easily
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  PAYMENT PROCESSING SYSTEM - WITHOUT STRATEGY PATTERN         ║");
        System.out.println("║  (DEMONSTRATING THE PROBLEMS)                                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        // PROBLEM: Single monolithic processor handles everything
        PaymentProcessor processor = new PaymentProcessor();

        System.out.println("\n📋 Scenario: Customer trying different payment methods\n");

        // Test 1: Credit Card Payment
        System.out.println("─── Test 1: Credit Card Payment ───");
        PaymentRequest ccRequest = new PaymentRequest("CREDIT_CARD", 100.00, "USD");
        ccRequest.setCardNumber("4532123456789012");
        ccRequest.setCvv("123");
        ccRequest.setExpiryDate("12/25");
        ccRequest.setCardHolderName("John Doe");
        ccRequest.setCustomerEmail("john@example.com");
        
        PaymentResult result1 = processor.processPayment(ccRequest);
        System.out.println("\nResult: " + result1);
        sleep(1000);

        // Test 2: PayPal Payment
        System.out.println("\n\n─── Test 2: PayPal Payment ───");
        PaymentRequest ppRequest = new PaymentRequest("PAYPAL", 150.00, "USD");
        ppRequest.setPaypalEmail("john.doe@email.com");
        ppRequest.setPaypalPassword("securepass123");
        ppRequest.setCustomerEmail("john@example.com");
        
        PaymentResult result2 = processor.processPayment(ppRequest);
        System.out.println("\nResult: " + result2);
        sleep(1000);

        // Test 3: Cryptocurrency Payment
        System.out.println("\n\n─── Test 3: Cryptocurrency Payment ───");
        PaymentRequest cryptoRequest = new PaymentRequest("CRYPTOCURRENCY", 200.00, "USD");
        cryptoRequest.setCryptoType("Bitcoin");
        cryptoRequest.setWalletAddress("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        cryptoRequest.setCustomerEmail("john@example.com");
        
        PaymentResult result3 = processor.processPayment(cryptoRequest);
        System.out.println("\nResult: " + result3);
        sleep(1000);

        // Test 4: Bank Transfer
        System.out.println("\n\n─── Test 4: Bank Transfer ───");
        PaymentRequest bankRequest = new PaymentRequest("BANK_TRANSFER", 500.00, "USD");
        bankRequest.setAccountNumber("123456789012");
        bankRequest.setRoutingNumber("021000021");
        bankRequest.setBankName("Chase Bank");
        bankRequest.setCustomerEmail("john@example.com");
        
        PaymentResult result4 = processor.processPayment(bankRequest);
        System.out.println("\nResult: " + result4);

        // PROBLEM: To add Apple Pay, we'd need to modify PaymentProcessor!
        System.out.println("\n\n" + "=".repeat(80));
        System.out.println("💭 What if we want to add Apple Pay or Google Pay?");
        System.out.println("   → We MUST modify PaymentProcessor class");
        System.out.println("   → Add another if-else branch");
        System.out.println("   → Risk breaking existing payment methods");
        System.out.println("   → Violates Open-Closed Principle!");
        System.out.println("=".repeat(80));

        // Display problems summary
        System.out.println("\n\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  PROBLEMS WITH THIS APPROACH:                                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  ❌ IF-ELSE HELL: Giant conditional chain                     ║");
        System.out.println("║     Current: ~250 lines for 4 methods                         ║");
        System.out.println("║     With 10 methods: ~600+ lines in ONE class!                ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ❌ VIOLATES OPEN-CLOSED PRINCIPLE:                           ║");
        System.out.println("║     Must MODIFY existing code to add new payment methods      ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ❌ VIOLATES SINGLE RESPONSIBILITY:                           ║");
        System.out.println("║     PaymentProcessor does validation, calculation, processing ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ❌ HARD TO TEST:                                             ║");
        System.out.println("║     Can't test credit card logic without entire class         ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ❌ CODE DUPLICATION:                                         ║");
        System.out.println("║     Fee calculation logic duplicated in multiple places       ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ❌ NO RUNTIME FLEXIBILITY:                                   ║");
        System.out.println("║     Can't easily switch payment methods dynamically           ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ❌ MAINTENANCE NIGHTMARE:                                    ║");
        System.out.println("║     Changes to one method risk breaking others                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        System.out.println("\n💡 See the 'after' package for the Strategy pattern solution!");
        System.out.println("   The solution extracts each payment method into its own class,");
        System.out.println("   making the code maintainable, testable, and extensible!\n");
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
