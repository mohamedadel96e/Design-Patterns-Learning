# Visitor Design Pattern

## Let's Think About This Problem Together...

Imagine you're building a document processing system. You have different types of document elements: paragraphs, images, tables, headings, etc. Now, users want various operations on these documents...

### The Initial Scenario

**Product Manager:** "We need to export documents to PDF!"

**Us:** "Sure, we'll add an `exportToPDF()` method to each element."

**Product Manager:** "Great! Oh, and we also need HTML export."

**Us:** "Okay... we'll add `exportToHTML()` to each element too."

**Product Manager:** "And word count functionality."

**Us:** "Another method to all elements..."

**Product Manager:** "And spell checking. And printing. And statistics. And..."

**Us:** "Wait... this is getting out of hand. Let's think about this differently."

---

## The Growing Problem

Let's visualize what happens with the naive approach:

```java
class Paragraph {
    public void exportToPDF() { ... }
    public void exportToHTML() { ... }
    public void exportToMarkdown() { ... }
    public int countWords() { ... }
    public void spellCheck() { ... }
    public void print() { ... }
    public Statistics getStatistics() { ... }
    // 10, 20, 30 more operations?
}

class Image {
    public void exportToPDF() { ... }
    public void exportToHTML() { ... }
    public void exportToMarkdown() { ... }
    public int countWords() { ... }  // Images don't have words!
    public void spellCheck() { ... }   // Can't spell check images!
    public void print() { ... }
    public Statistics getStatistics() { ... }
    // Same 30 operations, many don't make sense...
}
```

### What's Wrong Here?

Let's brainstorm the problems...

---

## Brainstorming Session: Problems with the Naive Approach

### Problem 1: Violates Single Responsibility Principle

**The Issue:** Each element class now has multiple responsibilities:
- Representing its own data (content, formatting, etc.)
- Knowing how to export to PDF
- Knowing how to export to HTML
- Knowing how to count words
- Knowing how to spell check
- ... and so on

**Why it's bad:**
- Element classes become bloated with 20, 30, 40+ methods
- Hard to understand what the class actually represents
- Mixing data representation with various operations

### Problem 2: Violates Open/Closed Principle

**The Issue:** Every time we add a new operation, we must modify ALL element classes.

**Scenario:**
- Product Manager: "We need JSON export now!"
- Us: Have to open and modify Paragraph, Image, Table, Heading, List, etc.
- If we have 15 element types, we modify 15 files
- Each modification is a chance to introduce bugs

**Why it's bad:**
- Can't add new operations without modifying existing, working code
- Changes ripple through the entire system
- Higher risk of breaking existing functionality
- Violates "closed for modification" principle

### Problem 3: Operations That Don't Make Sense

**The Issue:** Not all operations apply to all elements.

**Examples:**
- `countWords()` on an Image? Returns 0, but method still exists
- `spellCheck()` on an Image? Does nothing, but clutters the interface
- `compress()` on a Paragraph? Doesn't make sense

**Why it's bad:**
- Cluttered interfaces with irrelevant methods
- Confusing for developers using the API
- Easy to call methods that don't do anything meaningful
- Violates Interface Segregation Principle

### Problem 4: Difficult to Add New Element Types

**The Issue:** If we add a new element type, we need to implement ALL operations.

**Scenario:**
- We want to add a new VideoElement
- Must implement exportToPDF(), exportToHTML(), countWords(), spellCheck(), etc.
- Even if some don't make sense for video
- Can't compile until all 30 methods are implemented

**Why it's bad:**
- High barrier to extending the system
- Forces implementation of irrelevant methods
- Couples new elements to all existing operations

### Problem 5: Scattered Operation Logic

**The Issue:** The logic for one operation is spread across many classes.

**Example - PDF Export:**
```
Paragraph.exportToPDF()     <- PDF logic here
Image.exportToPDF()         <- More PDF logic here
Table.exportToPDF()         <- Even more PDF logic here
Heading.exportToPDF()       <- Still more PDF logic here
```

**Why it's bad:**
- To understand PDF export, must read 15 different files
- To change PDF format, must modify 15 different places
- Hard to maintain consistency
- Difficult to test PDF export as a whole feature

### Problem 6: Team Organization Issues

**The Real-world Impact:**

Imagine your team structure:
- Sarah specializes in PDF export
- John specializes in HTML export
- Maria specializes in statistics

**With the current approach:**
- Sarah needs to modify Paragraph, Image, Table, etc. for PDF
- John needs to modify the same files for HTML
- Maria needs to modify the same files for statistics
- Constant merge conflicts!
- Hard to work in parallel
- Reviewing changes is a nightmare

