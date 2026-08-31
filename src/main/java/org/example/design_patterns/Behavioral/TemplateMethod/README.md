# Template Method Pattern

## Overview
The Template Method Pattern is a behavioral design pattern that defines the skeleton of an algorithm in a base class, allowing subclasses to override specific steps of the algorithm without changing its structure.

## Intent
- Define the skeleton of an algorithm in a method, deferring some steps to subclasses.
- Let subclasses redefine certain steps of an algorithm without changing the algorithm's overall structure.
- Achieve code reuse by extracting common behavior into a base class.
- Invert the control structure: allowing the base class to control the flow and call subclass implementations when needed.

## Problem
When you have multiple classes that perform similar operations with only minor variations, you often encounter:
- Code duplication: The same algorithmic structure is repeated across multiple classes.
- Maintenance issues: Changes to the core algorithm require updates in multiple places.
- Inconsistency: It is easy to introduce errors when copying and modifying similar code.

## Solution
The Template Method pattern addresses this by:
1. Extracting common behavior into an abstract base class.
2. Defining a template method that outlines the structural flow of the algorithm.
3. Permitting subclasses to provide specific implementations for variant steps.
4. Optionally using hook methods to provide extension points for further customization.

## Structure
```
AbstractClass
├── templateMethod() [final]    // Defines the algorithm skeleton
├── primitiveOperation1()       // Abstract: must be implemented
├── primitiveOperation2()       // Abstract: must be implemented
└── hook()                      // Hook: optional override

ConcreteClassA extends AbstractClass
├── primitiveOperation1()       // Specific implementation
└── primitiveOperation2()       // Specific implementation

ConcreteClassB extends AbstractClass
├── primitiveOperation1()       // Different implementation
├── primitiveOperation2()       // Different implementation
└── hook()                      // Optionally overridden
```

## Real-World Example: Document Report Generation
Consider a document report generation system where different types of reports (PDF, HTML, CSV) follow a standard generation process but require specific rendering for each step.

### The Initial Implementation
In a standard approach, report generators often duplicate the flow structure, changing only the rendering details.

Problems with this approach:
- Duplicated report generation logic across multiple classes.
- Modifying the generation process requires changing every report class.
- Ensuring consistency across all report types is challenging.
- Adding a new report type involves copying existing code and modifying it.

### Applying the Template Method Pattern
By extracting the common generation steps into an abstract base class, subclasses only need to implement the specific rendering details.

Benefits of this approach:
- Algorithm structure is defined in a single place.
- Subclasses are focused on their specific variations.
- Changes to the core process propagate to all subclasses automatically.
- New report types can be added efficiently by extending the base class.
- A consistent process is enforced across the system.

## When to Use
- When multiple classes execute algorithms with similar structures but different underlying step implementations.
- When you want to allow subclasses to override only certain steps, rather than the entire algorithm.
- When you need to reduce code duplication across similar processes.
- When you want explicit control over the extension points in your algorithm.

## When Not to Use
- When the algorithm does not possess a fixed, consistent structure.
- When subclasses need to modify the flow of the algorithm entirely.
- When you require runtime flexibility to swap the algorithm (in which case, the Strategy pattern is more suitable).
- When the problem is simple enough that the added abstraction layer provides little benefit.

## Related Patterns
- Strategy Pattern: Encapsulates entire algorithms, allowing them to be swapped dynamically at runtime using composition.
- Factory Method: A specialized use of Template Method focusing on object creation.
- Hook Method: A structural component often utilized within Template Methods to offer optional overriding capabilities.

## Key Takeaways
1. Template Method relies on inheritance to vary parts of an algorithm.
2. The template method itself is typically marked as final to prevent subclasses from altering the defined sequence.
3. Abstract methods dictate the required variations that subclasses must fulfill.
4. Hook methods offer optional overrides, typically providing empty or default implementations.
5. The pattern exemplifies the Hollywood Principle: "Don't call us, we'll call you," by letting the superclass dictate execution flow.
