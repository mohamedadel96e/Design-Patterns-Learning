package before;

import java.util.UUID;
import java.util.regex.Pattern;

/**
 * PROBLEM: Payment Processor with if-else hell
 * 
 * This class demonstrates the issues WITHOUT the Strategy pattern:
 * 
 * 1. CONDITIONAL COMPLEXITY: Giant if-else chain that grows with each payment method
 * 2. VIOLATION OF OCP: Must modify this class to add new payment methods
 * 3. VIOLATION OF SRP: This class does too much - validates, calculates fees, processes
 * 4. HARD TO TEST: Can't test individual payment methods in isolation
 * 5. CODE DUPLICATION: Common logic repeated across branches
 * 6. MAINTENANCE NIGHTMARE: Changes to one payment method risk breaking others
 */
public class PaymentProcessor {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern CARD_PATTERN = Pattern.compile("^[0-9]{16}$");
    private static final Pattern CVV_PATTERN = Pattern.compile("^[0-9]{3,4}$");

    /**
     * THE PROBLEM: One massive method handling all payment types
     * As we add more payment methods, this becomes unmaintainable!
     */
    public PaymentResult processPayment(PaymentRequest request) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("PROCESSING PAYMENT: " + request.getPaymentMethod());
        System.out.println("=".repeat(80));

