# UML (Unified Modeling Language) - Complete Guide

## 📚 Table of Contents
1. [Introduction to UML](#introduction-to-uml)
2. [UML Diagram Types](#uml-diagram-types)
3. [Class Diagrams](#class-diagrams)
4. [Sequence Diagrams](#sequence-diagrams)
5. [Use Case Diagrams](#use-case-diagrams)
6. [Activity Diagrams](#activity-diagrams)
7. [State Machine Diagrams](#state-machine-diagrams)
8. [Component Diagrams](#component-diagrams)
9. [Deployment Diagrams](#deployment-diagrams)
10. [Object Diagrams](#object-diagrams)
11. [Package Diagrams](#package-diagrams)
12. [Common Relationships](#common-relationships)
13. [Best Practices](#best-practices)

---

## 📖 Introduction to UML

**UML (Unified Modeling Language)** is a standardized visual modeling language used to specify, visualize, construct, and document software systems.

### Purpose of UML:
- **Visualize** system architecture and design
- **Specify** system structure and behavior
- **Document** design decisions
- **Communicate** between team members
- **Blueprint** for implementation

### UML History:
- Developed in the 1990s by Grady Booch, Ivar Jacobson, and James Rumbaugh
- Standardized by the Object Management Group (OMG)
- Current version: UML 2.5.1 (as of 2017)

---

## 🗂️ UML Diagram Types

UML diagrams are divided into two main categories:

### **Structural Diagrams** (Static View)
Show the static structure of the system:
1. **Class Diagram** - Classes, attributes, methods, relationships
2. **Object Diagram** - Object instances at a specific moment
3. **Component Diagram** - System components and dependencies
4. **Deployment Diagram** - Physical deployment of artifacts
5. **Package Diagram** - Package organization and dependencies
6. **Composite Structure Diagram** - Internal structure of classes

### **Behavioral Diagrams** (Dynamic View)
Show the dynamic behavior of the system:
1. **Use Case Diagram** - User interactions with the system
2. **Sequence Diagram** - Time-ordered interaction between objects
3. **Activity Diagram** - Workflow and business processes
4. **State Machine Diagram** - Object state transitions
5. **Communication Diagram** - Object interactions with emphasis on relationships
6. **Timing Diagram** - Time constraints on behavior
7. **Interaction Overview Diagram** - Combination of activity and sequence diagrams

---

## 📦 Class Diagrams

Class diagrams are the most commonly used UML diagrams, showing classes and their relationships.

### Class Notation

```
┌─────────────────────┐
│   ClassName         │  ← Class name (bold, centered)
├─────────────────────┤
│ - attribute1: Type  │  ← Attributes (fields)
│ # attribute2: Type  │
│ + attribute3: Type  │
├─────────────────────┤
│ + method1(): Type   │  ← Methods (operations)
│ - method2(): void   │
│ # method3(): Type   │
└─────────────────────┘
```

### Visibility Modifiers

| Symbol | Visibility | Meaning |
|--------|-----------|---------|
| `+` | Public | Accessible from anywhere |
| `-` | Private | Accessible only within the class |
| `#` | Protected | Accessible within class and subclasses |
| `~` | Package | Accessible within the same package |

### Class Types

#### 1. **Regular Class**
```
┌─────────────────────┐
│     ClassName       │
├─────────────────────┤
│ - field: Type       │
├─────────────────────┤
│ + method(): Type    │
└─────────────────────┘
```

#### 2. **Abstract Class**
```
┌─────────────────────┐
│  «abstract»         │  ← Stereotype
│   ClassName         │  (Name in italics)
├─────────────────────┤
│ - field: Type       │
├─────────────────────┤
│ + method(): Type    │
│ + abstract(): Type  │  (Abstract methods in italics)
└─────────────────────┘
```

#### 3. **Interface**
```
┌─────────────────────┐
│  «interface»        │  ← Stereotype
│   InterfaceName     │  (Name in italics)
├─────────────────────┤
│ + method1(): Type   │  (All methods public by default)
│ + method2(): Type   │
└─────────────────────┘
```

#### 4. **Enum**
```
┌─────────────────────┐
│     «enum»          │
│    EnumName         │
├─────────────────────┤
│ VALUE1              │
│ VALUE2              │
│ VALUE3              │
└─────────────────────┘
```

### Relationships in Class Diagrams

#### 1. **Association** (Has-a relationship)
```
ClassA ────────> ClassB
       navigability
```
- Solid line connecting two classes
- Arrow shows navigation direction
- Represents a structural relationship

**Example:**
```
Customer ────────> Order
         places
```

#### 2. **Bidirectional Association**
```
ClassA ───────── ClassB
```
- No arrows, or arrows on both ends
- Both classes know about each other

#### 3. **Aggregation** (Weak "has-a")
```
Whole ◇────── Part
      (empty diamond)
```
- Empty diamond on the "whole" side
- Part can exist independently of the whole
- Shared ownership

**Example:**
```
Department ◇────── Employee
           has
```
(Employee can exist without Department)

#### 4. **Composition** (Strong "has-a")
```
Whole ◆────── Part
      (filled diamond)
```
- Filled diamond on the "whole" side
- Part cannot exist independently of the whole
- Exclusive ownership
- Lifecycle dependency

**Example:**
```
House ◆────── Room
      contains
```
(Room cannot exist without House)

#### 5. **Inheritance/Generalization** (Is-a relationship)
```
Subclass ───────▷ Superclass
         (empty triangle)
```
- Solid line with empty triangle pointing to parent
- Represents inheritance

**Example:**
```
Dog ───────▷ Animal
    is-a
```

#### 6. **Realization/Implementation**
```
ConcreteClass ········▷ Interface
              (dashed line with empty triangle)
```
- Dashed line with empty triangle
- Class implements interface

**Example:**
```
ArrayList ········▷ List
          implements
```

#### 7. **Dependency**
```
ClassA ········> ClassB
       (dashed arrow)
```
- Dashed arrow
- Weak relationship (uses, imports, parameters)
- One class depends on another

**Example:**
```
OrderService ········> EmailService
             uses
```

### Multiplicity (Cardinality)

Indicates how many instances can be associated:

| Notation | Meaning |
|----------|---------|
| `1` | Exactly one |
| `0..1` | Zero or one |
| `*` or `0..*` | Zero or more |
| `1..*` | One or more |
| `n` | Exactly n (where n > 1) |
| `n..m` | Between n and m |

**Example:**
```
Customer 1 ────── 0..* Order
         │              │
         places      placed by
```

### Attributes and Methods Details

#### Attribute Syntax:
```
visibility name: type [multiplicity] = defaultValue {property}
```

**Examples:**
```
- name: String
+ age: int = 0
# emails: String[*]
+ ID: int {readOnly}
```

#### Method Syntax:
```
visibility name(parameter: type): returnType
```

**Examples:**
```
+ getName(): String
- calculateTotal(items: List): double
# validate(data: Object): boolean
```

### Stereotypes

Stereotypes extend UML vocabulary:

Common stereotypes:
- `«interface»` - Interface
- `«abstract»` - Abstract class
- `«enumeration»` - Enumeration
- `«entity»` - Entity class
- `«boundary»` - Boundary class
- `«control»` - Control class
- `«utility»` - Utility class
- `«exception»` - Exception class

---

## 🔄 Sequence Diagrams

Sequence diagrams show how objects interact in a time sequence.

### Basic Elements

#### 1. **Actor**
```
  ┌─┐
  │ │  ← Stick figure (external entity)
  └─┘
```

#### 2. **Object/Participant**
```
┌──────────────┐
│ objectName   │  ← Object (box at top)
│  :ClassName  │
└──────────────┘
      │
      │  ← Lifeline (dashed vertical line)
      │
```

#### 3. **Activation Bar**
```
      │
      ▐  ← Thin rectangle on lifeline
      ▐    (shows when object is active)
      │
```

#### 4. **Messages**

**Synchronous Call** (waits for response):
```
──────────────────>
    method()
```

**Asynchronous Call** (doesn't wait):
```
─────────────────->
    method()
     (open arrow)
```

**Return Message**:
```
<- - - - - - - - -
    return value
   (dashed line)
```

**Self-Call**:
```
      │
      ▐──┐
      ▐  │ method()
      ▐<─┘
      │
```

#### 5. **Creation**
```
────────────────────> ┌──────────┐
  «create»            │ :Object  │
                      └──────────┘
```

#### 6. **Destruction**
```
      │
      ▐
      X  ← Large X (object destroyed)
```

### Fragments (Combined Fragments)

#### **alt** (Alternative - if/else)
```
┌─ alt ────────────────────────────┐
│ [condition1]                      │
│    ──────> message1               │
├───────────────────────────────────┤
│ [else]                            │
│    ──────> message2               │
└───────────────────────────────────┘
```

#### **opt** (Optional - if)
```
┌─ opt ────────────────────────────┐
│ [condition]                       │
│    ──────> message                │
└───────────────────────────────────┘
```

#### **loop** (Iteration)
```
┌─ loop ───────────────────────────┐
│ [for each item]                   │
│    ──────> processItem()          │
└───────────────────────────────────┘
```

#### **par** (Parallel)
```
┌─ par ────────────────────────────┐
│    ──────> message1               │
├───────────────────────────────────┤
│    ──────> message2               │
└───────────────────────────────────┘
```

#### **ref** (Reference to another diagram)
```
┌─ ref ────────────────────────────┐
│  OtherSequenceDiagram             │
└───────────────────────────────────┘
```

### Example Sequence Diagram

```
 User         WebApp         Database
  │             │               │
  │──login()───>│               │
  │             │──query()─────>│
  │             │<─────result───│
  │<───success──│               │
  │             │               │
```

---

## 👤 Use Case Diagrams

Use case diagrams show system functionality from a user's perspective.

### Elements

#### 1. **Actor**
```
  ┌─┐
  │ │  ← Stick figure
  └─┘
 User
```
Represents external entity (user, system, device)

#### 2. **Use Case**
```
  ╱────────────╲
 │  Use Case   │  ← Oval/Ellipse
  ╲────────────╱
```
Represents a system function

#### 3. **System Boundary**
```
┌──────────────────────────┐
│      System Name         │
│  ╱────────╲  ╱────────╲ │
│ │Use Case1││Use Case2│  │
│  ╲────────╱  ╲────────╱ │
└──────────────────────────┘
```
Rectangle containing use cases

### Relationships

#### **Association**
```
  ┌─┐
  │ │─────────╱────────╲
  └─┘        │Use Case │
 Actor        ╲────────╱
```
Solid line between actor and use case

#### **Include** (Required behavior)
```
  ╱────────╲         ╱────────╲
 │Use Case1│····>   │Use Case2│
  ╲────────╱ «include» ╲────────╱
```
Dashed arrow with «include» stereotype
Use Case1 always includes Use Case2

#### **Extend** (Optional behavior)
```
  ╱────────╲         ╱────────╲
 │Use Case2│<····   │Use Case1│
  ╲────────╱ «extend»  ╲────────╱
```
Dashed arrow with «extend» stereotype
Use Case1 may extend Use Case2

#### **Generalization**
```
  ╱────────╲
 │ General │
  ╲────────╱
      △
      │
  ╱────────╲
 │Specific │
  ╲────────╱
```
Inheritance between actors or use cases

---

## 🔀 Activity Diagrams

Activity diagrams show workflow and business processes.

### Elements

#### 1. **Initial Node** (Start)
```
  ●  ← Filled circle
```

#### 2. **Final Node** (End)
```
  ◉  ← Filled circle with outer circle
```

#### 3. **Activity/Action**
```
┌────────────────┐
│   Activity     │  ← Rounded rectangle
└────────────────┘
```

#### 4. **Control Flow**
```
─────────────────>  ← Arrow
```

#### 5. **Decision Node** (Branch)
```
      ◇  ← Diamond
     ╱│╲
    ╱ │ ╲
  [yes] [no]
```

#### 6. **Merge Node**
```
    ╲ │ ╱
     ╲│╱
      ◇  ← Diamond
```

#### 7. **Fork** (Start parallel activities)
```
  │
  │
  ▬▬▬  ← Thick horizontal bar
 ╱│╲
```

#### 8. **Join** (End parallel activities)
```
 ╲│╱
  ▬▬▬  ← Thick horizontal bar
  │
```

#### 9. **Swimlanes** (Partitions)
```
┌─────────┬─────────┬─────────┐
│ Actor1  │ Actor2  │ Actor3  │
├─────────┼─────────┼─────────┤
│         │         │         │
│  ┌───┐  │  ┌───┐  │         │
│  │Act│  │  │Act│  │         │
│  └───┘  │  └───┘  │         │
└─────────┴─────────┴─────────┘
```

#### 10. **Object Node**
```
┌────────────────┐
│  [ObjectState] │  ← Rectangle
└────────────────┘
```

#### 11. **Signal Send/Receive**
```
Send:    ──────────╲
                    ╲  ← Pentagon pointing right
                     ╲

Receive: ╱
        ╱  ← Pentagon pointing left
────────╱
```

### Example Activity Diagram

```
           ●  (Initial)
           │
           ▼
      ┌─────────┐
      │ Action1 │
      └─────────┘
           │
           ▼
          ◇  [condition]
         ╱ ╲
    [yes]   [no]
       ╱     ╲
      ▼       ▼
  ┌────┐   ┌────┐
  │Act2│   │Act3│
  └────┘   └────┘
      ╲     ╱
       ╲   ╱
        ▼ ▼
         ◇
         │
         ▼
         ◉  (Final)
```

---

## 🔄 State Machine Diagrams

State machine diagrams show states and transitions of an object.

### Elements

#### 1. **Initial State**
```
  ●  ← Filled circle
```

#### 2. **Final State**
```
  ◉  ← Filled circle with outer circle
```

#### 3. **State**
```
┌──────────────────┐
│   State Name     │  ← Rounded rectangle
├──────────────────┤
│ entry / action   │  (Optional: entry/exit actions)
│ do / activity    │
│ exit / action    │
└──────────────────┘
```

#### 4. **Composite State** (Contains substates)
```
┌────────────────────────────┐
│     Composite State        │
│  ┌──────┐      ┌──────┐   │
│  │State1│─────>│State2│   │
│  └──────┘      └──────┘   │
└────────────────────────────┘
```

#### 5. **Transition**
```
         event [guard] / action
State1 ─────────────────────────> State2
```
- **event**: Trigger for transition
- **[guard]**: Condition (optional)
- **/action**: Action on transition (optional)

#### 6. **Choice Pseudostate**
```
      ◇  ← Diamond
     ╱│╲
    ╱ │ ╲
  [yes] [no]
```

#### 7. **Fork/Join**
```
Fork:  ──▬──  (splits into concurrent states)
       ╱ ╲

Join:  ╲ ╱   (merges from concurrent states)
       ──▬──
```

#### 8. **History State**
```
   ⓗ  ← Circle with 'H'
```
Returns to the most recent substate

### Example State Machine

```
      ●
      │
      ▼
  ┌────────┐  submit   ┌──────────┐
  │  Draft │─────────> │ Submitted │
  └────────┘           └──────────┘
      │                     │
      │cancel          approve│
      ▼                     ▼
  ┌────────┐           ┌──────────┐
  │Canceled│           │ Approved │
  └────────┘           └──────────┘
      │                     │
      └──────────┬──────────┘
                 ▼
                 ◉
```

---

## 🧩 Component Diagrams

Component diagrams show physical components and their dependencies.

### Elements

#### 1. **Component**
```
┌─────────────────────┐
│  «component»        │
│  ┌──┐               │
│  │  │ ComponentName │
│  └──┘               │
└─────────────────────┘
```

Or simplified:
```
┌──┐
│  │ ComponentName
└──┘
```

#### 2. **Interface**

**Provided Interface** (lollipop):
```
Component ─────○  IInterface
```

**Required Interface** (socket):
```
Component ─────◐  IInterface
```

#### 3. **Port**
```
┌─────────────┐
│ Component   │□──  ← Port (small square)
│             │
└─────────────┘
```

#### 4. **Dependencies**
```
Component1 ········> Component2
           (dashed arrow)
```

---

## 🖥️ Deployment Diagrams

Deployment diagrams show physical deployment of artifacts on nodes.

### Elements

#### 1. **Node** (Physical device)
```
┌─────────────────────┐
│  «device»           │
│  ┌────────────┐     │
│  │ ServerName │     │  ← 3D box
│  │            │     │
│  └────────────┘     │
└─────────────────────┘
```

#### 2. **Artifact** (Code file, library, executable)
```
┌───────────────┐
│ «artifact»    │
│   file.jar    │
└───────────────┘
```

#### 3. **Execution Environment**
```
┌─────────────────────┐
│ «executionEnvironment» │
│   JVM               │
└─────────────────────┘
```

#### 4. **Communication Path**
```
Node1 ──────────── Node2
      «protocol»
```

#### 5. **Deployment**
```
Node ┌─────────────┐
     │  «deploy»   │
     │  Artifact   │
     └─────────────┘
```

---

## 📦 Object Diagrams

Object diagrams show instances of classes at a specific moment.

### Notation

**Object:**
```
┌─────────────────────┐
│ objectName:ClassName│  ← Underlined
├─────────────────────┤
│ attribute1 = value1 │  ← Concrete values
│ attribute2 = value2 │
└─────────────────────┘
```

**Anonymous Object:**
```
┌─────────────────────┐
│   :ClassName        │
├─────────────────────┤
│ attribute1 = value1 │
└─────────────────────┘
```

**Links** (instance of association):
```
Object1 ──────── Object2
```

---

## 📁 Package Diagrams

Package diagrams show package organization and dependencies.

### Elements

#### 1. **Package**
```
┌────────────┐
│ PackageName│  ← Tab on top
├────────────┴──────────┐
│                       │
│   Classes/Packages    │
│                       │
└───────────────────────┘
```

#### 2. **Package Dependencies**
```
Package1 ········> Package2
         «import»

Package1 ········> Package2
         «access»

Package1 ────────> Package2
         «merge»
```

---

## 🔗 Common Relationships Summary

| Relationship | Notation | Line | Arrow | Meaning |
|--------------|----------|------|-------|---------|
| **Association** | —— | Solid | Optional | Structural relationship |
| **Directed Association** | ──> | Solid | Yes | One-way navigation |
| **Aggregation** | ◇—— | Solid | Diamond (empty) | Weak ownership |
| **Composition** | ◆—— | Solid | Diamond (filled) | Strong ownership |
| **Inheritance** | ──▷ | Solid | Triangle (empty) | Is-a relationship |
| **Realization** | ··▷ | Dashed | Triangle (empty) | Implements interface |
| **Dependency** | ··> | Dashed | Arrow | Uses relationship |

---

## ✅ Best Practices

### General Rules

1. **Keep It Simple**
   - Don't include every detail
   - Focus on what's important for current audience
   - Use appropriate level of abstraction

2. **Be Consistent**
   - Use consistent naming conventions
   - Apply same notation throughout
   - Follow team/organization standards

3. **Use Meaningful Names**
   - Clear, descriptive class names
   - Verb-based method names
   - Noun-based attribute names

4. **Show Only Relevant Details**
   - Hide implementation details when appropriate
   - Show what's necessary for understanding
   - Use packages to organize large models

5. **Avoid Crossing Lines**
   - Minimize line crossings for readability
   - Use proper layout and spacing
   - Consider using multiple diagrams

6. **Add Notes When Needed**
   ```
   ┌─────────────┐
   │   Note      │
   ├─────────────┤
   │ Explanation │
   └─────────────┘
         │
         │ (dashed line to element)
         ▼
   ```

### Class Diagram Best Practices

1. **Start with key classes** - Focus on core domain objects
2. **Show only public interface** - Hide private details in overview diagrams
3. **Use packages** - Group related classes
4. **Indicate multiplicity** - Always show cardinality on associations
5. **Name associations** - Use clear relationship names
6. **Place parent above child** - In inheritance hierarchies

### Sequence Diagram Best Practices

1. **Order actors logically** - Important actors on left
2. **Show time progression** - Top to bottom
3. **Use fragments sparingly** - Too many make diagram complex
4. **Keep focused** - One scenario per diagram
5. **Show return messages** - Make call-response clear

### Activity Diagram Best Practices

1. **Use swimlanes** - Show responsibility clearly
2. **Start and end clearly** - Always include initial and final nodes
3. **Name activities well** - Use action verbs
4. **Avoid too many branches** - Consider separate diagrams
5. **Show parallel activities** - Use fork/join when appropriate

### State Machine Best Practices

1. **Name states clearly** - Use descriptive names
2. **Show all transitions** - Include guards and actions
3. **Identify initial state** - Make entry point obvious
4. **Use composite states** - For complex state machines
5. **Document events** - Make triggers clear

---

## 📐 Drawing Tools

### Popular UML Tools:
- **PlantUML** - Text-based, version control friendly
- **Lucidchart** - Web-based, collaborative
- **draw.io (diagrams.net)** - Free, web/desktop
- **Enterprise Architect** - Professional, comprehensive
- **Visual Paradigm** - Professional, full-featured
- **StarUML** - Desktop, free/commercial
- **Mermaid** - Markdown-based, GitHub-friendly
- **Microsoft Visio** - Professional diagramming

---

## 🎯 Quick Reference Card

### Must-Know Symbols

```
Classes:
┌────────┐  Regular class
│ Class  │
└────────┘

Relationships:
──────>   Association (directed)
◇─────    Aggregation (has-a, weak)
◆─────    Composition (has-a, strong)
───▷      Inheritance (is-a)
··▷       Realization (implements)
··>       Dependency (uses)

Multiplicity:
1         Exactly one
0..1      Zero or one
*         Zero or more
1..*      One or more

Visibility:
+         Public
-         Private
#         Protected
~         Package

Stereotypes:
«interface»
«abstract»
«enum»
```

---

## 📚 Further Learning

### Resources:
- **UML Specification** - Official OMG documentation
- **"UML Distilled" by Martin Fowler** - Essential guide
- **"Applying UML and Patterns" by Craig Larman** - Comprehensive
- **PlantUML Documentation** - For practical diagram creation

### Practice Tips:
1. Start with simple examples
2. Reverse-engineer existing code into UML
3. Design before coding using UML
4. Review and critique others' diagrams
5. Use UML in team discussions

---

## 💡 Remember

> "The purpose of modeling is not to create perfect diagrams, but to understand and communicate the system design effectively."

- UML is a **tool**, not a goal
- Use the **right diagram** for the problem
- **Simplicity** beats completeness
- **Communicate** rather than document everything
- **Iterate** and refine your diagrams

---

**Happy Modeling! 📊**
