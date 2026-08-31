# Interface Segregation Principle (ISP)

## Overview
*"No client should be forced to depend on methods it does not use."* - Robert C. Martin

The core idea is to create fine-grained interfaces that are specific to client needs, rather than relying on a single, general-purpose interface.

## The Problem (Before)

In the `before` directory, there is a large `Worker` interface. It violates ISP by forcing all implementations to provide methods they may not actually need:

```java
interface Worker {
    void work();
    void eat();
    void sleep();
    void attendMeeting();
    void submitReport();
}
```

### Issues with this approach
- **Unnecessary Methods**: For instance, robot workers don't eat or sleep, yet they are forced to implement these methods.
- **Empty Implementations**: Classes end up providing stub or dummy implementations for methods they don't actually support.
- **Fat Interfaces**: A single interface attempts to serve all types of clients, leading to bloated contracts.
- **Tight Coupling**: Clients depend on methods they never call, which creates unnecessary dependencies.
- **Hard to Change**: Modifying the fat interface affects all implementing classes, even those that don't care about the changed method.

## The Solution (After)

In the `after` directory, the code is refactored to follow ISP by breaking the large interface down into smaller, highly focused ones:

1. **Workable**: For entities that can perform work.
2. **Eatable**: For entities that require eating.
3. **Sleepable**: For entities that require sleeping.
4. **Meetable**: For entities that attend meetings.
5. **Reportable**: For entities that submit reports.

### Benefits of this approach
- **Focused Interfaces**: Each interface serves a single, well-defined purpose.
- **No Unused Methods**: Implementing classes only define the behavior they actually need.
- **Flexible Composition**: You can combine these granular interfaces as needed for specific implementations.
- **Easy to Extend**: New capabilities can be added by introducing new interfaces without breaking existing code.
- **Better Decoupling**: Clients only depend on the specific methods they actually use.

## Key Takeaways

1. Prefer many small, specific interfaces over a single large one.
2. Never force a client to implement a method it doesn't use.
3. Leverage interface composition to build up complex behavior.
4. Design interfaces from the perspective of the client using them.
5. Keep interfaces cohesive and sharply focused.

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

- Document handlers separating read-only and read-write capabilities.
- User roles distinguishing between admin, viewer, and editor permissions.
- Device capabilities (e.g., interfaces for printing, scanning, or faxing).
- Data access patterns cleanly split into read, write, and delete operations.
- API clients tailored with different feature sets.
