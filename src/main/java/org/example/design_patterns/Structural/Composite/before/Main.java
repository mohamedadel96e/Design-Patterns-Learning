package org.example.design_patterns.Structural.Composite.before;

public class Main {
    public static void main(String[] args) {
        MenuSection root = new MenuSection("Main Menu");
        root.addItem(new MenuItem("Burger", 8.50));
        root.addItem(new MenuItem("Salad", 6.00));

        MenuSection drinks = new MenuSection("Drinks");
        drinks.addItem(new MenuItem("Coffee", 3.00));
        drinks.addItem(new MenuItem("Tea", 2.50));

        MenuSection desserts = new MenuSection("Desserts");
        desserts.addItem(new MenuItem("Cake", 4.25));

        root.addSection(drinks);
        root.addSection(desserts);

        double total = 0.0;
        for (MenuItem item : root.getItems()) {
            item.order();
            total += item.getPrice();
        }

        for (MenuSection section : root.getSections()) {
            System.out.println("Section: " + section.getName());
            for (MenuItem item : section.getItems()) {
                item.order();
                total += item.getPrice();
            }
            for (MenuSection nested : section.getSections()) {
                System.out.println("Nested section: " + nested.getName());
                for (MenuItem item : nested.getItems()) {
                    item.order();
                    total += item.getPrice();
                }
            }
        }

        System.out.println("Total before tax: $" + total);
    }
}
