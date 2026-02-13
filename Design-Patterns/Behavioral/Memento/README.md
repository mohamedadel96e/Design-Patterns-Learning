# Memento Design Pattern 🧠💾

## Let's Think About This Together...

Imagine you're building a text editor. Seems simple enough, right? Let's brainstorm what features users expect...

### The Problem Space 🤔

**User:** "I need an undo feature in my text editor!"

**Us:** "Sure! That sounds straightforward."

**User:** "And redo too!"

**Us:** "Okay, so we need to track changes somehow..."

**User:** "Oh, and I should be able to undo multiple times, not just once."

**Us:** "Hmm, this is getting interesting. Let's think about how to solve this..."

---

## 🎯 The Real Challenge

Let's break down what we actually need:

1. **Capture the state** of our text editor at different points in time
2. **Store multiple states** (for multiple undos)
3. **Restore previous states** when the user hits undo
4. **Move forward again** when the user hits redo

Sounds manageable! But wait... let's think deeper 🤯

---

## 💭 First Brainstorming Session: Naive Solutions

### Idea #1: "Just store copies of the entire object!"

```java
class TextEditor {
    private String content;
    private List<TextEditor> history = new ArrayList<>();
    
    public void save() {
        history.add(this); // Store a copy
    }
}
```

**Problem:** Wait... are we storing a reference or a copy? If we store `this`, we're just storing references to the same object! Every item in our history would point to the current state. That won't work. ❌

**Fix Attempt:** Okay, let's clone the entire object then!

```java
public void save() {
    TextEditor copy = new TextEditor();
    copy.content = this.content;
    // copy all other fields...
    history.add(copy);
}
```

**New Problems:**
- What if TextEditor has 50 fields? We need to copy all of them!
- What if some fields are complex objects themselves?
- This violates encapsulation - we're exposing internal implementation
- The TextEditor needs to know how to copy itself (extra responsibility)
- Memory intensive - storing entire objects for every change

This is getting messy... 🤷‍♂️

---

### Idea #2: "Let's just store the differences!"

```java
class TextEditor {
    private String content;
    private List<String> changes = new ArrayList<>();
    
    public void typeText(String text) {
        changes.add("ADD:" + text);
        content += text;
    }
}
```

**Sounds smart, right?** We're only storing what changed!

**But think about these scenarios:**
- What if the user replaces all text? We'd need to store the entire document anyway
- How do we handle complex operations? (Cut, paste, formatting changes, etc.)
- Restoring state means replaying all changes from the beginning - slow for 1000 undos!
- What about redo? We need to store forward changes too
- The logic gets extremely complex, extremely fast

Hmm, this isn't as elegant as we thought... 😓

---

### Idea #3: "What if we expose all fields and let someone else manage history?"

```java
class TextEditor {
    public String content;  // Made public
    public int cursorPosition;
    public String selectedText;
    // ... more fields
}

class HistoryManager {
    private List<TextEditorState> history = new ArrayList<>();
    
    public void save(TextEditor editor) {
        TextEditorState state = new TextEditorState();
        state.content = editor.content;
        state.cursorPosition = editor.cursorPosition;
        // ... copy all fields
        history.add(state);
    }
}
```

**Problems with this approach:**
- 🚨 **ENCAPSULATION VIOLATION!** We're exposing all internal state
- The HistoryManager needs to know about TextEditor's internal structure
- Every time we change TextEditor's internals, we must update HistoryManager
- Other classes can now modify TextEditor's state directly
- This is a maintenance nightmare!

We're breaking fundamental OOP principles here... ⚠️

---

## 🌟 The "Aha!" Moment

Let's step back and think about what we **really** need:

### The Core Requirements:
1. ✅ **Capture state** without exposing internal structure
2. ✅ **Store state** externally (not in the object itself)
3. ✅ **Restore state** later
4. ✅ **Maintain encapsulation** - nobody should peek inside our saved state
5. ✅ **Keep it flexible** - easy to add more fields to save later

### The Key Insight 💡

What if we create a **special object whose ONLY job is to hold a snapshot of state**?
- The TextEditor creates it (so it can access its own private fields)
- It's **opaque** to everyone else (they can hold it, but not peek inside)
- Only the TextEditor can extract data from it

This is the **Memento Pattern**! 🎉

---

## 🏗️ The Memento Pattern Structure

Think of it like a **time capsule**:
- **Originator** (TextEditor) puts items in the capsule
- **Memento** (the capsule itself) - sealed, no peeking!
- **Caretaker** (HistoryManager) - holds capsules but can't open them

### The Three Key Players:

#### 1. The Originator (TextEditor)
*"I create snapshots of myself and can restore from them"*
```
- Has internal state
- Creates Mementos containing its state
- Restores its own state from Mementos
```

