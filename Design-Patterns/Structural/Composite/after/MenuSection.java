package Structural.Composite.after;

import java.util.ArrayList;
import java.util.List;

public class MenuSection implements MenuComponent {
    private final String name;
    private final List<MenuComponent> children = new ArrayList<>();

    public MenuSection(String name) {
        this.name = name;
    }

    public void add(MenuComponent component) {
        children.add(component);
    }

    public void remove(MenuComponent component) {
        children.remove(component);
    }

    public List<MenuComponent> getChildren() {
        return children;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        double total = 0.0;
        for (MenuComponent child : children) {
            total += child.getPrice();
        }
        return total;
    }

    @Override
    public void order() {
        System.out.println("Section: " + name);
        for (MenuComponent child : children) {
            child.order();
        }
    }
}
