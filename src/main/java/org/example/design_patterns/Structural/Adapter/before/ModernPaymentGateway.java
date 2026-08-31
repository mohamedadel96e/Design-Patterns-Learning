package org.example.design_patterns.Structural.Adapter.before;

public class ModernPaymentGateway {
    public String pay(PaymentRequest request) {
        String confirmation = "MOD-" + request.getOrderId();
        System.out.println("Modern gateway processed " + request.getAmountInCents() + " " + request.getCurrency()
                + " for account " + request.getAccountId() + ".");
        return confirmation;
    }
}

