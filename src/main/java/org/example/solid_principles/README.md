# SOLID Principles in Java

This directory contains examples demonstrating the SOLID principles of object-oriented design. Each principle has a dedicated directory with "before" and "after" implementations to help illustrate both the design flaws and their corresponding solutions.

## What are SOLID Principles?

SOLID is an acronym representing five design principles intended to make software designs more understandable, flexible, and maintainable over time:

1. **S** - Single Responsibility Principle (SRP)
2. **O** - Open/Closed Principle (OCP)
3. **L** - Liskov Substitution Principle (LSP)
4. **I** - Interface Segregation Principle (ISP)
5. **D** - Dependency Inversion Principle (DIP)

## Structure

Each principle's directory includes:
- `before/`: Code demonstrating the typical issues encountered when the principle is ignored.
- `after/`: Refactored code showing how applying the principle resolves those issues.
- `README.md`: A detailed breakdown of the principle, the core problem, and the applied solution.

## How to Use This Repository

1. Start with the `README.md` in each principle's directory to understand the core concept.
2. Review the code in the `before` directory to see the real-world problems caused by violating the principle.
3. Study the code in the `after` directory to understand how the refactoring addresses those problems.
4. Run the examples locally to see the behavioral differences in action.

## Principles Overview

### 1. Single Responsibility Principle (SRP)
*"A class should have only one reason to change."*
Focuses on ensuring that a module or class is responsible for exactly one part of the software's functionality.

### 2. Open/Closed Principle (OCP)
*"Software entities should be open for extension but closed for modification."*
Encourages designing modules that can be extended with new behavior without altering existing code.

### 3. Liskov Substitution Principle (LSP)
*"Objects of a superclass should be replaceable with objects of a subclass without breaking the application."*
Ensures that inheritance hierarchies are semantically sound and that subclasses honor the contracts of their base classes.

### 4. Interface Segregation Principle (ISP)
*"No client should be forced to depend on methods it does not use."*
Advocates for creating small, specific interfaces rather than large, monolithic ones.

### 5. Dependency Inversion Principle (DIP)
*"Depend on abstractions, not concretions."*
Helps decouple high-level logic from low-level implementations by ensuring both depend on shared abstractions.