---

## The "Aha!" Moment

Let's step back and think about what we REALLY want...

### Key Insight 1: Stability vs. Volatility

**Think about it:**
- How often do we add new element types? (Rarely - the document structure is stable)
- How often do we add new operations? (Frequently - new export formats, new analysis tools)

**Realization:** Our current design optimizes for adding elements, but operations change more often!

### Key Insight 2: Separation of Concerns

**What if we could:**
- Keep element classes focused on representing data
- Put each operation in its own class
- Add new operations without touching element classes

**This would solve:**
- Single Responsibility - elements represent data, operations do operations
- Open/Closed - add operations without modifying elements
- Team organization - each developer works on their own operation class

### Key Insight 3: The Double Dispatch Problem

**But wait, there's a catch...**

How does an operation know which element type it's working with?

**Basic polymorphism won't work:**
```java
Operation op = new PDFExport();
Element elem = getElement();  // Could be Paragraph, Image, etc.
op.process(elem);  // op doesn't know specific type of elem!
```

**The challenge:**
- Operation needs to perform different logic for each element type
- But we want operations separate from elements
- We need a way for the operation to "visit" each element type appropriately

**This is where the Visitor pattern comes in!**

---

## The Visitor Pattern Solution

The Visitor pattern uses a clever technique called "double dispatch" to solve this.

### The Big Picture

**Two hierarchies that work together:**

1. **Element Hierarchy** (stable - rarely changes)
   - Paragraph, Image, Table, Heading, etc.
   - Each element can "accept" a visitor

2. **Visitor Hierarchy** (volatile - changes often)
   - PDFExportVisitor, HTMLExportVisitor, WordCountVisitor, etc.
   - Each visitor can "visit" different element types

### How It Works

**The Dance:**

1. You create a visitor (e.g., PDFExportVisitor)
2. You pass it to an element (e.g., paragraph.accept(visitor))
3. The element calls back to the visitor with itself (visitor.visit(this))
4. The visitor now knows the specific element type and performs the appropriate action

**The Magic:**

```java
// In Paragraph class (element)
public void accept(Visitor visitor) {
    visitor.visit(this);  // 'this' is known to be Paragraph
}

// In PDFExportVisitor class
public void visit(Paragraph paragraph) {
    // I know I'm dealing with a Paragraph!
    // Export paragraph to PDF
}

public void visit(Image image) {
    // I know I'm dealing with an Image!
    // Export image to PDF
}
```

### Why This Works

**Double Dispatch Explained:**

1. **First dispatch:** paragraph.accept(visitor)
   - Java dispatches to Paragraph's accept method based on runtime type

2. **Second dispatch:** visitor.visit(this)
   - Java dispatches to visit(Paragraph) because 'this' is statically typed as Paragraph

**Result:** The visitor method is chosen based on BOTH the visitor type AND the element type!

---

## Structure of the Visitor Pattern

### The Four Key Players

#### 1. Visitor Interface
*"I define what operations can be performed"*
```
- visit(Paragraph)
- visit(Image)
- visit(Table)
- ... one method per element type
```

#### 2. Concrete Visitors
*"I implement specific operations"*
```
PDFExportVisitor:
  - visit(Paragraph) -> export paragraph to PDF
  - visit(Image) -> export image to PDF

HTMLExportVisitor:
  - visit(Paragraph) -> export paragraph to HTML
  - visit(Image) -> export image to HTML
```

#### 3. Element Interface
*"I can accept visitors"*
```
- accept(Visitor)
```

#### 4. Concrete Elements
*"I accept visitors and let them visit me"*
```
Paragraph:
  - accept(Visitor) -> visitor.visit(this)

Image:
  - accept(Visitor) -> visitor.visit(this)
```

---

## Benefits of the Visitor Pattern

### Benefit 1: Easy to Add New Operations

**Before:**
- Add new operation: Modify ALL element classes
- 15 element types = 15 files to change

**After:**
- Add new operation: Create ONE new visitor class
- Element classes remain unchanged
- Follows Open/Closed Principle

### Benefit 2: Operation Logic is Centralized

**Before:**
- PDF logic scattered across 15 element classes
- To modify PDF export: hunt through 15 files

**After:**
- PDF logic in ONE class: PDFExportVisitor
- To modify PDF export: edit one file
- Easy to understand, maintain, and test

### Benefit 3: Single Responsibility Respected

