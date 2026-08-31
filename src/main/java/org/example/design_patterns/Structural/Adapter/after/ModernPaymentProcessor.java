package org.example.design_patterns.Structural.Adapter.after;

public class ModernPaymentProcessor implements PaymentProcessor {
    private final ModernPaymentGateway gateway;

    public ModernPaymentProcessor(ModernPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public PaymentResult process(Order order) {
        PaymentRequest request = new PaymentRequest(
                order.getOrderId(),
                order.getAccountId(),
                order.getAmountInCents(),
                order.getCurrency()
        );
        String confirmation = gateway.pay(request);
        return new PaymentResult("ModernGateway", confirmation);
    }
}

