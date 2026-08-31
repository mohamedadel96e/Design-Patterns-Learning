# The Open/Closed Principle (OCP)

## Core Concept

*"Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification."* — Bertrand Meyer

In practice, this means you should be able to add new behavior to a system without changing its existing source code.

## The Problem (Before)

In the `before` folder, the `PaymentProcessor` class violates OCP by relying on conditional logic to handle various payment methods:

```java
if (method.equals("CREDIT_CARD")) {
    // Process credit card
} else if (method.equals("PAYPAL")) {
    // Process PayPal
} else if (method.equals("BANK_TRANSFER")) {
    // Process bank transfer
}
```

This approach introduces several issues:
- **Constant Modification**: Every new payment method requires changing the core `PaymentProcessor` class.
- **High Risk**: Modifying existing code always carries the risk of breaking existing functionality.
- **Testing Burden**: Any change means you need to retest all the existing payment methods to ensure no regressions occurred.
- **Tight Coupling**: The processor is intimately tied to the implementation details of every single payment method.

## The Solution (After)

In the `after` folder, we've refactored the design to respect OCP by leveraging polymorphism and the Strategy pattern:

1. **PaymentMethod**: An interface that defines the contract.
2. **Concrete Implementations**: Classes like `CreditCardPayment`, `PayPalPayment`, and `BankTransferPayment` handle their specific logic.
3. **PaymentProcessor**: Now orchestrates payments by interacting with the interface, completely unaware of the specific implementations.

### Why This Design is Better

- **Open for Extension**: We can drop in a new payment method simply by creating a new class.
- **Closed for Modification**: The `PaymentProcessor` and existing payment classes remain untouched.
- **Safety**: Adding features doesn't jeopardize the stability of existing code.
- **Focused Testing**: Each payment method can be unit tested in isolation.
- **Loose Coupling**: The processor depends only on an abstraction, not on concrete details.

## Key Takeaways

1. Rely on abstractions (interfaces or abstract classes) to define contracts.
2. Replace large conditional blocks with polymorphism.
3. Aim to implement new features by writing new code rather than altering existing code.
4. Identify potential axes of change early and design extension points for them.

## Running the Example

```bash
# Run the before version
javac before/*.java
java before.Main

# Run the after version
javac after/*.java
java after.Main
```

## Real-World Applications

This principle is foundational and shows up everywhere:
- Payment and checkout systems
- Notification dispatchers (email, SMS, push)
- File exporters (PDF, CSV, Excel)
- Authentication providers (OAuth, LDAP, local)
- Shipping rate calculators (FedEx, UPS, DHL)
