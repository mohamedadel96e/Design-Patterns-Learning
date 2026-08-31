package org.example.design_patterns.Structural.Composite.before;

public class MenuItem {
    private final String name;
    private final double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void order() {
        System.out.println("Order item: " + name + " ($" + price + ")");
    }
}
