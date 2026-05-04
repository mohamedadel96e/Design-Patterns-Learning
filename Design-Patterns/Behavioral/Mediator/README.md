# Mediator Design Pattern - A Practical Learning Journey

## Why This Pattern Matters

When you build UI or workflow-heavy software, components often need to react to each other.

If every component talks directly to every other component, you get a dense web of dependencies. That web makes components hard to reuse, hard to test, and expensive to change.

The Mediator pattern breaks that web by moving the interaction rules into a single coordinating object. Components become independent, and the coordination logic becomes explicit and centralized.

## Start With a Real-Life Scenario

In this folder, we model a simple profile form with three UI components:

1. `Checkbox` for "I have a dog".
2. `TextBox` for the dog's name.
3. `Button` to submit the form.

The interaction rules are common in real software:

1. When the checkbox is checked, the text box becomes visible.
2. When text is entered, the submit button becomes enabled.
3. When submit is clicked, the form is processed.

If those rules live inside each component, the components become tightly coupled and hard to reuse in other dialogs.

## The Core Problem in the Before Version

Open [before/Main.java](before/Main.java) and [before/Checkbox.java](before/Checkbox.java).

The checkbox directly owns references to `TextBox` and `Button` and performs coordination itself.

### Symptoms you should actively recognize

1. Components know concrete types of other components.
2. Business rules are scattered across UI widgets.
3. Reusing a checkbox in another form requires editing its code.
4. Changing the flow means touching multiple components.

This is a design smell because interaction logic and widget logic are tangled together.

## Mediator Pattern Definition

The Mediator pattern defines an object that encapsulates how a set of objects interact.

Instead of components calling each other directly, they notify the mediator, and the mediator decides what to do next.

## Pattern Roles in This Example

The `after` folder maps classical Mediator roles to concrete classes.

1. **Mediator interface**: [after/Mediator.java](after/Mediator.java)
2. **Concrete mediator**: [after/DialogMediator.java](after/DialogMediator.java)
3. **Base component**: [after/Component.java](after/Component.java)
4. **Concrete components**: [after/Checkbox.java](after/Checkbox.java), [after/TextBox.java](after/TextBox.java), [after/Button.java](after/Button.java)

### Notice the event flow contract

Each component notifies the mediator using a string event such as `check`, `textChanged`, or `click`. This keeps the component simple and keeps the workflow rules in one place.

## How the After Version Works Step by Step

Open [after/Main.java](after/Main.java) and follow the flow:

1. Create `Checkbox`, `TextBox`, and `Button` with no direct references to each other.
2. Create `DialogMediator` and wire the components to it.
3. Trigger user actions on the components.
4. Each component notifies the mediator about its event.
5. The mediator updates other components based on the rules.

At runtime:

1. Checking the checkbox makes the text box visible.
2. Typing in the text box enables the button.
3. Clicking the button triggers the submit action.

All coordination is centralized in [after/DialogMediator.java](after/DialogMediator.java).

## Design Questions You Should Think Through

Use these prompts while reading the code.

1. If you add a new field and validation rule, which class should you touch?
2. If you want to reuse `Checkbox` in a different dialog, do you need to modify it?
3. If the submit rule changes, should UI widgets change?
4. If you add a second dialog, can you reuse components with a new mediator?

If your answers repeatedly point to "the mediator" rather than the UI widgets, you are applying the pattern correctly.

## Trade-offs and Engineering Reality

The Mediator pattern is not free. It introduces a central object and extra routing logic.

You should use it when:

1. Many components interact with each other in non-trivial ways.
2. You want to reuse components across dialogs or workflows.
3. You want interaction rules in one place.

You can avoid it when:

1. There are only one or two simple dependencies.
2. The overhead of a mediator would add noise without benefits.

## What Changed Architecturally From Before to After

Before:

1. Components knew about each other directly.
2. Interaction rules were scattered.
3. Reuse required edits to component code.

After:

1. Components depend only on `Mediator`.
2. Interaction rules are centralized.
3. Reuse means swapping the mediator, not the components.

This is a practical move toward the Single Responsibility Principle and lower coupling.

## Complexity and Maintenance Perspective

### Time complexity

Each user action still triggers a constant amount of work, so the runtime cost stays roughly $O(1)$ per event.

### Structural complexity

You add one mediator class, but you remove hidden dependencies between components. That trade pays off as the UI grows.

## How to Run the Example

From the `Behavioral/Mediator` directory:

```bash
javac before/*.java
java before.Main

javac after/*.java
java after.Main
```

Compare outputs and inspect the component and mediator code.

## Final Reflection

The Mediator pattern is about protecting components from coordination chaos.

When you evaluate a design, ask one powerful question:

"If I change how components interact, how many components must I touch?"

Mediator helps you keep that number low.