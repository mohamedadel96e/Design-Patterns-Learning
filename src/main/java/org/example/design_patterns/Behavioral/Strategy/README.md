# Strategy Design Pattern

## A Real-World Scenario

Consider building an e-commerce payment processing system. Customers can pay using various methods:
- Credit Card
- PayPal
- Cryptocurrency
- Bank Transfer

Each payment method has its own processing logic, validation rules, and transaction fees.

---

## The Initial Implementation

A common starting point for this requirement often looks like a monolithic conditional block:

```java
public class PaymentProcessor {
    public PaymentResult processPayment(PaymentRequest request) {
        if (request.getMethod().equals("CREDIT_CARD")) {
            // Credit card logic
            validateCreditCard(request);
            double fee = request.getAmount() * 0.029 + 0.30;
            // process credit card...
        } else if (request.getMethod().equals("PAYPAL")) {
            // PayPal logic
            validatePayPalAccount(request);
            double fee = request.getAmount() * 0.034;
            // process PayPal...
        } else if (request.getMethod().equals("CRYPTO")) {
            // Crypto logic
            validateWalletAddress(request);
            double fee = request.getAmount() * 0.01;
            // process crypto...
        } else if (request.getMethod().equals("BANK_TRANSFER")) {
            // Bank transfer logic
            validateBankAccount(request);
            double fee = 5.00; // flat fee
            // process bank transfer...
        }
    }
}
```

At first glance, this gets the job done. However, as the system evolves, several issues begin to surface.

---

## Identifying the Problems

### Problem 1: Violation of Open-Closed Principle
When the business needs to support Apple Pay or Google Pay, we have to modify the `PaymentProcessor` class by appending more `else if` statements. This violates the Open-Closed Principle (open for extension, closed for modification).

```java
// Modifying existing code to add new behavior
else if (request.getMethod().equals("APPLE_PAY")) {
    // Add new logic here...
}
```

### Problem 2: Code Becomes Unmaintainable
As we add more payment methods, the `processPayment()` method grows significantly. What starts as 80 lines for four methods quickly scales to hundreds of lines. This conditional complexity becomes difficult to navigate.

### Problem 3: Difficult to Test
Testing the credit card logic in isolation is challenging. The logic is embedded within a method that handles all payment methods, requiring you to set up test data specifically to trigger that branch and potentially mocking the entire `PaymentProcessor`.

### Problem 4: Code Duplication
Different payment methods often share common operations like logging, error handling, result creation, and transaction recording. This leads to duplicated code across the branches.

### Problem 5: Cannot Change Behavior at Runtime
If a user starts with PayPal but decides to switch to a Credit Card during checkout, the payment method is determined by a string check. There is no clean mechanism to dynamically swap the processing algorithm.

### Problem 6: Hard to Understand
For a developer navigating the codebase, locating specific payment processing logic means sifting through a large method containing unrelated branches.

---

## Discovering the Pattern

Looking closely at the implementation:
1. We have multiple algorithms for the same task (processing a payment).
2. We need to select one algorithm based on user choice.
3. The algorithms are interchangeable from the client's perspective.
4. Each algorithm is conceptually independent of the others.

The payment methods represent different strategies for accomplishing the same goal. This maps directly to the Strategy Pattern.

---

## Understanding the Strategy Pattern

### Definition
The Strategy Pattern defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

### The Core Concept
Instead of housing all algorithms in one place:
1. Extract each algorithm into its own class.
2. Make all algorithms implement a common interface.
3. The client holds a reference to the strategy interface.
4. The client can switch strategies at runtime.

### Structure
```
[Context] ──uses──> [Strategy Interface]
                           ▲
                           │
        ┌──────────────┬───┴────┬──────────────┐
        │              │        │              │
[ConcreteStrategy1] [Concrete...] [ConcreteStrategyN]
```

---

## Applying the Pattern

We can refactor the payment processor in a few structured steps.

### Step 1: Define the Strategy Interface
Create a common interface for all payment strategies:

```java
public interface PaymentStrategy {
    PaymentResult processPayment(PaymentRequest request);
    double calculateFee(double amount);
    boolean validate(PaymentRequest request);
    String getPaymentMethodName();
}
```

### Step 2: Extract Each Algorithm
Move each payment method into its own dedicated class:

