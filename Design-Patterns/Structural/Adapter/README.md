# Adapter Design Pattern - A Practical Learning Journey

## Concept Summary

The Adapter pattern lets objects with incompatible interfaces work together by translating one interface into another that the client expects.

Think of it as a translator: the client speaks a specific "language" (an interface), and the adapter converts requests so the legacy or third-party class can respond without being changed.

## Why It Matters

In real systems you constantly integrate:

- Legacy components you cannot change
- Third-party SDKs that do not match your interface
- Internal modules that evolved at different times

Without an adapter, integration logic leaks into business code and multiplies across services.

## Real-Life Scenario in This Folder

We model a checkout flow that must work with two payment systems:

- A **modern gateway** that accepts a `PaymentRequest`
- A **legacy gateway** that only accepts `accountId` and `amountInCents`

In the `before` version, `CheckoutService` knows both APIs and performs manual conversion.
In the `after` version, `CheckoutService` talks only to a `PaymentProcessor` interface.

## The Core Problem in the Before Version

Open [before/Main.java](before/Main.java) and [before/CheckoutService.java](before/CheckoutService.java).

Notice how integration logic lives inside business logic:

1. The service knows how to call both gateways.
2. The service converts `Order` into each gateway's expected input.
3. Adding a third gateway would require more branching and more conversion logic.

This is exactly the coupling the Adapter pattern removes.

## Adapter Pattern Definition

The Adapter pattern converts the interface of a class into another interface clients expect.

It allows the client to remain stable even when a new or incompatible dependency is introduced.

## Pattern Roles in This Example

The `after` folder maps classical Adapter roles to concrete classes:

1. **Target interface**: [after/PaymentProcessor.java](after/PaymentProcessor.java)
2. **Client**: [after/CheckoutService.java](after/CheckoutService.java)
3. **Adaptee (legacy API)**: [after/LegacyPaymentGateway.java](after/LegacyPaymentGateway.java)
4. **Adapter**: [after/LegacyGatewayAdapter.java](after/LegacyGatewayAdapter.java)
5. **Another concrete implementation**: [after/ModernPaymentProcessor.java](after/ModernPaymentProcessor.java)

## How the After Version Works Step by Step

Open [after/Main.java](after/Main.java) and trace the flow:

1. `CheckoutService` only knows `PaymentProcessor`.
2. For the legacy system, we pass `LegacyGatewayAdapter`.
3. The adapter converts `Order` into the legacy method call.
4. For the modern system, we pass `ModernPaymentProcessor`.
5. The service stays unchanged in both cases.

That is the adapter payoff: stable business code, flexible integrations.

## Brainstorming Walkthrough (Question -> Hypothesis -> Experiment)

### Question

If our checkout flow must support a new payment SDK, should we edit every service or just one new class?

### Hypothesis

If we introduce a `PaymentProcessor` interface and adapt each gateway to it, only the adapter layer changes.

### Experiment

1. Compare [before/CheckoutService.java](before/CheckoutService.java) to [after/CheckoutService.java](after/CheckoutService.java).
2. Notice how the after version contains **zero** gateway-specific logic.
3. Add a third gateway (try it yourself) by creating another `PaymentProcessor` implementation.

### Result

The checkout flow compiles without modification, proving that integration complexity is isolated in the adapter layer.

## Trade-offs and Engineering Reality

The Adapter pattern introduces extra classes and indirection.
Use it when:

1. You integrate APIs you cannot change.
2. Client code must stay stable as integrations change.
3. Conversion logic is reused in more than one place.

Avoid it when:

1. You control both sides and can align interfaces directly.
2. The conversion is a one-off script with no reuse.

## Common Pitfalls

- Placing conversion logic inside the client instead of the adapter
- Creating a single "god adapter" that handles many different APIs
- Adapting too early when there is no real incompatibility

## Glossary

- **Target**: The interface your code expects.
- **Adaptee**: The incompatible class you want to use.
- **Adapter**: The translator between them.

## How to Run the Example

From the `Structural/Adapter` directory:

```bash
javac before/*.java
java before.Main

javac after/*.java
java after.Main
```

To run the lightweight assertion-based test in the `after` version:

```bash
javac after/*.java
java -ea after.AdapterTest
```

## Final Reflection

The Adapter pattern is about protecting the core of your application from change at the edges.
When a new API arrives, the business code should not care.

Ask yourself this question whenever you integrate a new dependency:

"Where should the conversion logic live so the rest of the system stays calm?"

If the answer is "inside a small adapter class," you are applying the pattern well.

## Next Exercises

1. Add a `CryptoPaymentGateway` with a different method signature and adapt it.
2. Add a `FraudCheckAdapter` that wraps a third-party service and returns a local `FraudRisk` object.
3. Replace the `PaymentProcessor` return type with a richer `PaymentResult` that includes latency and fees.

