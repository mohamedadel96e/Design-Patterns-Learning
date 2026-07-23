package after;

public class Order {
    private final String orderId;
    private final String accountId;
    private final int amountInCents;
    private final String currency;

    public Order(String orderId, String accountId, int amountInCents, String currency) {
        this.orderId = orderId;
        this.accountId = accountId;
        this.amountInCents = amountInCents;
        this.currency = currency;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getAccountId() {
        return accountId;
    }

    public int getAmountInCents() {
        return amountInCents;
    }

    public String getCurrency() {
        return currency;
    }
}

