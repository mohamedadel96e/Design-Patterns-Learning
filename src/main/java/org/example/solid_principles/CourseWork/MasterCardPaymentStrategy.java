package org.example.solid_principles.CourseWork;

public class MasterCardPaymentStrategy implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing master card payments...");
    }
}
