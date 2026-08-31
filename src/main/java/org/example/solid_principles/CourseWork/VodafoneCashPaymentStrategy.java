package org.example.solid_principles.CourseWork;

public class VodafoneCashPaymentStrategy implements PaymentStrategy{
    @Override
    public void processPayment(double amount) {
        System.out.println("You Have Paid by your Vodafone Cash Account");
    }
}
