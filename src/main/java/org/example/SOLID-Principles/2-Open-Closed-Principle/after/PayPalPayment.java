package after;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * PayPal payment implementation
 * 
 * This class is CLOSED for modification but can be used
 * to EXTEND the system's payment capabilities
 */
public class PayPalPayment implements PaymentMethod {
    private String email;
    private String password;
    
    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    @Override
    public PaymentResult processPayment(BigDecimal amount) {
        System.out.println("💳 Processing PayPal Payment");
        System.out.println("   Email: " + email);
        System.out.println("   Amount: $" + amount);
        
        if (!isValid()) {
            return new PaymentResult(false, "Invalid PayPal credentials", null);
        }
        
        // Simulate payment processing
        System.out.println("   → Connecting to PayPal API...");
        System.out.println("   → Authenticating user...");
        System.out.println("   → Processing payment...");
        
        String transactionId = "PP-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("✅ PayPal payment successful!");
        
        return new PaymentResult(true, "Payment processed via PayPal", transactionId);
    }
    
    @Override
    public String getPaymentMethodName() {
        return "PayPal";
    }
    
    @Override
    public boolean isValid() {
        return email != null && 
               email.contains("@") && 
               password != null && 
               !password.isEmpty();
    }
}
