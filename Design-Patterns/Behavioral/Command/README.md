# Command Design Pattern - A Practical Learning Journey

## Why This Pattern Matters

In real software, requests come from many places: UI buttons, hotkeys, scheduled jobs, API endpoints, or batch scripts. If those callers directly invoke business logic, the system becomes hard to evolve.

The Command pattern turns a request into an object. That single move unlocks powerful capabilities:

1. You can queue, schedule, log, or replay actions.
2. You can add undo and redo without rewriting the caller.
3. You can decouple the requester from the actual work.

## Start With a Real-Life Example

Imagine a restaurant:

1. The customer tells the waiter what they want.
2. The waiter writes an order ticket.
3. The kitchen reads the ticket and cooks the meal.

The waiter does not cook. The customer does not enter the kitchen. The ticket is the contract between request and execution.

That ticket is a Command object in the real world.

### Mapping the example to Command roles

1. **Invoker**: the waiter who receives the request.
2. **Command**: the written order ticket.
3. **Receiver**: the kitchen that knows how to cook.
4. **Client**: the customer who decides what to order.

Because the order is an object, it can be queued, reordered, or handed to a different cook without changing the customer or waiter behavior.

## The Core Problem in Software

A typical UI button might directly call a service method:

- Save button -> document.save()
- Print button -> printer.print(document)

This looks simple, but it hard-wires the UI to the business layer. It also makes features like undo, macro recording, or delayed execution awkward.

You end up with a growing set of buttons that each contain one-off logic and cannot be reused in a different workflow.

## In This Folder: Job Scheduler With Queued Tasks

In this folder, we model a simple job scheduler that queues tasks like:

1. Database backups
2. Daily report generation
3. Email notifications

The `before` version hard-codes job types inside the scheduler. The `after` version turns each job into a Command object.

## Command Pattern Definition

The Command pattern encapsulates a request as an object, thereby letting you:

- Parameterize clients with different requests.
- Queue or log requests.
- Support undoable operations.

In short: you move the request into its own object and let callers execute it through a uniform interface.

## Pattern Roles and Responsibilities

1. **Command interface**: declares `execute()` (and optionally `undo()`).
2. **Concrete command**: stores the receiver and request data, implements `execute()`.
3. **Receiver**: contains the actual business logic (the work).
4. **Invoker**: triggers commands, does not know how they do the work.
5. **Client**: builds commands, wires them with receivers, assigns them to invokers.

This separation is what makes commands composable, queueable, and testable.

## The Core Problem in the Before Version

Open [before/Main.java](before/Main.java) and [before/JobScheduler.java](before/JobScheduler.java).

You will notice the scheduler knows every concrete job type and every receiver.

### Symptoms you should actively recognize

1. The scheduler must switch on `JobType` to decide what to run.
2. Adding a new job forces edits inside the scheduler.
3. The scheduler holds dependencies on every service it might call.
4. The queue is about jobs, but the scheduler owns all job logic.

## Pattern Roles in This Example

The `after` folder maps the Command roles to concrete classes.

1. **Command interface**: [after/Command.java](after/Command.java)
2. **Concrete commands**: [after/BackupDatabaseCommand.java](after/BackupDatabaseCommand.java), [after/GenerateReportCommand.java](after/GenerateReportCommand.java), [after/SendEmailCommand.java](after/SendEmailCommand.java)
3. **Invoker (scheduler)**: [after/JobScheduler.java](after/JobScheduler.java)
4. **Receivers**: [after/DatabaseBackupService.java](after/DatabaseBackupService.java), [after/ReportService.java](after/ReportService.java), [after/EmailService.java](after/EmailService.java)
5. **Client (wiring)**: [after/Main.java](after/Main.java)

## A Concrete Software Example

Consider a job scheduler that runs queued tasks.

Without Command, the scheduler must understand every job type and call the right service.

With Command:

1. Each job is a command object.
2. The scheduler only knows how to call `execute()`.
3. The receivers hold real business logic.
4. The client wires jobs into the queue.

Now you can:

- Add new jobs without editing the scheduler.
- Queue jobs from different parts of the system.
- Log or retry jobs uniformly.
- Swap or reorder commands without touching receivers.

## How the Flow Works Step by Step

1. The client creates a receiver (the object that does the work).
2. The client creates command objects, each tied to the receiver.
3. The client gives those commands to an invoker (UI button, scheduler, or script).
4. When triggered, the invoker calls `execute()`.
5. The command delegates to the receiver with the stored context.

At runtime, the invoker never knows who performs the work or how.

## How the After Version Works Step by Step

Open [after/Main.java](after/Main.java) and follow the flow:

1. Create the receivers (backup, report, email services).
2. Create command objects with the data they need.
3. Queue commands in [after/JobScheduler.java](after/JobScheduler.java).
4. The scheduler runs the queue by calling `execute()`.

The queue stays generic even as new job types are added.

## Design Questions You Should Think Through

Use these prompts when evaluating a design:

1. Do I need to queue, schedule, or log requests?
2. Do I need undo or redo later?
3. Do I want to reuse the same action across different UI surfaces?
4. Do I want to record and replay a sequence of operations?

If most answers are yes, Command is a strong fit.

## Trade-offs and Engineering Reality

The Command pattern is not free. It adds classes and indirection.

You should use it when:

1. You have many actions that must be treated uniformly.
2. You need to queue, log, or undo operations.
3. You want to decouple request creation from request execution.

You can avoid it when:

1. There are only a few trivial actions.
2. You will never need undo, macros, or scheduling.
3. The added abstraction would add noise without benefit.

## What Changes Architecturally

Before:

1. UI or caller directly invokes business logic.
2. Each action is hard-coded inside the invoker.
3. Adding new behaviors requires editing the invoker.

After:

1. UI depends only on the command interface.
2. Each action is encapsulated and reusable.
3. New behaviors are added by introducing new commands.

This is a practical move toward the Open-Closed Principle.

## Complexity and Maintenance Perspective

### Time complexity

Executing a command is usually $O(1)$ for the invoker. The receiver work dominates runtime as before.

### Structural complexity

You introduce extra classes, but you gain flexibility, testability, and lower coupling.

## How to Run the Example

From the `Behavioral/Command` directory:

```bash
javac before/*.java
java before.Main

javac after/*.java
java after.Main
```

## Final Reflection

The Command pattern is about turning actions into first-class objects.

When you evaluate a design, ask one powerful question:

"If I need to queue, undo, or replay this action later, can I do that without rewriting the caller?"

Command helps you answer yes.
