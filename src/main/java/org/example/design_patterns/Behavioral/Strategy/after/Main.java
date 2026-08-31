package org.example.design_patterns.Behavioral.Strategy.after;

/**
 * DEMONSTRATION: The Solution With Strategy Pattern
 * 
 * This demonstrates a clean payment processing system using the Strategy pattern.
 * Each payment method is encapsulated in its own class.
 * 
 * Key Benefits Demonstrated:
 * 1. No if-else chains - polymorphism handles method selection
 * 2. Open-Closed Principle - add new payment methods without modifying existing code
 * 3. Single Responsibility - each class has one job
 * 4. Easy to test - can test each strategy independently
 * 5. Runtime flexibility - can switch strategies dynamically
 * 6. Clean, maintainable code - each file is small and focused
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  PAYMENT PROCESSING SYSTEM - WITH STRATEGY PATTERN            ║");
        System.out.println("║  (THE SOLUTION)                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        System.out.println("\n📋 Scenario: Customer trying different payment methods\n");

        // Test 1: Credit Card Payment
        System.out.println("─── Test 1: Credit Card Payment ───");
        PaymentRequest ccRequest = new PaymentRequest("CREDIT_CARD", 100.00, "USD");
        ccRequest.setCardNumber("4532123456789012");
        ccRequest.setCvv("123");
        ccRequest.setExpiryDate("12/25");
        ccRequest.setCardHolderName("John Doe");
        ccRequest.setCustomerEmail("john@example.com");
        
        // BENEFIT: Create processor with specific strategy
        PaymentProcessor processor = new PaymentProcessor(new CreditCardStrategy());
        PaymentResult result1 = processor.processPayment(ccRequest);
        System.out.println("\nResult: " + result1);
        sleep(1000);

        // Test 2: Switch to PayPal at RUNTIME
        System.out.println("\n\n─── Test 2: Customer Switches to PayPal ───");
        PaymentRequest ppRequest = new PaymentRequest("PAYPAL", 150.00, "USD");
        ppRequest.setPaypalEmail("john.doe@email.com");
        ppRequest.setPaypalPassword("securepass123");
        ppRequest.setCustomerEmail("john@example.com");
        
        // BENEFIT: Can change strategy at runtime!
        processor.setStrategy(new PayPalStrategy());
        PaymentResult result2 = processor.processPayment(ppRequest);
        System.out.println("\nResult: " + result2);
        sleep(1000);

        // Test 3: Switch to Cryptocurrency
        System.out.println("\n\n─── Test 3: Customer Switches to Cryptocurrency ───");
        PaymentRequest cryptoRequest = new PaymentRequest("CRYPTOCURRENCY", 200.00, "USD");
        cryptoRequest.setCryptoType("Bitcoin");
        cryptoRequest.setWalletAddress("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        cryptoRequest.setCustomerEmail("john@example.com");
        
        // BENEFIT: Easy strategy swap
        processor.setStrategy(new CryptoStrategy());
        PaymentResult result3 = processor.processPayment(cryptoRequest);
        System.out.println("\nResult: " + result3);
        sleep(1000);

        // Test 4: Switch to Bank Transfer
        System.out.println("\n\n─── Test 4: Customer Switches to Bank Transfer ───");
        PaymentRequest bankRequest = new PaymentRequest("BANK_TRANSFER", 500.00, "USD");
        bankRequest.setAccountNumber("123456789012");
        bankRequest.setRoutingNumber("021000021");
        bankRequest.setBankName("Chase Bank");
        bankRequest.setCustomerEmail("john@example.com");
        
        // BENEFIT: Yet another easy swap
        processor.setStrategy(new BankTransferStrategy());
        PaymentResult result4 = processor.processPayment(bankRequest);
        System.out.println("\nResult: " + result4);

        // BENEFIT: Can calculate fees without processing
        System.out.println("\n\n" + "=".repeat(80));
        System.out.println("💰 Fee Comparison for $1000 payment:");
        System.out.println("=".repeat(80));
        compareFees(1000.00);

        // BENEFIT: Adding a new payment method is EASY!
        System.out.println("\n\n" + "=".repeat(80));
        System.out.println("✨ Want to add Apple Pay or Google Pay?");
        System.out.println("   1. Create new class: ApplePayStrategy implements PaymentStrategy");
        System.out.println("   2. Implement the 4 interface methods");
        System.out.println("   3. Done! No existing code needs to change!");
        System.out.println("   → This follows the Open-Closed Principle perfectly");
        System.out.println("=".repeat(80));

        // Display benefits summary
        System.out.println("\n\n╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║  BENEFITS OF STRATEGY PATTERN:                                ║");
        System.out.println("╠════════════════════════════════════════════════════════════════╣");
        System.out.println("║  ✅ NO IF-ELSE CHAINS:                                        ║");
        System.out.println("║     PaymentProcessor is just ~40 lines of clean code!         ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ✅ OPEN-CLOSED PRINCIPLE:                                    ║");
        System.out.println("║     Add new payment methods without modifying existing code   ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ✅ SINGLE RESPONSIBILITY:                                    ║");
        System.out.println("║     Each strategy class has ONE job and does it well          ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ✅ EASY TO TEST:                                             ║");
        System.out.println("║     Test each payment strategy independently                  ║");
        System.out.println("║     Mock strategies easily for integration tests              ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ✅ RUNTIME FLEXIBILITY:                                      ║");
        System.out.println("║     Switch payment methods dynamically at any time            ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ✅ MAINTAINABLE:                                             ║");
        System.out.println("║     Each strategy is in its own small, focused file           ║");
        System.out.println("║     Changes are localized and safe                            ║");
        System.out.println("║                                                                ║");
        System.out.println("║  ✅ REUSABLE:                                                 ║");
        System.out.println("║     Strategies can be used by different contexts              ║");
        System.out.println("║     Share strategies across different parts of application    ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        System.out.println("\n🎉 Strategy Pattern makes the code clean, flexible, and maintainable!\n");
    }

    private static void compareFees(double amount) {
        PaymentStrategy[] strategies = {
            new CreditCardStrategy(),
            new PayPalStrategy(),
            new CryptoStrategy(),
            new BankTransferStrategy()
        };

        for (PaymentStrategy strategy : strategies) {
            double fee = strategy.calculateFee(amount);
            double total = amount + fee;
            System.out.printf("   %-20s Fee: $%6.2f  Total: $%8.2f%n", 
                             strategy.getPaymentMethodName(), fee, total);
        }
    }

    private static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
