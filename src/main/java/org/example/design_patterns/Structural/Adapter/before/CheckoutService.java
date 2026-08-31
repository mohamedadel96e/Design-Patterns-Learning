package org.example.design_patterns.Structural.Adapter.before;

public class CheckoutService {
    private final ModernPaymentGateway modernGateway;
    private final LegacyPaymentGateway legacyGateway;

    public CheckoutService(ModernPaymentGateway modernGateway, LegacyPaymentGateway legacyGateway) {
        this.modernGateway = modernGateway;
        this.legacyGateway = legacyGateway;
    }

    public String checkout(Order order, PaymentMethod method) {
        if (method == PaymentMethod.MODERN_GATEWAY) {
            PaymentRequest request = new PaymentRequest(
                    order.getOrderId(),
                    order.getAccountId(),
                    order.getAmountInCents(),
                    order.getCurrency()
            );
            return modernGateway.pay(request);
        }

        return legacyGateway.charge(order.getAccountId(), order.getAmountInCents());
    }
}

