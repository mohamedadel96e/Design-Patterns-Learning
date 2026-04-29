# Iterator Design Pattern - A Practical Learning Journey

## Why This Pattern Matters

When you design software, you often face a subtle but repeated problem: the business logic needs to walk through data, but the data is stored in different ways.

In one class, data may be stored in an array. In another, it may live in a list. Later, a third team may introduce a tree or a database-backed cursor. If your service code directly depends on each storage type, the code becomes harder to evolve and easier to break.

The Iterator pattern solves this by separating two concerns:

1. How data is stored.
2. How data is traversed.

That separation sounds small, but it has major design consequences for maintainability and extensibility.

## Start With a Real-Life Scenario

In this folder, we model a travel-planning system with two collections:

1. Booked trips.
2. Wishlist trips.

Both collections contain the same domain object: `Trip`.

However, each collection stores data differently:

1. `BookedTrips` uses a fixed array.
2. `WishlistTrips` uses a dynamic `List`.

Now ask yourself a design question:

If `ItineraryService` needs to print trips, filter budget-friendly options, and show short trips, should it know every internal storage detail?

In the `before` version, it does. In the `after` version, it does not.

## The Core Problem in the Before Version

Open [before/Main.java](before/Main.java) and [before/ItineraryService.java](before/ItineraryService.java).

You will notice repeated traversal logic and direct dependence on concrete structures.

### Symptoms you should actively recognize

1. The service contains separate loops for arrays and lists.
2. Every new operation repeats similar traversal code.
3. Internal collection details leak through methods like `getTrips()`.
4. Adding a new collection type forces service changes.

This is a design smell because one reason to change traversal code and one reason to change business logic are mixed in the same class.

## Iterator Pattern Definition

The Iterator pattern provides a way to access elements of a collection sequentially without exposing the underlying representation.

You can think of it as a traversal contract:

1. `hasNext()` asks whether more elements exist.
2. `next()` returns the next element.

The client uses this contract and stays ignorant of whether the collection is an array, a list, or something else.

## Pattern Roles in This Example

The `after` folder maps classical Iterator roles to concrete classes.

1. **Iterator interface**: [after/TripIterator.java](after/TripIterator.java)
2. **Aggregate interface**: [after/TripCollection.java](after/TripCollection.java)
3. **Concrete aggregate (array-backed)**: [after/BookedTrips.java](after/BookedTrips.java)
4. **Concrete aggregate (list-backed)**: [after/WishlistTrips.java](after/WishlistTrips.java)
5. **Concrete iterator for booked trips**: [after/BookedTripsIterator.java](after/BookedTripsIterator.java)
6. **Concrete iterator for wishlist trips**: [after/WishlistTripsIterator.java](after/WishlistTripsIterator.java)
7. **Client using only abstractions**: [after/ItineraryService.java](after/ItineraryService.java)

### Extended teaching layer in this implementation

To make the idea more practical, the `after` version also includes filtered traversal wrappers:

1. Filter contract: [after/TripFilter.java](after/TripFilter.java)
2. Concrete filters: [after/BudgetFriendlyFilter.java](after/BudgetFriendlyFilter.java), [after/ShortTripFilter.java](after/ShortTripFilter.java)
3. Filtered iterator wrapper: [after/FilteringTripIterator.java](after/FilteringTripIterator.java)

This demonstrates an important design insight: once traversal is abstracted, you can compose behavior around iterators without modifying collections.

## How the After Version Works Step by Step

Open [after/Main.java](after/Main.java) and follow the flow:

1. Create `BookedTrips` and `WishlistTrips`.
2. Add the same type of objects (`Trip`) to both.
3. Call one service API: `printTrips(TripCollection collection)`.
4. `ItineraryService` asks each collection for an iterator.
5. The service traverses through `TripIterator` only.

At runtime:

1. `BookedTrips` returns `BookedTripsIterator`.
2. `WishlistTrips` returns `WishlistTripsIterator`.
3. The service code remains identical in both cases.

That is the pattern payoff: polymorphism in traversal.

## Design Questions You Should Think Through

Use these prompts while reading the code.

1. If we add `FavoriteTrips` backed by a `LinkedHashSet`, do we need to modify `ItineraryService`?
2. If we change `BookedTrips` from array to list, how many client classes should notice?
3. If filtering logic changes, should collection classes change?
4. If traversal becomes pagination-based, where should that change live?

If your answers repeatedly point to "only iterator-level classes," you are applying the pattern correctly.

## Trade-offs and Engineering Reality

The Iterator pattern is not free. It introduces extra classes and indirection.

You should use it when:

1. You have multiple collection implementations with shared traversal use cases.
2. You want client code independent from internal storage details.
3. You need extensible traversal strategies (filtering, slicing, batching).

You can avoid it when:

1. There is a single trivial collection with no expected variation.
2. Simplicity is more valuable than abstraction at current scale.

A good engineer does not apply patterns mechanically. A good engineer applies them when they remove meaningful change friction.

## What Changed Architecturally From Before to After

Before:

1. Client knew data structure details.
2. Traversal duplicated everywhere.
3. New operations required repetitive loop logic.

After:

1. Client depends on traversal abstraction only.
2. Traversal is encapsulated inside iterator classes.
3. New operations can compose iterators rather than copy loops.

This is a practical move toward the Open-Closed Principle.

## Complexity and Maintenance Perspective

### Time complexity

Both versions still perform linear traversal for scan operations, so complexity is generally $O(n)$.

### Structural complexity

The after version has more classes but lower change coupling.

That trade is common in good architecture: a little more upfront structure for much safer long-term evolution.

## How to Run the Example

From the `Behavioral/Iterator` directory:

```bash
javac before/*.java
java before.Main

javac after/*.java
java after.Main
```

Compare outputs and inspect the service classes in both versions.

The key learning is not just that both versions work.

The key learning is that the after version remains stable when storage details evolve.

## Final Reflection

The Iterator pattern is fundamentally about protecting business logic from storage volatility.

In small examples, the difference may appear minor. In larger systems, this separation can prevent fragile services, repetitive loops, and expensive refactors.

When you evaluate a design, ask one powerful question:

"If I change how data is stored, how many classes must I touch?"

Iterator helps you keep that number low.