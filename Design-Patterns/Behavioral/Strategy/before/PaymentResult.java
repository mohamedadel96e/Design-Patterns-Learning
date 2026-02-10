package before;

/**
 * Represents the result of a payment transaction
 */
public class PaymentResult {
    private boolean success;
    private String transactionId;
    private String message;
    private double amount;
    private double fee;
    private double totalCharged;
    private String paymentMethod;
    private long timestamp;

    public PaymentResult(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters and setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getTransactionId() { return transactionId; }
    public void setTransactionId(String transactionId) { this.transactionId = transactionId; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getFee() { return fee; }
    public void setFee(double fee) { this.fee = fee; }

    public double getTotalCharged() { return totalCharged; }
    public void setTotalCharged(double totalCharged) { this.totalCharged = totalCharged; }

    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    @Override
    public String toString() {
        return String.format(
            "PaymentResult{success=%s, transactionId='%s', method='%s', amount=%.2f, fee=%.2f, total=%.2f, message='%s'}",
            success, transactionId, paymentMethod, amount, fee, totalCharged, message
        );
    }
}