#### 2. The Memento (TextEditorMemento)
*"I'm a sealed snapshot of state at a point in time"*
```
- Stores the Originator's internal state
- Protected from access by other objects
- No business logic, just data storage
```

#### 3. The Caretaker (HistoryManager)
*"I manage when to save and restore, but don't touch the data"*
```
- Knows WHEN to save/restore
- Stores Mementos in history
- Never examines or modifies Memento contents
```

---

## 🤝 How They Work Together

```
User types text → TextEditor changes
         ↓
TextEditor creates Memento (snapshot)
         ↓
Caretaker stores Memento in history
         ↓
User hits "Undo"
         ↓
Caretaker retrieves Memento from history
         ↓
TextEditor restores its state from Memento
         ↓
Done! ✨
```

---

## 💻 Implementation Strategy

### Before Pattern (the problems we discussed):
- Direct exposure of state
- Tight coupling
- Violation of Single Responsibility
- Hard to maintain

### After Pattern (clean solution):
- **Encapsulation preserved** - state stays private
- **Single Responsibility** - each class has one job
- **Flexible** - easy to add more state to save
- **Maintainable** - changes don't ripple through the system

---

## 🎓 Key Takeaways

### When Should You Use Memento?

✅ **Good fits:**
- Undo/Redo functionality
- Transaction rollback
- Game save states
- Document versioning
- Form state preservation
- Snapshot-based debugging

❌ **Watch out for:**
- **Memory usage** - storing many states can be expensive
  - *Solution:* Limit history size, use compression, or delta encoding
- **Complex state** - if objects have circular references
  - *Solution:* Careful memento design, possibly serialize only what's needed
- **Frequent changes** - creating mementos on every keystroke?
  - *Solution:* Batch changes, save periodically, or use Command pattern alongside

### The Memento Pattern Strengths:
- 🛡️ **Preserves encapsulation** - no need to expose internal structure
- 🔄 **Easy undo/redo** - just swap mementos
- 🎯 **Single Responsibility** - each class has a clear purpose
- 📦 **Decoupled** - Caretaker doesn't depend on Originator's internals

### The Memento Pattern Considerations:
- 💾 **Memory overhead** - each memento consumes memory
- 🐌 **Performance** - creating/restoring can be expensive for large states
- 🔧 **Maintenance** - need to update memento when internal state changes

---

## 🔄 Comparing Before and After

### Architecture Comparison:

**Before:**
```
TextEditor ←→ HistoryManager
   ↓                ↓
Public fields   Tightly coupled
Breaking        High maintenance
encapsulation   cost
```

**After:**
```
TextEditor → Memento → HistoryManager
     ↓           ↓            ↓
  Private    Opaque     Manages timing
  state      snapshot   only
```

---

## 🚀 Extensions and Variations

### Things to Think About:

1. **Incremental Mementos**: Instead of storing full state, store only what changed
   - Pros: Less memory
   - Cons: More complex, slower restoration

2. **Memento Compression**: Serialize and compress large states
   - Pros: Saves memory
   - Cons: CPU overhead for compression/decompression

3. **Memento Pooling**: Reuse memento objects
   - Pros: Reduces object creation overhead
   - Cons: Need careful management to avoid bugs

4. **Hybrid with Command Pattern**: Store commands instead of state
   - Pros: Can be more efficient
   - Cons: Can't handle all scenarios (external state changes)

5. **Narrow vs Wide Interface**: How much of the state to save?
   - Full state: Easier but memory intensive
   - Partial state: Efficient but need to carefully choose what to save

---

## 💡 Real-World Thoughts

### "But wait, what about..."

**Q: Can't we just use serialization?**
A: Yes! In Java, you could serialize objects. But:
- You still need the memento concept (what to serialize, when, who manages it)
- Serialization exposes your entire object structure
- It's language-specific
- The pattern provides better control and abstraction

**Q: What if my object is huge?**
A: Consider:
- Saving only what changes (incremental mementos)
- Compression
- Limiting history size (e.g., max 50 undos)
- Using weak references for old mementos 
- Periodically cleaning up old history

**Q: Is this overkill for simple undo?**
A: Depends! For a simple text field, maybe. But as complexity grows (formatted text, images, multiple panes), the pattern pays off by keeping things organized.

---

## 🎯 Summary

The Memento pattern is all about:
- **Saving and restoring state** without breaking encapsulation
- **Separating concerns** - who manages vs. who stores vs. who creates
- **Maintaining clean architecture** even as requirements grow

It's not always the perfect solution, but when you need undo/redo/snapshots and want to keep your OOP principles intact, it's a powerful tool in your design pattern toolkit!

---

## 📂 Code Examples

Check out:
- **`before/`** - Shows the problems with naive approaches
- **`after/`** - Clean implementation using Memento pattern

Run them, break them, modify them - that's how we learn! 🎓