```java
public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        // Credit card specific logic
    }
    
    @Override
    public double calculateFee(double amount) {
        return amount * 0.029 + 0.30;
    }
    // ... other methods
}

public class PayPalStrategy implements PaymentStrategy {
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        // PayPal specific logic
    }
    
    @Override
    public double calculateFee(double amount) {
        return amount * 0.034;
    }
    // ... other methods
}
```

### Step 3: Update the Context
The processor now delegates the actual work to the injected strategy:

```java
public class PaymentProcessor {
    private PaymentStrategy strategy;
    
    public PaymentProcessor(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }
    
    public PaymentResult processPayment(PaymentRequest request) {
        return strategy.processPayment(request);
    }
}
```

---

## Benefits Achieved

### 1. Open-Closed Principle
Adding a new payment method simply involves creating a new class that implements the interface. The existing processor code remains untouched.

```java
public class ApplePayStrategy implements PaymentStrategy {
    // Implement methods
}
```

### 2. Single Responsibility
Each strategy class is solely responsible for handling its specific payment method. The `PaymentProcessor` focuses purely on coordinating the process.

### 3. Improved Testability
We can test each strategy independently:

```java
@Test
public void testCreditCardFee() {
    CreditCardStrategy strategy = new CreditCardStrategy();
    assertEquals(3.20, strategy.calculateFee(100.00));
}
```

### 4. Runtime Flexibility
Strategies can be hot-swapped during execution:

```java
PaymentProcessor processor = new PaymentProcessor(new PayPalStrategy());
// Process with PayPal...

// User decides to change payment method
processor.setStrategy(new CreditCardStrategy());
// Process with Credit Card...
```

### 5. Code Clarity and Maintenance
- Giant conditional chains are eliminated.
- Files are focused and smaller.
- Modifying a strategy (e.g., updating fee calculation) only impacts that specific class, reducing the risk of regressions.

---

## When to Use Strategy Pattern

Use Strategy When:
1. You have multiple algorithms for the same task.
2. You want to switch between algorithms at runtime.
3. You have conditional statements choosing between different variants of similar behavior.
4. You want to isolate business logic from implementation details.
5. You need to add new algorithms without modifying existing code.

Avoid Strategy When:
1. You only have one or two variants, making the abstraction unnecessary overhead.
2. The algorithms are tightly coupled to the context state.
3. The algorithms rarely change.

---

## Code Structure in This Project

### Before
```
before/
├── Main.java                    # Demonstrates the problems
├── PaymentProcessor.java        # Monolithic conditional logic
├── PaymentRequest.java          # Payment data
└── PaymentResult.java           # Result data
```

### After
```
after/
├── Main.java                    # Clean demonstration
├── PaymentStrategy.java         # Strategy interface
├── CreditCardStrategy.java      # Credit card implementation
├── PayPalStrategy.java          # PayPal implementation
├── CryptoStrategy.java          # Cryptocurrency implementation
├── BankTransferStrategy.java    # Bank transfer implementation
├── PaymentProcessor.java        # Simple context class
├── PaymentRequest.java          # Payment data
└── PaymentResult.java           # Result data
```

---

## Key Takeaways

1. Start Simple, Refactor When Needed: The initial code is fine for basic requirements. Patterns become valuable as complexity increases.
2. Patterns Solve Problems: Apply patterns to solve specific design issues, not preemptively.
3. Favor Composition Over Inheritance: Strategy uses composition (has-a) rather than inheritance (is-a).
4. Open-Closed Principle: The Strategy Pattern is a primary example of designing for extension rather than modification.

---

## Comparison with Other Patterns

Strategy vs State Pattern:
- Strategy: The client generally dictates which strategy to use.
- State: The context changes its state automatically based on internal rules.

Strategy vs Command Pattern:
- Strategy: Focuses on how a specific task is done (the algorithm).
- Command: Focuses on what action to perform (encapsulating a request).

Strategy vs Template Method:
- Strategy: Relies on composition.
- Template Method: Relies on inheritance.

---

## Real-World Examples

In Java/JDK:
- `Comparator` interface for sorting strategies.
- `LayoutManager` in Swing for component layout strategies.
- `ThreadPoolExecutor.RejectedExecutionHandler` for handling rejected tasks.

In Popular Libraries:
- Spring's `CacheManager` implementations.
- Jackson's `JsonSerializer` variants.

---

## Reflection

Consider your current projects:
- Are there large conditional statements driving varied business rules?
- Do you frequently add new behavior variations?
- Could those variations be abstracted into interchangeable components?

Use patterns pragmatically to keep your design flexible and maintainable.
