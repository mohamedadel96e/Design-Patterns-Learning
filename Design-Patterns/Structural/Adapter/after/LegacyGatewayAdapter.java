package after;

public class LegacyGatewayAdapter implements PaymentProcessor {
    private final LegacyPaymentGateway gateway;

    public LegacyGatewayAdapter(LegacyPaymentGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public PaymentResult process(Order order) {
        String confirmation = gateway.charge(order.getAccountId(), order.getAmountInCents());
        return new PaymentResult("LegacyGateway", confirmation);
    }
}

