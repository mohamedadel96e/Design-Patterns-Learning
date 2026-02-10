# Strategy Design Pattern - A Step-by-Step Journey

## 🎯 Let's Start with a Real-World Scenario

Imagine you're building an **e-commerce payment processing system**. Customers can pay using:
- Credit Card
- PayPal
- Cryptocurrency
- Bank Transfer

Each payment method has its own processing logic, validation rules, and transaction fees.

---

## 🤔 Step 1: The Initial Implementation (The Problem)

### How would you typically code this?

Most developers (myself included!) would start with something like this:

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

**At first glance, this seems reasonable, right?** It's straightforward and gets the job done.

---

## 🚨 Step 2: Identifying the Problems

But as we work with this code, problems start to emerge...

### Problem #1: Violation of Open-Closed Principle
**Question:** What happens when the business wants to add Apple Pay or Google Pay?

**Answer:** We have to **modify** the `PaymentProcessor` class, adding more `else if` statements. This violates the Open-Closed Principle (open for extension, closed for modification).

```java
// Now we have to modify existing code!
else if (request.getMethod().equals("APPLE_PAY")) {
    // Add new logic here...
}
```

### Problem #2: Code Becomes Unmaintainable
As we add more payment methods, the `processPayment()` method grows into a **monster**:

```
Lines of code in processPayment():
- With 4 payment methods: ~80 lines
- With 8 payment methods: ~160 lines
- With 12 payment methods: ~240 lines
```

This is often called **"if-else hell"** or **"conditional complexity"**.

### Problem #3: Difficult to Test
**Question:** How do you test just the credit card logic?

**Answer:** You can't easily! The logic is buried inside the method with all other payment methods. You have to:
- Mock the entire `PaymentProcessor`
- Set up test data to trigger the specific branch
- Test through the entire method, not just the credit card logic

### Problem #4: Code Duplication
Each payment method likely shares some common operations:
- Logging
- Error handling
- Result creation
- Transaction recording

But we end up **duplicating** this code across all branches.

### Problem #5: Cannot Change Behavior at Runtime
**Scenario:** A user starts with PayPal but wants to switch to Credit Card mid-checkout.

**Problem:** The payment method is determined by a string check. There's no clean way to swap algorithms dynamically.

### Problem #6: Hard to Understand
**Question:** For a new developer, where is the credit card processing logic?

**Answer:** Buried somewhere in a huge method among dozens of other branches. Good luck finding it!

---

## 💡 Step 3: Discovering the Pattern

Let's step back and think about what's really happening here:

1. We have **multiple algorithms** for the same task (processing payment)
2. We need to select **one algorithm** based on user choice
3. The algorithms are **interchangeable** from the client's perspective
4. Each algorithm is **independent** of the others

**Key Insight:** The payment methods are different **strategies** for accomplishing the same goal!

This is where the **Strategy Pattern** comes in.

---

## 📚 Step 4: Understanding the Strategy Pattern

### Definition
> **Strategy Pattern** defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it.

### The Key Idea
Instead of having all algorithms in one place, we:
1. **Extract** each algorithm into its own class
2. Make all algorithms implement a **common interface**
3. The client **holds a reference** to the strategy interface
4. The client can **switch strategies** at runtime

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

## 🔧 Step 5: Applying the Pattern to Our Problem

Let's refactor step by step:

### Step 5.1: Define the Strategy Interface
Create a common interface for all payment strategies:

```java
public interface PaymentStrategy {
    PaymentResult processPayment(PaymentRequest request);
    double calculateFee(double amount);
    boolean validate(PaymentRequest request);
    String getPaymentMethodName();
}
```

### Step 5.2: Extract Each Algorithm
Move each payment method into its own class:

```java
public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public PaymentResult processPayment(PaymentRequest request) {
        // ONLY credit card logic here
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
        // ONLY PayPal logic here
    }
    
    @Override
    public double calculateFee(double amount) {
        return amount * 0.034;
    }
    // ... other methods
}
```

