# Liskov Substitution Principle (LSP)

## Overview
*"Objects of a superclass should be replaceable with objects of a subclass without breaking the application."* - Barbara Liskov

In practice, this means subtypes must be fully substitutable for their base types without altering the correctness of the program.

## The Problem (Before)

In the `before` directory, there is a `Bird` hierarchy that violates LSP. It typically looks something like this:

```java
class Bird {
    void fly() { ... }
}

class Penguin extends Bird {
    @Override
    void fly() {
        throw new UnsupportedOperationException("Penguins can't fly!");
    }
}
```

### Issues with this approach
- **Broken Substitution**: A `Penguin` instance cannot safely substitute a `Bird` instance without breaking the code that relies on it.
- **Unexpected Exceptions**: Any client code expecting a `Bird` and calling `fly()` will encounter runtime exceptions when passed a `Penguin`.
- **Violated Contract**: The subclass fails to honor the contract defined by the superclass.
- **Type Checking Overhead**: It forces client code to perform explicit type checks (like `instanceof`) before invoking methods.
- **Fragile Codebase**: Adding new bird types requires careful, case-by-case consideration of these exceptions.

## The Solution (After)

In the `after` directory, the code is refactored to adhere to LSP by designing a more appropriate hierarchy based on actual behaviors:

1. **Bird**: A base class containing common avian behavior.
2. **FlyingBird**: An abstract class for birds capable of flight.
3. **NonFlyingBird**: An abstract class for birds that do not fly.
4. **Eagle**, **Sparrow**: Concrete implementations of flying birds.
5. **Penguin**, **Ostrich**: Concrete implementations of non-flying birds.

### Benefits of this approach
- **Proper Substitution**: Subtypes can safely replace their base types.
- **Predictable Behavior**: Methods behave as expected without surprising exceptions.
- **Honored Contracts**: Subclasses strictly fulfill the contracts established by their superclasses.
- **No Type Checking**: Clients can interact with the objects polymorphically.
- **Extensible Design**: It's much easier to add new bird types correctly and safely.

## Key Takeaways

1. Subclasses should strengthen, not weaken, preconditions.
2. Subclasses should weaken, not strengthen, postconditions.
3. Subclasses must preserve the invariants of the superclass.
4. Avoid throwing exceptions from overridden methods if the base class doesn't throw them.
5. Design inheritance hierarchies based on actual behavior rather than real-world taxonomies.

## How to Run

```bash
# Compile the before example
javac before/*.java

# Run the before example
java before.Main

# Compile the after example
javac after/*.java

# Run the after example
java after.Main
```

## Real-World Applications

- Shape hierarchies (e.g., the classic Rectangle vs. Square problem).
- Vehicle categorization (distinguishing between flying, driving, and swimming vehicles).
- Storage systems (handling read-only versus read-write streams or files).
- User types with differing capability levels.
- Payment methods with varied processing requirements.
