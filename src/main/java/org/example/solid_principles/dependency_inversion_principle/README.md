# Dependency Inversion Principle (DIP)

## Overview
*"Depend on abstractions, not concretions."* - Robert C. Martin

This principle is built on two key rules:
1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
2. Abstractions should not depend on details. Details should depend on abstractions.

## The Problem (Before)

In the `before` directory, the `NotificationService` relies directly on concrete implementations. It often looks like this:

```java
class NotificationService {
    private EmailSender emailSender;
    private SMSSender smsSender;
    
    // Tightly coupled to concrete classes
}
```

### Issues with this approach
- **Tight Coupling**: The high-level service code is directly tied to low-level implementation details.
- **Hard to Test**: It's difficult to mock the concrete dependencies for unit testing.
- **Hard to Extend**: Introducing new notification methods means modifying the core service class.
- **Inflexible**: You cannot easily swap out implementations at runtime.
- **Violates DIP**: The service is depending on concretions rather than abstractions.

## The Solution (After)

In the `after` directory, the code is refactored to introduce abstractions, satisfying DIP:

1. **NotificationChannel**: An abstract interface defining how a notification is sent.
2. **EmailChannel**, **SMSChannel**, **PushChannel**: Concrete classes implementing the abstraction.
3. **NotificationService**: Now depends purely on the `NotificationChannel` abstraction instead of concrete classes.
4. **Dependency Injection**: Dependencies are provided from the outside (e.g., via a constructor), removing instantiation logic from the service.

### Benefits of this approach
- **Loose Coupling**: High-level modules depend only on stable abstractions.
- **Easy to Test**: Dependencies can be quickly mocked or stubbed during testing.
- **Easy to Extend**: New notification channels can be added without ever touching the `NotificationService`.
- **Flexible**: Implementations can be swapped dynamically at runtime.
- **Adheres to DIP**: The dependency direction is inverted, relying on abstractions rather than concretions.

## Key Takeaways

1. Program to interfaces (or abstract classes), not to concrete implementations.
2. Use dependency injection to supply concrete implementations to your classes.
3. High-level modules should define the abstractions they require.
4. Low-level modules should implement those abstractions.
5. This pattern effectively inverts the traditional top-down dependency flow.

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

- Service layers depending on repository interfaces rather than concrete database adapters.
- Web controllers depending on service interfaces.
- Notification systems supporting multiple broadcast channels.
- Data access layers designed to support multiple database vendors.
- Authentication systems allowing multiple strategies (e.g., OAuth, JWT, basic auth).