### Step 5.3: Update the Context (PaymentProcessor)
The processor now **delegates** to the strategy:

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
        // No more if-else!
        return strategy.processPayment(request);
    }
}
```

---

## ✅ Step 6: Benefits We've Achieved

### ✅ Benefit #1: Open-Closed Principle
Want to add Apple Pay? Just create a new class:

```java
public class ApplePayStrategy implements PaymentStrategy {
    // Implement methods
}
```

**No existing code needs to change!**

### ✅ Benefit #2: Single Responsibility
Each strategy class has **one job**: handle its specific payment method. The `PaymentProcessor` has **one job**: coordinate the payment process.

### ✅ Benefit #3: Easy Testing
```java
@Test
public void testCreditCardFee() {
    CreditCardStrategy strategy = new CreditCardStrategy();
    assertEquals(3.20, strategy.calculateFee(100.00));
}
```

**We can test each strategy in isolation!**

### ✅ Benefit #4: Runtime Flexibility
```java
PaymentProcessor processor = new PaymentProcessor(new PayPalStrategy());
// Process with PayPal...

// User changes mind
processor.setStrategy(new CreditCardStrategy());
// Process with Credit Card...
```

### ✅ Benefit #5: Cleaner Code
- No giant if-else chains
- Each file is small and focused
- Easy to navigate and understand
- New developers can find code quickly

### ✅ Benefit #6: Easier Maintenance
- Bug in PayPal? Fix `PayPalStrategy.java`
- Update credit card fees? Modify `CreditCardStrategy.java`
- Changes are **localized** and **safe**

---

## 🎓 Step 7: When to Use Strategy Pattern

### ✅ Use Strategy When:
1. You have **multiple algorithms** for the same task
2. You want to **switch between algorithms** at runtime
3. You have **conditional statements** choosing between different variants of similar algorithms
4. You want to **isolate** business logic from implementation details
5. You need to **add new algorithms** without modifying existing code

### ❌ Don't Use Strategy When:
1. You only have **one or two** variants (overengineering)
2. The algorithms are **tightly coupled** to the context
3. The algorithms **rarely change**
4. The added abstraction doesn't provide clear benefits

---

## 🏗️ Code Structure in This Project

### Before (Problems)
```
before/
├── Main.java                    # Demonstrates the problems
├── PaymentProcessor.java        # Giant if-else hell
├── PaymentRequest.java          # Payment data
└── PaymentResult.java           # Result data
```

**Run this to see the problems:**
```bash
cd before
javac *.java
java before.Main
```

### After (Solution)
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

**Run this to see the solution:**
```bash
cd after
javac *.java
java after.Main
```

---

## 🎯 Key Takeaways

1. **Start Simple, Refactor When Needed**: The "before" code isn't wrong for simple cases. But as complexity grows, patterns help.

2. **Patterns Solve Problems**: Don't use patterns just to use them. Use them when you encounter the specific problems they solve.

3. **Favor Composition Over Inheritance**: Strategy uses composition (has-a) rather than inheritance (is-a).

4. **Open-Closed Principle**: The Strategy Pattern is a perfect example of being open for extension but closed for modification.

5. **Think in Terms of Behaviors**: Instead of thinking "what is it?", think "what does it do differently?"

---

## 🔄 Comparison with Other Patterns

### Strategy vs State Pattern
- **Strategy**: Client usually chooses which strategy to use
- **State**: Context changes state automatically based on internal logic

### Strategy vs Command Pattern
- **Strategy**: Focuses on **how** something is done (algorithm)
- **Command**: Focuses on **what** action to perform (encapsulates request)

### Strategy vs Template Method
- **Strategy**: Uses **composition** (has-a relationship)
- **Template Method**: Uses **inheritance** (is-a relationship)

---

## 📖 Real-World Examples

### In Java/JDK:
- `Comparator` interface (different sorting strategies)
- `LayoutManager` in Swing (different layout strategies)
- `ThreadPoolExecutor.RejectedExecutionHandler` (rejection strategies)

### In Popular Libraries:
- Spring's `CacheManager` (different caching strategies)
- Apache Commons Collections' `Transformer` (transformation strategies)
- Jackson's `JsonSerializer` (different serialization strategies)

---

## 🚀 Next Steps

1. **Run the code** in `before/` and `after/` directories
2. **Compare** the implementations side-by-side
3. **Try adding** a new payment method (e.g., Apple Pay)
4. **Experiment** by modifying the strategies
5. **Think** about where you could apply this in your own projects

---

## 💭 Reflection Questions

1. Where in your current project do you have large if-else or switch statements?
2. Are there places where you need to add new behavior frequently?
3. Could any algorithms be extracted and made interchangeable?
4. What's stopping you from refactoring to use Strategy Pattern?

Remember: **Patterns are tools, not rules.** Use them when they solve a real problem, not just because they're "best practice."

---

Happy coding! 🎉
