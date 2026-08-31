package org.example.solid_principles.CourseWork;

public class PickUpOrder extends Order implements ShippingCostCalculator {

    @Override
    public double calculateShippingCost() {
        return getPrice();
    }
}
