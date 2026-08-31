# The Composite Pattern

## Core Concept

The Composite pattern allows you to treat individual objects and collections of objects uniformly. It achieves this by organizing objects into a tree structure where both the leaves (individual items) and the composites (containers) share a common interface.

This means client code can interact with the interface without needing to know whether it's dealing with a single object or an entire branch of the tree.

## Why We Need It

In software design, we frequently encounter nested or hierarchical structures, such as:
- File systems (files and directories)
- UI element trees (components and panels)
- Product configurations (individual parts and assemblies)

Without the Composite pattern, navigating and operating on these structures usually requires fragile type checking and conditional logic scattered throughout the client code.

## Example Scenario

In this directory, we're modeling a restaurant menu:
- A menu item represents a single dish with a price.
- A menu section groups multiple items or even other sections, and its price is the sum of its contents.

In the `before` version, calculating the total or printing the order involves distinct logic for items versus sections. In the `after` version, both implement a unified `MenuComponent` interface.

## The Problem (Before)

If you look at `before/Main.java` and `before/MenuSection.java`, you can see the friction:
1. The client code is acutely aware of the difference between a standalone item and a section.
2. It has to manually iterate through children to calculate totals.
3. Adding further nesting (like subsections) would increase the complexity of the branching logic.

This type-specific handling is exactly what Composite is designed to eliminate.

## How the Composite Solves This

The pattern composes objects into tree structures, providing a single interface for both the individual leaf nodes and the composite nodes.

### Pattern Roles

In the `after` folder, the classes map to the Composite roles:
1. **Component**: `after/MenuComponent.java` (The shared interface)
2. **Leaf**: `after/MenuItem.java` (An individual item)
3. **Composite**: `after/MenuSection.java` (A collection of components)
4. **Client**: `after/Main.java`

### Step-by-Step Flow

Looking at `after/Main.java`:
1. The client constructs a tree by creating a `MenuSection` and adding both items and nested sections to it.
2. Since both implement `MenuComponent`, the client simply calls `getPrice()` on the root menu.
3. The composite recursively calculates the price down the tree.
4. Printing the order works the same way, traversing the tree without any explicit type checks.

The benefit is a clean, uniform interface regardless of the underlying structural complexity.

## Adapter vs. Composite

- **Adapter** translates one interface into another to fix incompatibility.
- **Composite** standardizes the interface across singular objects and collections to simplify client interactions with tree structures.

## Design Trade-offs

While Composite simplifies the client, it adds indirection and can obscure the exact structure of the tree.

Consider using it when:
- You need to represent part-whole hierarchies.
- You want clients to interact with individual objects and compositions uniformly.
- You want to eliminate manual tree traversal and type-checking in client code.

You might want to avoid it when:
- Your data structure is flat.
- You need completely different operations for leaf nodes versus composite nodes.
- You require strict type safety that a shared interface might compromise.

## Common Pitfalls

- Placing child-management methods (like `add` or `remove`) on leaf classes where they don't make sense.
- Exposing the internal tree traversal logic to the client.
- Using the composite purely as a data structure rather than encapsulating behavior.

## Running the Example

From the `Structural/Composite` directory:

```bash
# Run the before version
javac Structural/Composite/before/*.java
java Structural.Composite.before.Main

# Run the after version
javac Structural/Composite/after/*.java
java Structural.Composite.after.Main

# Run tests
javac Structural/Composite/after/*.java
java -ea Structural.Composite.after.CompositeTest
```

## Final Thoughts

The Composite pattern shines when it comes to removing conditionals from client code that navigates nested structures. If you find yourself writing logic that behaves differently depending on whether an object contains other objects of the same base type, consider whether a unified composite tree would yield a cleaner design.
