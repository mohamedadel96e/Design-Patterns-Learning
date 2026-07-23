# Bridge Design Pattern - A Practical Learning Journey

## Concept Summary

The Bridge pattern separates an abstraction from its implementation so the two can vary independently.

Think of it as splitting a product into two layers:

- the **what** the client uses
- the **how** that work is performed

That separation prevents inheritance trees from exploding when you have multiple dimensions of change.

## Why It Matters

In real systems you often need to combine two axes of variation:

- different user-facing behaviors
- different backend implementations

Without Bridge, you end up creating a subclass for every combination.

## Real-Life Scenario in This Folder

We model remote controls for smart devices:

- a **remote control abstraction** with power and volume actions
- multiple **devices** such as a TV, radio, and projector

In the `before` version, each remote is hard-wired to one device type.
In the `after` version, the remote depends on a `Device` interface, so any remote can work with any device.

## The Core Problem in the Before Version

Open [before/Main.java](before/Main.java) and [before/TvRemoteControl.java](before/TvRemoteControl.java).

Notice how the code repeats the same remote behavior for each device:

1. A TV remote and a radio remote both implement the same power and volume logic.
2. Adding a new device means adding another remote class.
3. Adding a new remote style means touching every device-specific remote.

This is the kind of class explosion Bridge is designed to prevent.

## Bridge Pattern Definition

The Bridge pattern decouples an abstraction from its implementation so each can evolve independently.

Instead of inheriting behavior into every combination, you compose an abstraction with an implementor.

## Pattern Roles in This Example

The `after` folder maps Bridge roles to concrete classes:

1. **Abstraction**: [after/RemoteControl.java](after/RemoteControl.java)
2. **Refined abstraction**: [after/BasicRemoteControl.java](after/BasicRemoteControl.java)
3. **Another refined abstraction**: [after/AdvancedRemoteControl.java](after/AdvancedRemoteControl.java)
4. **Implementor interface**: [after/Device.java](after/Device.java)
5. **Concrete implementors**: [after/Tv.java](after/Tv.java), [after/Radio.java](after/Radio.java), [after/Projector.java](after/Projector.java)

## How the After Version Works Step by Step

Open [after/Main.java](after/Main.java) and trace the flow:

1. `RemoteControl` holds a `Device` reference.
2. `BasicRemoteControl` and `AdvancedRemoteControl` reuse the same abstraction logic.
3. The concrete device decides how enable, disable, and volume behavior is stored.
4. A new device can be added without changing the remote classes.
5. A new remote style can be added without changing the device classes.

That is the Bridge payoff: two independent hierarchies that can mix and match.

## Adapter vs Bridge

The Adapter example in this repository focuses on translating one interface into another.

Bridge is different:

- **Adapter** fixes incompatibility.
- **Bridge** prevents a design from becoming rigid in the first place.

If your problem is "these APIs do not match," think Adapter.
If your problem is "I need two evolving dimensions without subclass explosion," think Bridge.

## Brainstorming Walkthrough (Question -> Hypothesis -> Experiment)

### Question

If we add another device type and another remote style, should we duplicate every combination?

### Hypothesis

If the remote and device are separated by a Bridge, only the new remote or the new device needs to change.

### Experiment

1. Compare [before/TvRemoteControl.java](before/TvRemoteControl.java) and [before/RadioRemoteControl.java](before/RadioRemoteControl.java).
2. Compare them with [after/RemoteControl.java](after/RemoteControl.java).
3. Notice how the after version isolates device behavior behind a single interface.

### Result

The after version removes the inheritance explosion and keeps both axes of change independent.

## Trade-offs and Engineering Reality

Bridge adds indirection and more small classes.
Use it when:

1. You have two or more dimensions of variation.
2. You expect both sides to evolve independently.
3. You want to avoid a subclass-per-combination design.

Avoid it when:

1. The design is simple and unlikely to grow.
2. A few direct classes are easier to understand.
3. You would be adding abstractions without a real need.

## Common Pitfalls

- Creating a Bridge when one simple inheritance hierarchy is enough
- Letting the abstraction leak device-specific behavior
- Building a fake interface that only exists to satisfy the pattern

## Glossary

- **Abstraction**: The high-level API the client uses.
- **Implementor**: The lower-level interface that performs the work.
- **Refined Abstraction**: A specialized version of the abstraction.

## How to Run the Example

From the `Structural/Bridge` directory:

```bash
javac Structural/Bridge/before/*.java
java Structural.Bridge.before.Main

javac Structural/Bridge/after/*.java
java Structural.Bridge.after.Main
```

To run the lightweight assertion-based test in the `after` version:

```bash
javac Structural/Bridge/after/*.java
java -ea Structural.Bridge.after.BridgeTest
```

## Final Reflection

Bridge helps you design for change instead of reacting to it.

When you see a class hierarchy starting to multiply across combinations, ask:

"Should these two concerns be inherited together, or should they be bridged?"

If the answer is "they should vary independently," Bridge is probably the right tool.

## Next Exercises

1. Add a `SoundBar` device without changing any remote control classes.
2. Add a `VoiceRemoteControl` that extends `RemoteControl` and adds voice commands.
3. Add a `mute` button to `BasicRemoteControl` and compare the result with `AdvancedRemoteControl`.