        // PROBLEM: Giant if-else chain
        if (request.getPaymentMethod().equals("CREDIT_CARD")) {
            return processCreditCard(request);
            
        } else if (request.getPaymentMethod().equals("PAYPAL")) {
            return processPayPal(request);
            
        } else if (request.getPaymentMethod().equals("CRYPTOCURRENCY")) {
            return processCryptocurrency(request);
            
        } else if (request.getPaymentMethod().equals("BANK_TRANSFER")) {
            return processBankTransfer(request);
            
        } else {
            PaymentResult result = new PaymentResult(false, "Unknown payment method: " + request.getPaymentMethod());
            result.setPaymentMethod(request.getPaymentMethod());
            return result;
        }
    }

    /**
     * Credit Card Processing Logic
     * Fee: 2.9% + $0.30
     */
    private PaymentResult processCreditCard(PaymentRequest request) {
        System.out.println("💳 Processing Credit Card Payment...");
        
        // Validation
        if (!validateCreditCard(request)) {
            return new PaymentResult(false, "Invalid credit card information");
        }
        
        // Calculate fee
        double fee = request.getAmount() * 0.029 + 0.30;
        double total = request.getAmount() + fee;
        
        System.out.println("   Card Number: **** **** **** " + request.getCardNumber().substring(12));
        System.out.println("   Holder: " + request.getCardHolderName());
        System.out.println("   Amount: $" + String.format("%.2f", request.getAmount()));
        System.out.println("   Processing Fee: $" + String.format("%.2f", fee) + " (2.9% + $0.30)");
        System.out.println("   Total Charged: $" + String.format("%.2f", total));
        
        // Simulate payment processing
        System.out.println("   → Connecting to payment gateway...");
        System.out.println("   → Authorizing transaction...");
        System.out.println("   → Charging card...");
        System.out.println("   ✓ Payment successful!");
        
        PaymentResult result = new PaymentResult(true, "Credit card payment processed successfully");
        result.setTransactionId(generateTransactionId("CC"));
        result.setPaymentMethod("CREDIT_CARD");
        result.setAmount(request.getAmount());
        result.setFee(fee);
        result.setTotalCharged(total);
        
        return result;
    }

    private boolean validateCreditCard(PaymentRequest request) {
        if (request.getCardNumber() == null || !CARD_PATTERN.matcher(request.getCardNumber()).matches()) {
            System.out.println("   ✗ Invalid card number");
            return false;
        }
        if (request.getCvv() == null || !CVV_PATTERN.matcher(request.getCvv()).matches()) {
            System.out.println("   ✗ Invalid CVV");
            return false;
        }
        if (request.getCardHolderName() == null || request.getCardHolderName().trim().isEmpty()) {
            System.out.println("   ✗ Invalid card holder name");
            return false;
        }
        return true;
    }

    /**
     * PayPal Processing Logic
     * Fee: 3.4% + $0.30
     */
    private PaymentResult processPayPal(PaymentRequest request) {
        System.out.println("🅿️ Processing PayPal Payment...");
        
        // Validation
        if (!validatePayPal(request)) {
            return new PaymentResult(false, "Invalid PayPal information");
        }
        
        // Calculate fee
        double fee = request.getAmount() * 0.034 + 0.30;
        double total = request.getAmount() + fee;
        
        System.out.println("   PayPal Account: " + request.getPaypalEmail());
        System.out.println("   Amount: $" + String.format("%.2f", request.getAmount()));
        System.out.println("   Processing Fee: $" + String.format("%.2f", fee) + " (3.4% + $0.30)");
        System.out.println("   Total Charged: $" + String.format("%.2f", total));
        
        // Simulate PayPal processing
        System.out.println("   → Redirecting to PayPal...");
        System.out.println("   → Authenticating user...");
        System.out.println("   → Processing transaction...");
        System.out.println("   ✓ Payment successful!");
        
        PaymentResult result = new PaymentResult(true, "PayPal payment processed successfully");
        result.setTransactionId(generateTransactionId("PP"));
        result.setPaymentMethod("PAYPAL");
        result.setAmount(request.getAmount());
        result.setFee(fee);
        result.setTotalCharged(total);
        
        return result;
    }

    private boolean validatePayPal(PaymentRequest request) {
        if (request.getPaypalEmail() == null || !EMAIL_PATTERN.matcher(request.getPaypalEmail()).matches()) {
            System.out.println("   ✗ Invalid PayPal email");
            return false;
        }
        if (request.getPaypalPassword() == null || request.getPaypalPassword().length() < 6) {
            System.out.println("   ✗ Invalid PayPal password");
            return false;
        }
        return true;
    }

    /**
     * Cryptocurrency Processing Logic
     * Fee: 1% (lower fee, higher volatility)
     */
    private PaymentResult processCryptocurrency(PaymentRequest request) {
        System.out.println("₿ Processing Cryptocurrency Payment...");
        
        // Validation
        if (!validateCrypto(request)) {
            return new PaymentResult(false, "Invalid cryptocurrency information");
        }
        
        // Calculate fee
        double fee = request.getAmount() * 0.01;
        double total = request.getAmount() + fee;
        
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
        
        PaymentResult result = new PaymentResult(true, "Cryptocurrency payment processed successfully");
        result.setTransactionId(generateTransactionId("CRYPTO"));
        result.setPaymentMethod("CRYPTOCURRENCY");
        result.setAmount(request.getAmount());
        result.setFee(fee);
        result.setTotalCharged(total);
        
        return result;
    }

    private boolean validateCrypto(PaymentRequest request) {
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

    /**
     * Bank Transfer Processing Logic
     * Fee: $5.00 flat fee
     */
    private PaymentResult processBankTransfer(PaymentRequest request) {
        System.out.println("🏦 Processing Bank Transfer...");
        
        // Validation
        if (!validateBankTransfer(request)) {
            return new PaymentResult(false, "Invalid bank account information");
        }
        
        // Calculate fee
        double fee = 5.00; // Flat fee
        double total = request.getAmount() + fee;
        
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
        
        PaymentResult result = new PaymentResult(true, "Bank transfer initiated successfully");
        result.setTransactionId(generateTransactionId("ACH"));
        result.setPaymentMethod("BANK_TRANSFER");
        result.setAmount(request.getAmount());
        result.setFee(fee);
        result.setTotalCharged(total);
        
        return result;
    }

    private boolean validateBankTransfer(PaymentRequest request) {
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

    /**
     * PROBLEM: What if we want to calculate fee without processing?
     * We can't easily reuse the fee calculation logic!
     */
    public double calculateFee(String paymentMethod, double amount) {
        // More duplication of the fee logic!
        switch (paymentMethod) {
            case "CREDIT_CARD":
                return amount * 0.029 + 0.30;
            case "PAYPAL":
                return amount * 0.034 + 0.30;
            case "CRYPTOCURRENCY":
                return amount * 0.01;
            case "BANK_TRANSFER":
                return 5.00;
            default:
                return 0.0;
        }
    }

    private String generateTransactionId(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
