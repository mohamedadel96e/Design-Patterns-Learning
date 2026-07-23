# Composite Design Pattern - A Practical Learning Journey

## Concept Summary

The Composite pattern lets you treat individual objects and groups of objects in the same way.

It builds a tree structure where both **leaves** and **containers** share a common interface.
Clients operate on the interface, not on special cases.

## Why It Matters

Real systems often deal with nested structures:

- files and folders
- UI components and panels
- product bundles and items

Without Composite, client code turns into a maze of `if` checks and special handling.

## Real-Life Scenario in This Folder

We model a menu system:

- A **menu item** has a price and can be ordered.
- A **menu section** groups items and can also be ordered.

In the `before` version, the order logic handles items and sections separately.
In the `after` version, both implement a shared `MenuComponent` interface.

## The Core Problem in the Before Version

Open [before/Main.java](before/Main.java) and [before/MenuSection.java](before/MenuSection.java).

Notice how the ordering logic checks types and handles pricing rules manually:

1. The client knows the difference between items and sections.
2. It loops through children to compute totals.
3. Adding another level of nesting multiplies branching logic.

This is exactly the complexity Composite removes.

## Composite Pattern Definition

The Composite pattern composes objects into tree structures and lets clients treat individual objects and compositions uniformly.

## Pattern Roles in This Example

The `after` folder maps Composite roles to concrete classes:

1. **Component**: [after/MenuComponent.java](after/MenuComponent.java)
2. **Leaf**: [after/MenuItem.java](after/MenuItem.java)
3. **Composite**: [after/MenuSection.java](after/MenuSection.java)
4. **Client**: [after/Main.java](after/Main.java)

## How the After Version Works Step by Step

Open [after/Main.java](after/Main.java):

1. The client creates a `MenuSection` and adds items and nested sections.
2. Both items and sections implement `MenuComponent`.
3. The client calls `getPrice()` on the whole menu and it just works.
4. Ordering prints the entire tree without any type checks.

That is the Composite payoff: a uniform interface for leaf and composite nodes.

## Adapter vs Composite

- **Adapter**: translate one interface into another.
- **Composite**: treat a single object and a collection the same way.

If the problem is "these two APIs don't match," think Adapter.
If the problem is "I need a tree of objects but want one API," think Composite.

## Brainstorming Walkthrough (Question -> Hypothesis -> Experiment)

### Question

If we nest menu sections three levels deep, do we want special logic in every client?

### Hypothesis

If both sections and items share one interface, the client can ignore the difference.

### Experiment

1. Compare [before/Main.java](before/Main.java) with [after/Main.java](after/Main.java).
2. Observe how the `after` version contains no branching for items vs sections.

### Result

The client treats the entire menu uniformly and the total is computed recursively.

## Trade-offs and Engineering Reality

Composite adds indirection and can hide complexity in the tree.
Use it when:

1. You need to represent part-whole hierarchies.
2. You want to treat leaves and groups the same way.
3. You want to avoid manual tree traversal in clients.

Avoid it when:

1. The structure is flat.
2. You need wildly different operations on leaves and composites.
3. You need strict type guarantees for each node type.

## Common Pitfalls

- Mixing child management logic into leaf classes
- Exposing tree traversal to client code
- Treating the composite as a storage container rather than a behavior container

## Glossary

- **Component**: Shared interface for leaves and composites.
- **Leaf**: A node with no children.
- **Composite**: A node that contains children.

## How to Run the Example

From the `Structural/Composite` directory:

```bash
javac Structural/Composite/before/*.java
java Structural.Composite.before.Main

javac Structural/Composite/after/*.java
java Structural.Composite.after.Main
```

To run the lightweight assertion-based test in the `after` version:

```bash
javac Structural/Composite/after/*.java
java -ea Structural.Composite.after.CompositeTest
```

## Final Reflection

Composite removes client-level conditionals for nested structures.

When you see a hierarchy where objects can contain other objects of the same type,
ask yourself:

"Should this be modeled as a composite tree with a single interface?"

If the answer is yes, the Composite pattern will simplify your design.

## Next Exercises

1. Add a `ComboMeal` section that automatically applies a discount.
2. Add a `printIndented()` operation to display depth levels.
3. Add a `remove()` method and update the test.
