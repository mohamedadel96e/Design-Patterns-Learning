# Interface Segregation Principle (ISP)

## Definition
*"No client should be forced to depend on methods it does not use."* - Robert C. Martin

Create fine-grained interfaces that are client-specific rather than one general-purpose interface.

## The Problem (Before)

In the `before` folder, we have a large `Worker` interface that violates ISP by forcing all implementations to provide methods they don't need:

```java
interface Worker {
    void work();
    void eat();
    void sleep();
    void attendMeeting();
    void submitReport();
}
```

### Issues with this approach:
- **Unnecessary Methods**: Robot workers don't eat or sleep but must implement these methods
- **Empty Implementations**: Classes provide stub implementations for methods they don't use
- **Fat Interfaces**: One interface tries to serve all clients
- **Tight Coupling**: Clients depend on methods they never call
- **Hard to Change**: Changes to interface affect all implementations

## The Solution (After)

In the `after` folder, we've refactored the code to follow ISP by creating smaller, focused interfaces:

1. **Workable** - For entities that can work
2. **Eatable** - For entities that need to eat
3. **Sleepable** - For entities that need to sleep
4. **Meetable** - For entities that attend meetings
5. **Reportable** - For entities that submit reports

### Benefits of this approach:
- ✅ **Focused Interfaces**: Each interface has a single purpose
- ✅ **No Unused Methods**: Classes implement only what they need
- ✅ **Flexible Composition**: Combine interfaces as needed
- ✅ **Easy to Extend**: Add new capabilities without affecting existing code
- ✅ **Better Decoupling**: Clients depend only on methods they use

## Key Takeaways

1. Prefer many small, specific interfaces over one large interface
2. Don't force clients to implement methods they don't use
3. Use interface composition to build complex behavior
4. Design interfaces from the client's perspective
5. Keep interfaces cohesive and focused

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

- Document handlers (read-only vs read-write)
- User roles (admin, viewer, editor capabilities)
- Device capabilities (printer, scanner, fax)
- Data access patterns (read, write, delete)
- API clients with different feature sets
