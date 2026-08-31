# Iterator Design Pattern

## Why this pattern matters

When you design software, you often face a subtle but repeated problem: the business logic needs to walk through data, but the data is stored in different ways.

In one class, data may be stored in an array. In another, it may live in a list. Later, a different team may introduce a tree or a database-backed cursor. If your service code directly depends on each storage type, the code becomes harder to evolve and easier to break.

The Iterator pattern solves this by separating two concerns:

1. How data is stored.
2. How data is traversed.

That separation has major design consequences for maintainability and extensibility.

## A real-life scenario

In this folder, we model a travel-planning system with two collections:

1. Booked trips.
2. Wishlist trips.

Both collections contain the same domain object: `Trip`. However, each collection stores data differently:

1. `BookedTrips` uses a fixed array.
2. `WishlistTrips` uses a dynamic `List`.

If `ItineraryService` needs to print trips, filter budget-friendly options, and show short trips, should it know every internal storage detail?

In the `before` version, it does. In the `after` version, it does not.

## The core problem in the "before" version

Open `before/Main.java` and `before/ItineraryService.java`.

You will notice repeated traversal logic and direct dependence on concrete structures.

### Symptoms to look out for

1. The service contains separate loops for arrays and lists.
2. Every new operation repeats similar traversal code.
3. Internal collection details leak through methods like `getTrips()`.
4. Adding a new collection type forces service changes.

This is a design smell because the reason to change traversal code and the reason to change business logic are mixed in the same class.

## Pattern definition

The Iterator pattern provides a way to access elements of a collection sequentially without exposing the underlying representation.

You can think of it as a traversal contract:

1. `hasNext()` asks whether more elements exist.
2. `next()` returns the next element.

The client uses this contract and stays ignorant of whether the collection is an array, a list, or something else entirely.

## Pattern roles in this example

The `after` folder maps classical Iterator roles to concrete classes.

1. **Iterator interface**: `after/TripIterator.java`
2. **Aggregate interface**: `after/TripCollection.java`
3. **Concrete aggregate (array-backed)**: `after/BookedTrips.java`
4. **Concrete aggregate (list-backed)**: `after/WishlistTrips.java`
5. **Concrete iterator for booked trips**: `after/BookedTripsIterator.java`
6. **Concrete iterator for wishlist trips**: `after/WishlistTripsIterator.java`
7. **Client using only abstractions**: `after/ItineraryService.java`

To make the idea more practical, the `after` version also includes filtered traversal wrappers:

1. Filter contract: `after/TripFilter.java`
2. Concrete filters: `after/BudgetFriendlyFilter.java`, `after/ShortTripFilter.java`
3. Filtered iterator wrapper: `after/FilteringTripIterator.java`

This demonstrates an important design insight: once traversal is abstracted, you can compose behavior around iterators without modifying the underlying collections.

## How the "after" version works step by step

Open `after/Main.java` and follow the flow:

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

## Design questions to think through

Use these prompts while reading the code:

1. If we add `FavoriteTrips` backed by a `LinkedHashSet`, do we need to modify `ItineraryService`?
2. If we change `BookedTrips` from an array to a list, how many client classes should notice?
3. If filtering logic changes, should collection classes change?
4. If traversal becomes pagination-based, where should that change live?

If your answers repeatedly point to "only iterator-level classes," you are applying the pattern correctly.

## Trade-offs and engineering reality

The Iterator pattern introduces extra classes and indirection.

You should use it when:

1. You have multiple collection implementations with shared traversal use cases.
2. You want client code independent from internal storage details.
3. You need extensible traversal strategies (filtering, slicing, batching).

You should avoid it when:

1. There is a single trivial collection with no expected variation.
2. Simplicity is more valuable than abstraction at the current scale.

A good engineer applies patterns when they remove meaningful change friction.

## Architectural changes

Before:
1. Client knew data structure details.
2. Traversal was duplicated everywhere.
3. New operations required repetitive loop logic.

After:
1. Client depends on traversal abstraction only.
2. Traversal is encapsulated inside iterator classes.
3. New operations can compose iterators rather than copy loops.

This is a practical move toward the Open-Closed Principle.

## Complexity and maintenance

### Time complexity
Both versions perform linear traversal for scan operations, so complexity is generally O(n).

### Structural complexity
The after version has more classes but lower change coupling. That trade-off is common in good architecture: slightly more upfront structure for much safer long-term evolution.

## How to run the example

From the `Behavioral/Iterator` directory:

```bash
javac before/*.java
java before.Main

javac after/*.java
java after.Main
```

Compare outputs and inspect the service classes in both versions. The key learning is that the after version remains stable even when storage details evolve.

## Final reflection

The Iterator pattern is fundamentally about protecting business logic from storage volatility.

In small examples, the difference may appear minor. In larger systems, this separation can prevent fragile services, repetitive loops, and expensive refactors.

When you evaluate a design, ask one powerful question: "If I change how data is stored, how many classes must I touch?"

Iterator helps you keep that number low.