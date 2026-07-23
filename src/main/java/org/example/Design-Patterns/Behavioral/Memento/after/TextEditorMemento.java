/**
 * TextEditorMemento - The MEMENTO
 * 
 * This is the "time capsule" that holds a snapshot of TextEditor's state.
 * 
 * KEY DESIGN DECISIONS:
 * 1. All fields are PRIVATE and FINAL - state is immutable once created
 * 2. No public setters - nobody can modify the saved state
 * 3. Only package-private getters - only TextEditor (in same package) can read values
 * 4. This class is "opaque" to the outside world
 * 
 * Think of it as a sealed envelope:
 * - TextEditor puts data in and seals it
 * - HistoryManager can hold it but can't open it
 * - Only TextEditor can open and read it
 */
public class TextEditorMemento {
    // Private final fields - immutable state snapshot
    private final String content;
    private final int cursorPosition;
    private final String selectedText;
    private final boolean isModified;
    private final long timestamp;  // Additional metadata
    
    /**
     * Package-private constructor - only accessible within the same package
     * This ensures that only TextEditor can create mementos
     */
    TextEditorMemento(String content, int cursorPosition, String selectedText, boolean isModified) {
        this.content = content;
        this.cursorPosition = cursorPosition;
        this.selectedText = selectedText;
        this.isModified = isModified;
        this.timestamp = System.currentTimeMillis();
    }
    
    /**
     * Package-private getters - only TextEditor can access these
     * The HistoryManager never calls these methods!
     */
    String getContent() {
        return content;
    }
    
    int getCursorPosition() {
        return cursorPosition;
    }
    
    String getSelectedText() {
        return selectedText;
    }
    
    boolean isModified() {
        return isModified;
    }
    
    long getTimestamp() {
        return timestamp;
    }
    
    /**
     * Public method that doesn't expose internal state
     * Anyone can call this, but it doesn't reveal the actual data
     */
    public String getMetadata() {
        return "Memento created at: " + new java.util.Date(timestamp);
    }
    
    /**
     * Note: We DON'T provide a way to modify these values!
     * Once created, a memento is immutable.
     * This prevents accidental corruption of historical states.
     */
}
