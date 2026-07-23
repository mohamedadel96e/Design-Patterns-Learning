package Structural.Composite.after;

public class CompositeTest {
    public static void main(String[] args) {
        MenuSection root = new MenuSection("Main Menu");
        root.add(new MenuItem("Burger", 8.50));
        root.add(new MenuItem("Salad", 6.00));

        MenuSection drinks = new MenuSection("Drinks");
        drinks.add(new MenuItem("Coffee", 3.00));
        drinks.add(new MenuItem("Tea", 2.50));
        root.add(drinks);

        MenuSection desserts = new MenuSection("Desserts");
        desserts.add(new MenuItem("Cake", 4.25));
        root.add(desserts);

        assert "Main Menu".equals(root.getName());
        assert Math.abs(root.getPrice() - 24.25) < 0.001;

        root.order();
        System.out.println("CompositeTest passed.");
    }
}
