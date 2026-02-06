# Open/Closed Principle (OCP)

## Definition
*"Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification."* - Bertrand Meyer

You should be able to extend a class's behavior without modifying its source code.

## The Problem (Before)

In the `before` folder, we have a `PaymentProcessor` class that violates OCP by using conditional statements to handle different payment methods:

```java
if (method.equals("CREDIT_CARD")) {
    // Credit card logic
} else if (method.equals("PAYPAL")) {
    // PayPal logic
} else if (method.equals("BANK_TRANSFER")) {
    // Bank transfer logic
}
```

### Issues with this approach:
- **Modification Required**: Adding a new payment method requires modifying the `PaymentProcessor` class
- **Violates OCP**: The class is not closed for modification
- **Risk of Bugs**: Every modification can introduce bugs in existing functionality
- **Testing Overhead**: Every change requires retesting all payment methods
- **Poor Scalability**: As more payment methods are added, the class becomes harder to maintain
- **Tight Coupling**: Payment logic is tightly coupled with the processor class

## The Solution (After)

In the `after` folder, we've refactored the code to follow OCP using polymorphism and strategy pattern:

1. **PaymentMethod** - Interface defining the contract
2. **CreditCardPayment**, **PayPalPayment**, **BankTransferPayment** - Concrete implementations
3. **PaymentProcessor** - Processes payments without knowing specific payment types
4. **Easy Extension**: New payment methods can be added without modifying existing code

### Benefits of this approach:
- ✅ **Open for Extension**: New payment methods can be added easily
- ✅ **Closed for Modification**: Existing code doesn't need to change
- ✅ **Reduced Risk**: New features don't break existing functionality
- ✅ **Easy Testing**: Each payment method can be tested independently
- ✅ **Better Organization**: Each payment method is in its own class
- ✅ **Loose Coupling**: Payment processor depends on abstraction, not concrete types

## Key Takeaways

1. Use abstraction (interfaces/abstract classes) to define contracts
2. Use polymorphism instead of conditional statements
3. New features should be added by writing new code, not modifying existing code
4. Design for extension points from the beginning
5. Apply strategy pattern for varying behaviors

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

- Payment processing systems
- Notification systems (email, SMS, push)
- Export formats (PDF, CSV, Excel)
- Authentication strategies (OAuth, LDAP, local)
- Shipping calculators (FedEx, UPS, DHL)
