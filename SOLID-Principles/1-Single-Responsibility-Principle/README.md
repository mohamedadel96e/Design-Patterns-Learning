# Single Responsibility Principle (SRP)

## Definition
*"A class should have only one reason to change."* - Robert C. Martin

A class should have one and only one responsibility. It should have only one job and one reason to be modified.

## The Problem (Before)

In the `before` folder, we have a `UserManager` class that violates SRP by handling multiple responsibilities:

1. **User Data Management** - Managing user information
2. **Email Notifications** - Sending emails to users
3. **Data Persistence** - Saving users to database
4. **Data Validation** - Validating user input
5. **Report Generation** - Creating user reports

### Issues with this approach:
- **Hard to maintain**: Changes to email logic require modifying the UserManager class
- **Hard to test**: Testing user management also means dealing with email and database logic
- **Low cohesion**: The class does too many unrelated things
- **High coupling**: Database, email, and validation logic are tightly coupled
- **Violation of SRP**: The class has multiple reasons to change (business logic, email templates, database schema, validation rules, report format)

## The Solution (After)

In the `after` folder, we've refactored the code to follow SRP by splitting responsibilities into separate classes:

1. **User** - Simple data model
2. **UserValidator** - Handles user data validation
3. **UserRepository** - Handles database operations
4. **EmailService** - Handles email notifications
5. **UserReportGenerator** - Handles report generation
6. **UserService** - Orchestrates the workflow using other services

### Benefits of this approach:
- ✅ **Easy to maintain**: Each class has a single, well-defined responsibility
- ✅ **Easy to test**: Each component can be tested in isolation
- ✅ **High cohesion**: Each class focuses on one thing
- ✅ **Low coupling**: Classes depend on abstractions (interfaces)
- ✅ **Flexible**: Easy to swap implementations (e.g., change email provider)
- ✅ **Reusable**: Components can be reused in different contexts

## Key Takeaways

1. Each class should have only one reason to change
2. Separate concerns into different classes
3. Use dependency injection to manage dependencies
4. Program to interfaces, not implementations
5. Keep classes focused and cohesive

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
