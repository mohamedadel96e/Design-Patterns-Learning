package org.example.solid_principles.open_closed_principle.after;

/**
 * Represents the result of a payment operation
 */
public class PaymentResult {
    private boolean success;
    private String message;
    private String transactionId;
    
    public PaymentResult(boolean success, String message, String transactionId) {
        this.success = success;
        this.message = message;
        this.transactionId = transactionId;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    @Override
    public String toString() {
        return "PaymentResult{success=" + success + 
               ", message='" + message + "'" +
               (transactionId != null ? ", transactionId='" + transactionId + "'" : "") + "}";
    }
}
