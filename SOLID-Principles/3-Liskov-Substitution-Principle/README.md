# Liskov Substitution Principle (LSP)

## Definition
*"Objects of a superclass should be replaceable with objects of a subclass without breaking the application."* - Barbara Liskov

Subtypes must be substitutable for their base types without altering the correctness of the program.

## The Problem (Before)

In the `before` folder, we have a `Bird` hierarchy that violates LSP:

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

### Issues with this approach:
- **Broken Substitution**: Penguin cannot substitute Bird without breaking code
- **Unexpected Exceptions**: Code expecting a Bird gets runtime exceptions
- **Violated Contract**: Subclass doesn't honor superclass contract
- **Type Checking**: Forces clients to check types before calling methods
- **Fragile Code**: Adding new bird types requires careful consideration

## The Solution (After)

In the `after` folder, we've refactored the code to follow LSP by properly designing the hierarchy:

1. **Bird** - Base class with common behavior
2. **FlyingBird** - Abstract class for birds that can fly
3. **NonFlyingBird** - Abstract class for birds that cannot fly
4. **Eagle**, **Sparrow** - Concrete flying birds
5. **Penguin**, **Ostrich** - Concrete non-flying birds

### Benefits of this approach:
- ✅ **Proper Substitution**: Subtypes can replace base types safely
- ✅ **No Surprises**: Methods behave as expected
- ✅ **Honored Contracts**: Subclasses fulfill superclass contracts
- ✅ **No Type Checking**: Clients use types polymorphically
- ✅ **Extensible**: Easy to add new bird types correctly

## Key Takeaways

1. Subclasses should strengthen, not weaken, preconditions
2. Subclasses should weaken, not strengthen, postconditions
3. Subclasses should preserve invariants of the superclass
4. Don't throw exceptions from overridden methods that base class doesn't throw
5. Design inheritance hierarchies carefully based on behavior

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

- Shape hierarchies (Rectangle/Square problem)
- Vehicle types (flying, driving, swimming vehicles)
- Storage systems (read-only vs read-write)
- User types with different capabilities
- Payment methods with different requirements
