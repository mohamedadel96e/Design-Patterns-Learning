package before;

public class Main {
    public static void main(String[] args) {
        Order order = new Order("ORD-1001", "acct-42", 2499, "USD");

        CheckoutService checkoutService = new CheckoutService(
                new ModernPaymentGateway(),
                new LegacyPaymentGateway()
        );

        String modernConfirmation = checkoutService.checkout(order, PaymentMethod.MODERN_GATEWAY);
        System.out.println("Modern confirmation: " + modernConfirmation);

        String legacyConfirmation = checkoutService.checkout(order, PaymentMethod.LEGACY_GATEWAY);
        System.out.println("Legacy confirmation: " + legacyConfirmation);
    }
}

