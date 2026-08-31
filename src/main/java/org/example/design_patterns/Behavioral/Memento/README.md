# Memento Design Pattern

## The challenge of state preservation

When building applications like a text editor, a common requirement is providing undo and redo functionality. This implies we need a robust way to track changes over time. 

If we break down the requirements, we need to:
1. Capture the state of the editor at different points in time.
2. Store multiple states to support multiple undo operations.
3. Restore previous states when the user triggers an undo.
4. Maintain a forward history for redo operations.

While this sounds straightforward, it introduces several architectural challenges.

## Evaluating naive approaches

### Approach 1: Storing copies of the entire object

```java
class TextEditor {
    private String content;
    private List<TextEditor> history = new ArrayList<>();
    
    public void save() {
        history.add(this);
    }
}
```

If we store a reference to `this`, every item in the history points to the current state, rendering the history useless. If we attempt to deep-clone the object instead, we introduce memory overhead and force the `TextEditor` to know how to copy itself. This breaks single responsibility and becomes unwieldy as the object grows in complexity.

### Approach 2: Storing only the differences

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

Storing differences (delta encoding) seems efficient, but it drastically increases logic complexity. Handling complex operations like cut and paste, restoring state by replaying all changes from the beginning, and tracking forward changes for redo can quickly become difficult to maintain.

### Approach 3: External state management

```java
class TextEditor {
    public String content; 
    public int cursorPosition;
    public String selectedText;
}

class HistoryManager {
    private List<TextEditorState> history = new ArrayList<>();
    
    public void save(TextEditor editor) {
        TextEditorState state = new TextEditorState();
        state.content = editor.content;
        state.cursorPosition = editor.cursorPosition;
        history.add(state);
    }
}
```

This approach violates encapsulation. By exposing all internal state to `HistoryManager`, we tightly couple the two classes. Any internal change to `TextEditor` requires a corresponding change to `HistoryManager`. 

## The Memento pattern solution

To solve this properly, we need to capture state without exposing internal structure, store it externally, and maintain encapsulation.

The Memento pattern achieves this by creating a dedicated object that acts as a snapshot of state. The original object creates this snapshot (allowing access to its private fields) and hands it off to a manager. The manager holds the snapshot but cannot inspect or modify its contents.

### Pattern structure

1. **Originator (TextEditor)**: The object whose state we want to save. It creates a Memento containing a snapshot of its current internal state and uses the Memento to restore its state.
2. **Memento (TextEditorMemento)**: The snapshot itself. It acts as an opaque data object that protects its contents from access by objects other than the Originator.
3. **Caretaker (HistoryManager)**: The object responsible for deciding when to capture state, storing the Mementos, and handing them back to the Originator for restoration. It never examines the contents of a Memento.

## Implementation strategy

In a design without Memento, state preservation often leads to direct exposure of internal fields, tight coupling, and a violation of the Single Responsibility Principle.

By applying the Memento pattern:
- **Encapsulation is preserved**: Internal state remains private.
- **Single Responsibility is respected**: The Originator manages its business logic, while the Caretaker manages history.
- **Maintenance is simplified**: Adding new state fields to the Originator only requires updating the Memento generation, without affecting the Caretaker.

## When to use Memento

You should consider this pattern for:
- Undo/redo functionality.
- Transaction rollback mechanisms.
- Application or game save states.
- Document versioning.
- Snapshot-based debugging.

You should be mindful of:
- **Memory usage**: Storing full snapshots can be expensive. If memory is a concern, consider limiting history size, using delta encoding within the Memento, or compressing the stored state.
- **Complex object graphs**: If the state includes complex references, ensure the Memento safely copies necessary data without holding onto outdated references.

## Architectural comparison

Before:
The Originator and Caretaker are tightly coupled, often forcing the Originator to expose its internal state publicly. This breaks encapsulation and increases the cost of maintenance.

After:
The Originator creates an opaque Memento. The Caretaker holds this Memento and returns it when needed. The timing and storage are decoupled from the state representation.

## Extensions and variations

1. **Incremental Mementos**: Storing only the changed data rather than full state saves memory but requires more complex restoration logic.
2. **Memento Compression**: Serializing and compressing large states reduces memory footprint at the cost of CPU overhead.
3. **Hybrid with Command Pattern**: Storing commands instead of state snapshots can be highly efficient for certain workflows, often referred to as the Command-Query separation or Event Sourcing depending on scale.

## Real-world considerations

While serialization is a common way to implement state saving, it often exposes the entire object structure and ties the implementation to language-specific features. The Memento pattern provides better abstraction and control over what constitutes a "saveable state."

For very large objects, creating snapshots on every minor change (like a single keystroke) is impractical. In those cases, changes are usually batched, or the Memento pattern is combined with the Command pattern to record discrete actions.

## Final reflection

The Memento pattern is fundamentally about safely capturing and restoring state while keeping your object-oriented design clean. It cleanly separates the responsibility of managing history from the responsibility of maintaining the state itself. When you need robust undo/redo capabilities without breaking encapsulation, Memento is the standard architectural approach.
