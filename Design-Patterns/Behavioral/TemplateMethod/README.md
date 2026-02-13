# Template Method Pattern

## Overview
The **Template Method Pattern** is a behavioral design pattern that defines the skeleton of an algorithm in a base class, allowing subclasses to override specific steps of the algorithm without changing its structure.

## Intent
- Define the skeleton of an algorithm in a method, deferring some steps to subclasses
- Let subclasses redefine certain steps of an algorithm without changing the algorithm's structure
- Achieve code reuse by extracting common behavior into a base class
- Invert the control structure: "don't call us, we'll call you"

## Problem
When you have multiple classes that perform similar operations with only minor variations, you often end up with:
- **Code duplication**: The same algorithm structure repeated across multiple classes
- **Maintenance issues**: Changes to the algorithm require updates in multiple places
- **Inconsistency**: Easy to make mistakes when copying and modifying similar code

## Solution
The Template Method pattern solves this by:
1. **Extracting common behavior** into an abstract base class
2. **Defining a template method** that outlines the algorithm's structure
3. **Allowing subclasses** to provide specific implementations for certain steps
4. **Using hooks** (optional methods) for additional customization points

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
In this example, we'll create a document report generation system where different types of reports (PDF, HTML, CSV) follow the same generation process but have specific implementations for each step.

### Before: Code Duplication
All report generators duplicate the same algorithm structure with only minor variations in specific steps.

**Problems:**
- Duplicated report generation flow across multiple classes
- Changes to the generation process require updates in multiple places
- Difficult to ensure consistency across all report types
- Adding a new report type means copying and modifying existing code

### After: Template Method Pattern
We extract the common report generation algorithm into an abstract base class, allowing subclasses to implement only the specific steps.

**Benefits:**
- ✅ Algorithm structure defined once in the base class
- ✅ Subclasses only implement the variant parts
- ✅ Changes to the algorithm flow affect all subclasses automatically
- ✅ Easy to add new report types by extending the base class
- ✅ Ensures consistent process across all report types

## When to Use
- When multiple classes have algorithms with similar structures but different implementations
- When you want to let subclasses override only certain parts of an algorithm
- When you want to avoid code duplication in similar algorithms
- When you want to control the extension points of an algorithm

## When Not to Use
- When the algorithm doesn't have a clear, fixed structure
- When subclasses need to change the algorithm's structure (not just its steps)
- When you need runtime flexibility to change the algorithm (consider Strategy pattern)
- When the algorithm is simple and doesn't benefit from this structure

## Related Patterns
- **Strategy Pattern**: Encapsulates entire algorithms, making them interchangeable at runtime
- **Factory Method**: A specialized version of Template Method for creating objects
- **Hook Method**: A technique used within Template Method for optional customization

## Key Takeaways
1. Template Method uses **inheritance** to vary parts of an algorithm
2. The template method should be **final** to prevent subclasses from changing the algorithm structure
3. **Abstract methods** represent required variation points
4. **Hook methods** represent optional variation points with default implementations
5. Promotes the **Hollywood Principle**: "Don't call us, we'll call you"
