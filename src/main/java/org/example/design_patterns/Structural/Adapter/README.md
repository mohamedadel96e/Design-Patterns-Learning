# The Adapter Pattern

## Core Concept

The Adapter pattern allows objects with incompatible interfaces to collaborate. It acts as a translator: the client interacts with an interface it understands, and the adapter translates those calls so the underlying legacy or third-party class can respond without needing modification.

## Why We Need It

In real-world systems, we often need to integrate:
- Legacy components that can't be safely modified
- Third-party SDKs that don't align with our internal interfaces
- Internal modules that evolved independently over time

Without an adapter, integration logic ends up bleeding into the business code, duplicating across services and making maintenance much harder.

## Example Scenario

In this directory, we have a checkout flow that needs to support two payment systems:
- A modern gateway that accepts a `PaymentRequest` object
- A legacy gateway that expects an `accountId` and an `amountInCents`

In the `before` version, `CheckoutService` knows about both APIs and handles the conversion manually. In the `after` version, `CheckoutService` relies entirely on a single `PaymentProcessor` interface.

## The Problem (Before)

If you look at `before/Main.java` and `before/CheckoutService.java`, you'll see the integration logic mixed in with the business logic:
1. The service knows exactly how to invoke both gateways.
2. It converts the `Order` into the specific input required by each gateway.
3. Adding a third gateway would mean adding more branching and conversion logic to the service.

This tight coupling is what the Adapter pattern aims to solve.

## How the Adapter Solves This

The Adapter pattern takes the interface of a class and converts it into another interface that clients expect. This keeps the client stable even when we introduce new or incompatible dependencies.

### Pattern Roles

In the `after` folder, here is how the classes map to the classical Adapter roles:
1. **Target interface**: `after/PaymentProcessor.java`
2. **Client**: `after/CheckoutService.java`
3. **Adaptee (legacy API)**: `after/LegacyPaymentGateway.java`
4. **Adapter**: `after/LegacyGatewayAdapter.java`
5. **Concrete implementation**: `after/ModernPaymentProcessor.java`

### Step-by-Step Flow

Checking `after/Main.java`, we can trace the execution:
1. `CheckoutService` only interacts with `PaymentProcessor`.
2. When using the legacy system, we pass in a `LegacyGatewayAdapter`.
3. The adapter translates the `Order` into the appropriate legacy method calls.
4. When using the modern system, we provide a `ModernPaymentProcessor`.
5. `CheckoutService` remains completely unchanged in both scenarios.

The payoff here is clear: our business code remains stable while allowing flexible integrations.

## Design Trade-offs

Like any pattern, Adapter introduces extra classes and indirection.

Consider using it when:
- You're integrating APIs that you can't or shouldn't change.
- Client code needs to remain stable as integrations evolve.
- The conversion logic is reused in multiple places.

You might want to avoid it when:
- You control both ends of the integration and can simply align their interfaces.
- The conversion is a simple one-off script with no need for reuse.

## Common Pitfalls

- Leaking conversion logic into the client rather than keeping it strictly in the adapter.
- Creating a massive "god adapter" that attempts to handle many different APIs at once.
- Introducing adapters prematurely before there's an actual incompatibility.

## Running the Example

From the `Structural/Adapter` directory:

```bash
# Run the before version
javac before/*.java
java before.Main

# Run the after version
javac after/*.java
java after.Main

# Run tests
javac after/*.java
java -ea after.AdapterTest
```

## Final Thoughts

The Adapter pattern is primarily about protecting your application's core from external changes. When a new API is introduced, your business code shouldn't need to care.

Whenever you're integrating a new dependency, it's worth asking: "Where should the conversion logic live to keep the rest of the system clean?" If the answer points to a small adapter class, you're likely on the right track.
