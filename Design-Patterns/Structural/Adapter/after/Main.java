package after;

public class Main {
    public static void main(String[] args) {
        Order order = new Order("ORD-1001", "acct-42", 2499, "USD");

        CheckoutService legacyCheckout = new CheckoutService(
                new LegacyGatewayAdapter(new LegacyPaymentGateway())
        );
        PaymentResult legacyResult = legacyCheckout.checkout(order);
        System.out.println("Legacy confirmation: " + legacyResult.getConfirmationCode());

        CheckoutService modernCheckout = new CheckoutService(
                new ModernPaymentProcessor(new ModernPaymentGateway())
        );
        PaymentResult modernResult = modernCheckout.checkout(order);
        System.out.println("Modern confirmation: " + modernResult.getConfirmationCode());
    }
}