**Before:**
- Paragraph knows about data AND PDF AND HTML AND counting AND...

**After:**
- Paragraph knows about paragraph data only
- PDFExportVisitor knows about PDF export only
- Each class has one clear responsibility

### Benefit 4: Easier Team Collaboration

**Before:**
- Multiple developers modifying same element classes
- Constant merge conflicts

**After:**
- Sarah works on PDFExportVisitor
- John works on HTMLExportVisitor
- Maria works on StatisticsVisitor
- No conflicts! Parallel development!

### Benefit 5: Type-Safe Operation Dispatch

**Alternative approaches (and their problems):**
- instanceof checks: Not type-safe, easy to forget a type
- String type codes: Error-prone, no compile-time checking
- Visitor pattern: Compile-time type checking, can't forget a type

---

## Trade-offs and Considerations

### When Visitor Works Well

GOOD fit when:
- You have a stable element structure (types rarely change)
- You frequently add new operations
- Operations need to work with multiple element types
- You want to keep operations separate from elements
- Type safety is important

### When Visitor Becomes Problematic

WATCH OUT when:
- Element types change frequently
  - Every new element type requires updating ALL visitors
  - Can be a lot of work if you have many visitors

- Elements have complex internal state
  - Visitor needs access to element internals
  - May require exposing more of the element's API
  - Can break encapsulation if not careful

- Simple operations
  - For trivial operations, visitor might be overkill
  - Sometimes a simple method is better

### The Fundamental Trade-off

**Visitor Pattern makes:**
- Adding new operations: EASY (just add a new visitor)
- Adding new element types: HARD (must update all visitors)

**Traditional approach makes:**
- Adding new operations: HARD (modify all elements)
- Adding new element types: EASY (just implement all methods)

**Choose based on what changes more often in your system!**

---

## Common Variations and Extensions

### Variation 1: Returning Values

Visitors can return values from visit methods:

```java
interface Visitor<T> {
    T visit(Paragraph p);
    T visit(Image i);
}

class WordCountVisitor implements Visitor<Integer> {
    public Integer visit(Paragraph p) {
        return p.getText().split("\\s+").length;
    }
    public Integer visit(Image i) {
        return 0;
    }
}
```

### Variation 2: Parameterized Visitors

Visitors can accept additional parameters:

```java
class ExportVisitor {
    private OutputStream output;
    
    public ExportVisitor(OutputStream output) {
        this.output = output;
    }
    
    public void visit(Paragraph p) {
        output.write(p.getText().getBytes());
    }
}
```

### Variation 3: Stateful Visitors

Visitors can maintain state across visits:

```java
class StatisticsVisitor {
    private int totalWords = 0;
    private int totalImages = 0;
    
    public void visit(Paragraph p) {
        totalWords += countWords(p);
    }
    
    public void visit(Image i) {
        totalImages++;
    }
    
    public Report getReport() {
        return new Report(totalWords, totalImages);
    }
}
```

### Variation 4: Composite with Visitor

Often used with Composite pattern for tree structures:

```java
class Document implements Element {
    private List<Element> elements;
    
    public void accept(Visitor v) {
        v.visit(this);
        for (Element e : elements) {
            e.accept(v);  // Visit all children
        }
    }
}
```

---

## Comparing Approaches: A Real Example

### Scenario: Adding 3 Operations to 5 Element Types

**Without Visitor:**
- Create 3 operations
- Modify 5 element classes
- Total changes: 15 method additions across 5 files
- Risk: Breaking existing element functionality

**With Visitor:**
- Create 3 visitor classes
- Element classes unchanged
- Total changes: 3 new files with 5 methods each
- Risk: Isolated to new visitors, existing code untouched

### Scenario: Adding 1 New Element Type

**Without Visitor:**
- Create 1 new element class
- Implement existing operations naturally
- Total changes: 1 new file

**With Visitor:**
- Create 1 new element class
- Add accept() method
- Update ALL existing visitors (if you have 10 visitors, update 10 files)
- Total changes: 11 files (1 new + 10 modified)

**This shows the trade-off clearly!**

---

## Real-World Applications

### Use Case 1: Compiler Design

**Problem:** AST (Abstract Syntax Tree) with different node types
**Operations:** Code generation, optimization, type checking, pretty printing
**Why Visitor:** Node types stable, operations change/added frequently

### Use Case 2: Document Processing

**Problem:** Document elements (text, images, tables, charts)
**Operations:** Export (PDF, HTML, Markdown), analysis, validation
**Why Visitor:** Document structure stable, new export formats added

