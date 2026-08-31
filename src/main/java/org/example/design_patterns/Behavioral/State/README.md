# State Design Pattern

## Why This Pattern Matters

When designing software, you often encounter a common problem: an object needs to alter its behavior depending on its internal state. 

In a basic implementation, state-dependent behavior is typically handled by sprinkling `if-else` or `switch` statements throughout the object's methods. Every time the object's state changes, a variable is updated, and every method must check this variable before acting. As the number of states and transitions grows, the class inevitably becomes a tangled web of conditionals that is risky to modify and difficult to test in isolation.

The State pattern addresses this by separating two concerns:

1. The logic of each individual state.
2. The context that maintains the current state.

That separation transforms a rigid, monolithic sequence of conditionals into a dynamic, object-oriented state machine.

## A Real-World Scenario

Consider a digital Document Management System. 

A Document can be in one of three states:
1. Draft: The author is writing the document.
2. Moderation: The document is under review by an editor.
3. Published: The document is live to the public.

The system supports actions like `publish()`, `approve()`, and `reject()`. Depending on the document's state, these actions behave differently:
- A Draft document can be published, which moves it to Moderation.
- A Moderation document can be approved (moving it to Published) or rejected (moving it back to Draft).
- A Published document doesn't do anything when approved or rejected, or perhaps it logs a warning.

In a naive implementation, the Document class handles these actions with large blocks of conditionals (e.g., `if (state == DRAFT)`). By applying the State pattern, each state becomes an independent, self-contained class.

## The Core Problem in the Initial Version

If you look at an initial `before/Document.java` implementation, you'll likely see massive `publish()`, `approve()`, and `reject()` methods filled with direct dependencies on state enums and extensive conditional logic.

Symptoms of this design smell include:

1. The class contains deeply nested conditional blocks for every state change.
2. Adding a new state (like Archived) requires modifying almost every core processing method in the Document class.
3. The transition logic is hardcoded and scattered.
4. Testing a single state's behavior requires setting up the entire Document object and navigating through unrelated conditionals.

This indicates that the class handling the document is tightly coupled to every single state and its business rules. 

## State Pattern Definition

The State pattern lets an object alter its behavior when its internal state changes. It appears as if the object changed its class.

You can think of it as a strategy pattern, but for states:

1. The Context delegates state-specific behavior to the current State object.
2. The current State object can transition the Context to another state.

The client interacts with the Context and remains abstracted from the complex state transitions happening behind the scenes.

## Pattern Roles in This Example

Mapping this to a refactored version, the roles look like this:

1. Context: `Document.java` (maintains a reference to the current state and delegates work to it).
2. State Abstraction: `State.java` (abstract class or interface with methods like `publish()`, `approve()`, and `reject()`).
3. Concrete State (Draft): `DraftState.java`
4. Concrete State (Moderation): `ModerationState.java`
5. Concrete State (Published): `PublishedState.java`
6. Client: `Main.java` (interacts with the Document).

This demonstrates a key design insight: once states are abstracted into classes, you can cleanly add new states without modifying existing ones.

## How the Refactored Version Works

In the refactored `after/Main.java` flow:

1. Instantiate the Document (which internally starts with `DraftState`).
2. The client calls `document.publish()`.
3. The Document delegates this to `DraftState.publish()`.
4. `DraftState` performs the required logic and transitions the Document to `ModerationState`.

At runtime:

1. `document.approve()` is called. The current state is now `ModerationState`.
2. `ModerationState` handles the approval, transitioning the Document to `PublishedState`.
3. If `document.publish()` is called again inside `PublishedState`, it might log a message and do nothing, keeping the state intact.

The result is elegant transition management and clear single-responsibility for each state.

## Design Questions to Consider

When evaluating your own state-dependent code, consider the following:

1. If you need to add an Archived state, do you need to modify the existing DraftState or PublishedState?
2. How straightforward is it to track down a bug if a document cannot be approved?
3. Who should be responsible for state transitions: the Context (Document) or the concrete States? In our example, concrete states initiate transitions, but both approaches are valid depending on the complexity of the domain.
