# Single Responsibility Principle (SRP)

## Overview
*"A class should have only one reason to change."* - Robert C. Martin

In practice, a class should have one and only one responsibility. It should handle a single job, meaning there should only be one domain-specific reason to modify its internal code.

## The Problem (Before)

In the `before` directory, there is a `UserManager` class that violates SRP by taking on multiple, unrelated responsibilities:

1. **User Data Management**: Managing the core user information.
2. **Email Notifications**: Handling the logic to send emails to users.
3. **Data Persistence**: Saving and retrieving users from a database.
4. **Data Validation**: Validating incoming user input.
5. **Report Generation**: Creating formatted reports for users.

### Issues with this approach
- **Hard to Maintain**: A change to the email formatting logic requires modifying the `UserManager` class, which also handles database access.
- **Hard to Test**: Writing tests for user management means you also have to navigate or mock the email and database logic.
- **Low Cohesion**: The class acts as a catch-all, doing too many unrelated tasks.
- **High Coupling**: Database access, email delivery, and validation logic are tightly bound together.
- **Violation of SRP**: The class has multiple reasons to change—modifying business logic, updating email templates, altering the database schema, changing validation rules, or adjusting the report format all require editing the same file.

## The Solution (After)

In the `after` directory, the code is refactored to follow SRP by splitting these responsibilities across separate, focused classes:

1. **User**: A simple data model.
2. **UserValidator**: Dedicated to handling user data validation.
3. **UserRepository**: Dedicated to handling database operations.
4. **EmailService**: Dedicated to managing email notifications.
5. **UserReportGenerator**: Dedicated to formatting and generating reports.
6. **UserService**: Orchestrates the high-level workflow by coordinating the other focused services.

### Benefits of this approach
- **Easier to Maintain**: Each class has a single, clearly defined responsibility.
- **Easier to Test**: Components can be easily isolated and tested independently.
- **High Cohesion**: Each class is strictly focused on its specific domain.
- **Low Coupling**: Classes can depend on abstractions (interfaces) rather than being bundled together.
- **Flexibility**: Implementations can be swapped out easily (e.g., switching to a different email provider) without touching the core user logic.
- **Reusability**: Individual components, like the `EmailService`, can be reused in other parts of the application.

## Key Takeaways

1. Ensure each class has only one reason to change.
2. Separate cross-cutting concerns into distinct classes.
3. Use dependency injection to manage interactions between these separated classes.
4. Program against interfaces rather than concrete implementations.
5. Keep your classes cohesive and highly focused.

## How to Run

```bash
# Compile the before example
javac before/*.java

# Run the before example
java before.Main

# Compile the after example
javac after/*.java

# Run the after example
java after.Main
```