### Use Case 3: Graphics/Scene Graphs

**Problem:** Shape hierarchy (circles, rectangles, polygons)
**Operations:** Rendering, hit testing, bounding box calculation, serialization
**Why Visitor:** Shape types stable, new operations for different renderers

### Use Case 4: Business Rule Processing

**Problem:** Different transaction types in a system
**Operations:** Validation, calculation, auditing, reporting
**Why Visitor:** Transaction types stable, business rules change often

---

## Implementation Considerations

### Challenge 1: The Accept Method Boilerplate

Every element needs nearly identical accept() method. Solutions:
- Abstract base class with default implementation
- Code generation for accept methods
- Accept it as necessary boilerplate (it's simple and consistent)

### Challenge 2: Default Visitor Behavior

Not all visitors need to handle all types. Solutions:
- Create an abstract base visitor with default no-op implementations
- Create specialized visitor interfaces for subset of elements
- Document which methods visitors must implement

### Challenge 3: Visitor Interface Evolution

When adding element types, must update visitor interface. Solutions:
- Use default methods (Java 8+) for backwards compatibility
- Create versioned visitor interfaces
- Use adapter pattern for old visitors with new elements

### Challenge 4: Circular Dependencies

Elements depend on Visitor interface, visitors depend on element interfaces. Solutions:
- Careful package organization
- Accept the coupling as intentional (it's the pattern's nature)
- Use interfaces to minimize concrete dependencies

---

## Anti-patterns and Mistakes to Avoid

### Mistake 1: Using Visitor When Element Structure Changes Often

**Problem:** Every new element type breaks all visitors
**Better:** Just add methods to elements if types are volatile

### Mistake 2: Visitors with Side Effects on Elements

**Problem:** Visitor modifies element state, breaks encapsulation
**Better:** Keep visitors read-only, or have explicit mutation methods

### Mistake 3: God Visitors

**Problem:** One visitor doing too many unrelated things
**Better:** One visitor per operation/concern

### Mistake 4: Not Using Type Safety

**Problem:** Casting element types instead of proper dispatch
**Better:** Trust the double dispatch, avoid casts

---

## Testing Strategy

### Testing Elements

Simple - they should just accept visitors:
```java
@Test
public void testParagraphAcceptsVisitor() {
    Paragraph p = new Paragraph("text");
    MockVisitor visitor = new MockVisitor();
    p.accept(visitor);
    assertTrue(visitor.visitedParagraph);
}
```

### Testing Visitors

Test each visit method independently:
```java
@Test
public void testPDFExportVisitsParagraph() {
    PDFExportVisitor visitor = new PDFExportVisitor();
    Paragraph p = new Paragraph("Hello World");
    visitor.visit(p);
    // Assert PDF output contains "Hello World"
}
```

### Integration Testing

Test visitor traversing multiple elements:
```java
@Test
public void testWordCountAcrossDocument() {
    Document doc = createTestDocument();
    WordCountVisitor visitor = new WordCountVisitor();
    doc.accept(visitor);
    assertEquals(100, visitor.getTotalWords());
}
```

---

## Summary

### The Visitor Pattern in a Nutshell

**What it does:**
- Separates operations from the objects they operate on
- Lets you add new operations without modifying existing classes
- Uses double dispatch for type-safe operation selection

**When to use it:**
- Stable object structure, volatile operations
- Multiple distinct operations needed on objects
- Want to keep operations organized separately
- Need type-safe operation dispatch

**Trade-offs:**
- Adding operations: EASY
- Adding element types: HARD
- Some complexity in understanding double dispatch
- Slightly more code initially (interfaces, accept methods)

### The Key Principles

1. **Separation of Concerns:** Operations live in visitors, data lives in elements
2. **Open/Closed Principle:** Add operations without modifying elements
3. **Single Responsibility:** Each visitor does one thing well
4. **Type Safety:** Compile-time checking of operation-element combinations

---

## Code Examples

Check out:
- **`before/`** - Shows problems with traditional approach (operations in elements)
- **`after/`** - Clean implementation using Visitor pattern

Run them, compare them, modify them. See how easy it is to add a new operation with the Visitor pattern versus the traditional approach. That's where the pattern really shines!

---

## Final Thoughts

The Visitor pattern is powerful but has a learning curve. The double dispatch mechanism isn't intuitive at first. But once you understand it, you'll recognize many situations where it's the perfect fit.

Ask yourself: "What changes more often - my object types or the operations I perform on them?"

If operations change more often, Visitor is your friend.
