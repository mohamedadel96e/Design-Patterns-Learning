# Dependency Inversion Principle (DIP)

## Definition
*"Depend on abstractions, not concretions."* - Robert C. Martin

1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
2. Abstractions should not depend on details. Details should depend on abstractions.

## The Problem (Before)

In the `before` folder, we have a `NotificationService` that directly depends on concrete implementations:

```java
class NotificationService {
    private EmailSender emailSender;
    private SMSSender smsSender;
    
    // Tightly coupled to concrete classes!
}
```

### Issues with this approach:
- **Tight Coupling**: High-level code depends on low-level implementations
- **Hard to Test**: Can't easily mock dependencies
- **Hard to Extend**: Adding new notification methods requires modifying the service
- **Inflexible**: Can't swap implementations at runtime
- **Violates DIP**: Depends on concretions, not abstractions

## The Solution (After)

In the `after` folder, we've refactored the code to follow DIP by introducing abstractions:

1. **NotificationChannel** - Abstract interface for all notification methods
2. **EmailChannel**, **SMSChannel**, **PushChannel** - Concrete implementations
3. **NotificationService** - Depends on abstraction, not concrete classes
4. **Dependency Injection** - Dependencies injected from outside

### Benefits of this approach:
- ✅ **Loose Coupling**: High-level code depends on abstractions
- ✅ **Easy to Test**: Dependencies can be mocked easily
- ✅ **Easy to Extend**: New channels added without modifying service
- ✅ **Flexible**: Can swap implementations at runtime
- ✅ **Follows DIP**: Depends on abstractions, not concretions

## Key Takeaways

1. Program to interfaces, not implementations
2. Use dependency injection to provide concrete implementations
3. High-level modules define the abstractions they need
4. Low-level modules implement those abstractions
5. This inverts the traditional dependency direction

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

- Service layers depending on repository interfaces
- Controllers depending on service interfaces
- Notification systems with multiple channels
- Data access with multiple database providers
- Authentication with multiple strategies
