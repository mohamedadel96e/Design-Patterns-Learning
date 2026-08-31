package org.example.solid_principles.CourseWork;

public class AmericanExpressPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing american express card payments...");
    }
}
