package org.example.design_patterns.Structural.Composite.after;

public class Main {
    public static void main(String[] args) {
        MenuSection root = new MenuSection("Main Menu");
        root.add(new MenuItem("Burger", 8.50));
        root.add(new MenuItem("Salad", 6.00));

        MenuSection drinks = new MenuSection("Drinks");
        drinks.add(new MenuItem("Coffee", 3.00));
        drinks.add(new MenuItem("Tea", 2.50));

        MenuSection desserts = new MenuSection("Desserts");
        desserts.add(new MenuItem("Cake", 4.25));

        root.add(drinks);
        root.add(desserts);

        root.order();
        System.out.println("Total before tax: $" + root.getPrice());
    }
}
