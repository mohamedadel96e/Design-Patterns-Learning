package org.example.design_patterns.Structural.Adapter.after;

public class AdapterTest {
    public static void main(String[] args) {
        Order order = new Order("ORD-2002", "acct-99", 9900, "USD");

        PaymentProcessor legacyProcessor = new LegacyGatewayAdapter(new LegacyPaymentGateway());
        PaymentResult legacyResult = legacyProcessor.process(order);
        assert "LegacyGateway".equals(legacyResult.getProviderName());
        assert legacyResult.getConfirmationCode().startsWith("LEG-");

        PaymentProcessor modernProcessor = new ModernPaymentProcessor(new ModernPaymentGateway());
        PaymentResult modernResult = modernProcessor.process(order);
        assert "ModernGateway".equals(modernResult.getProviderName());
        assert modernResult.getConfirmationCode().startsWith("MOD-");

        System.out.println("AdapterTest passed.");
    }
}

