# The Bridge Pattern

## Core Concept

The Bridge pattern separates an abstraction from its implementation so that the two can vary independently. 

Think of it as dividing a component into two distinct layers:
- The abstraction (what the client interacts with)
- The implementation (how the underlying work is actually performed)

This separation is crucial for preventing your inheritance tree from exploding when you need to handle multiple dimensions of change.

## Why We Need It

In practice, we often encounter situations where we need to combine two axes of variation, such as:
- Different user-facing behaviors
- Different backend implementations

Without the Bridge pattern, you typically end up creating a subclass for every possible combination, which scales poorly.

## Example Scenario

In this directory, we're modeling remote controls for smart devices. We have:
- A remote control abstraction that supports power and volume actions
- Multiple devices like a TV, radio, and projector

In the `before` version, each type of remote is tightly coupled to a specific device. In the `after` version, the remote relies on a `Device` interface, allowing any remote to operate any device.

## The Problem (Before)

If you check `before/Main.java` and `before/TvRemoteControl.java`, you'll notice the same remote behavior being duplicated across different devices:
1. Both the TV and radio remotes implement identical power and volume logic.
2. Introducing a new device requires creating a corresponding remote class.
3. Adding a new feature to the remote means modifying every device-specific remote class.

This combinatorial explosion of classes is exactly what Bridge resolves.

## How the Bridge Solves This

Instead of using inheritance for every combination of remote and device, the Bridge pattern uses composition. We decouple the abstraction (the remote) from the implementation (the device).

### Pattern Roles

In the `after` folder, the classes map to the Bridge roles as follows:
1. **Abstraction**: `after/RemoteControl.java`
2. **Refined abstraction**: `after/BasicRemoteControl.java`
3. **Another refined abstraction**: `after/AdvancedRemoteControl.java`
4. **Implementor interface**: `after/Device.java`
5. **Concrete implementors**: `after/Tv.java`, `after/Radio.java`, `after/Projector.java`

### Step-by-Step Flow

Looking at `after/Main.java`, we can see how this plays out:
1. `RemoteControl` holds a reference to a `Device`.
2. `BasicRemoteControl` and `AdvancedRemoteControl` share the same core abstraction logic.
3. The concrete device handles the actual implementation of enabling, disabling, and adjusting volume.
4. We can add a new device without touching the remote classes.
5. We can add a new remote style without altering the device classes.

The result is two independent class hierarchies that can be mixed and matched cleanly.

## Adapter vs. Bridge

While both patterns deal with interfaces, their intents are different:
- **Adapter** is used to resolve incompatibilities between existing interfaces.
- **Bridge** is an upfront design decision to keep two dimensions of a system independent and prevent rigidity.

If you're trying to make two mismatched APIs work together, you probably want an Adapter. If you're designing a system with multiple axes of variation and want to avoid a massive subclass tree, Bridge is the better choice.

## Design Trade-offs

Bridge introduces indirection and requires more initial classes.

Consider using it when:
- You have two or more dimensions of variation.
- You expect both the abstraction and implementation to evolve independently.
- You want to avoid the combinatorial explosion of subclasses.

You might want to avoid it when:
- The design is simple and unlikely to require independent evolution.
- Direct concrete classes are straightforward and sufficient.
- You'd be adding abstraction layers without a clear, immediate benefit.

## Common Pitfalls

- Using a Bridge when a simple inheritance hierarchy would suffice.
- Allowing device-specific implementation details to leak into the abstraction layer.
- Creating interfaces just for the sake of the pattern without real variation.

## Running the Example

From the `Structural/Bridge` directory:

```bash
# Run the before version
javac Structural/Bridge/before/*.java
java Structural.Bridge.before.Main

# Run the after version
javac Structural/Bridge/after/*.java
java Structural.Bridge.after.Main

# Run tests
javac Structural/Bridge/after/*.java
java -ea Structural.Bridge.after.BridgeTest
```

## Final Thoughts

The Bridge pattern is an excellent tool for designing systems that anticipate change. When you notice a class hierarchy starting to multiply rapidly across different combinations, it's worth asking whether those concerns should be inherited together, or if they would be better off bridged. If they need to evolve independently, Bridge is likely the right approach.
