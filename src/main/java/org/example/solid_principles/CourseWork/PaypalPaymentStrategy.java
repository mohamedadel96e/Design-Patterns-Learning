package org.example.solid_principles.CourseWork;

public class PaypalPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing paypal payments...");
    }
}
