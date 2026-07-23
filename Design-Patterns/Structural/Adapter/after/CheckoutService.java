package after;

public class CheckoutService {
    private final PaymentProcessor processor;

    public CheckoutService(PaymentProcessor processor) {
        this.processor = processor;
    }

    public PaymentResult checkout(Order order) {
        return processor.process(order);
    }
}

