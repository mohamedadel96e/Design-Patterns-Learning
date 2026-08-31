package org.example.design_patterns.Structural.Composite.before;

import java.util.ArrayList;
import java.util.List;

public class MenuSection {
    private final String name;
    private final List<MenuItem> items = new ArrayList<>();
    private final List<MenuSection> sections = new ArrayList<>();

    public MenuSection(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public void addSection(MenuSection section) {
        sections.add(section);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public List<MenuSection> getSections() {
        return sections;
    }
}
