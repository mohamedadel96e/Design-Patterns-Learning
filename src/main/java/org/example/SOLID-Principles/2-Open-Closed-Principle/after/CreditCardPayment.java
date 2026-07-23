package after;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Credit Card payment implementation
 * 
 * This class is CLOSED for modification but can be used
 * to EXTEND the system's payment capabilities
 */
public class CreditCardPayment implements PaymentMethod {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
    
    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        System.out.println("💳 Processing Credit Card Payment");
        System.out.println("   Card: " + maskCardNumber(cardNumber));
        System.out.println("   Holder: " + cardHolderName);
        System.out.println("   Amount: $" + amount);
        
        if (!isValid()) {
            return new PaymentResult(false, "Invalid credit card information", null);
        }
        
        // Simulate payment processing
        System.out.println("   → Contacting payment gateway...");
        System.out.println("   → Authorizing transaction...");
        System.out.println("   → Processing charge...");
        
        String transactionId = "CC-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("✅ Credit card payment successful!");
        
        return new PaymentResult(true, "Payment processed successfully", transactionId);
    }
    
    @Override
    public String getPaymentMethodName() {
        return "Credit Card";
    }
    
    @Override
    public boolean isValid() {
        return cardNumber != null && 
               cardNumber.length() >= 13 && 
               cardNumber.length() <= 19 &&
               cvv != null && 
               cvv.length() >= 3;
    }
    
    private String maskCardNumber(String cardNumber) {
        if (cardNumber.length() < 4) return "****";
        return "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
    }
}
