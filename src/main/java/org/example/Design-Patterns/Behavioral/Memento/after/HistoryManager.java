import java.util.ArrayList;
import java.util.List;

/**
 * HistoryManager - AFTER Memento Pattern (The CARETAKER)
 * 
 * IMPROVEMENTS OVER "BEFORE" VERSION:
 * 1. ✅ Doesn't know anything about TextEditor's internal structure
 * 2. ✅ Treats mementos as opaque objects (can hold but can't peek inside)
 * 3. ✅ Single Responsibility - only manages WHEN to save/restore
 * 4. ✅ Loosely coupled - changes to TextEditor don't affect this class
 * 5. ✅ Can easily work with different Originator types
 * 
 * Think of this as a librarian:
 * - Organizes and stores sealed envelopes (mementos)
 * - Knows which shelf each envelope is on
 * - Never opens the envelopes to see what's inside
 */
public class HistoryManager {
    private List<TextEditorMemento> history;
    private int currentPosition;
    private static final int MAX_HISTORY_SIZE = 50;
    
    public HistoryManager() {
        this.history = new ArrayList<>();
        this.currentPosition = -1;
    }
    
    /**
     * Save a memento to history
     * Notice: We don't need to know anything about TextEditor!
     * We just receive a memento and store it.
     * 
     * @param memento The memento to save
     */
    public void saveState(TextEditorMemento memento) {
        // Remove any "redo" history if we're not at the end
        while (history.size() > currentPosition + 1) {
            history.remove(history.size() - 1);
        }
        
        // Add the new memento
        history.add(memento);
        currentPosition++;
        
        // Limit history size to prevent memory issues
        if (history.size() > MAX_HISTORY_SIZE) {
            history.remove(0);
            currentPosition--;
        }
        
        System.out.println("💾 State saved to history (Total states: " + history.size() + ")");
    }
    
    /**
     * Get the previous memento for undo
     * Notice: We don't restore the state ourselves!
     * We just return the memento and let TextEditor handle restoration.
     * 
     * @return The previous memento, or null if at the beginning
     */
    public TextEditorMemento undo() {
        if (currentPosition > 0) {
            currentPosition--;
            TextEditorMemento memento = history.get(currentPosition);
            System.out.println("↶ Undo - Moving to position " + currentPosition);
            return memento;
        } else {
            System.out.println("⚠ Nothing to undo!");
            return null;
        }
    }
    
    /**
     * Get the next memento for redo
     * 
     * @return The next memento, or null if at the end
     */
    public TextEditorMemento redo() {
        if (canRedo()) {
            currentPosition++;
            TextEditorMemento memento = history.get(currentPosition);
            System.out.println("↷ Redo - Moving to position " + currentPosition);
            return memento;
        } else {
            System.out.println("⚠ Nothing to redo!");
            return null;
        }
    }
    
    /**
     * Check if undo is possible
     */
    public boolean canUndo() {
        return currentPosition > 0;
    }
    
    /**
     * Check if redo is possible
     */
    public boolean canRedo() {
        return currentPosition < history.size() - 1;
    }
    
    /**
     * Get history statistics
     */
    public int getHistorySize() {
        return history.size();
    }
    
    public int getCurrentPosition() {
        return currentPosition;
    }
    
    /**
     * Get memento metadata (without exposing internal state)
     * This demonstrates that we can work with mementos without
     * knowing their internal structure!
     */
    public void displayHistory() {
        System.out.println("\n📚 History (Total: " + history.size() + ", Current: " + currentPosition + ")");
        for (int i = 0; i < history.size(); i++) {
            String marker = (i == currentPosition) ? " ← Current" : "";
            System.out.println("  [" + i + "] " + history.get(i).getMetadata() + marker);
        }
    }
    
    /**
     * Clear all history
     */
    public void clear() {
        history.clear();
        currentPosition = -1;
        System.out.println("🗑️  History cleared");
    }
    
    /**
     * KEY OBSERVATION:
     * This entire class works with mementos without ever:
     * - Knowing what data is inside them
     * - Modifying their contents
     * - Depending on TextEditor's implementation
     * 
     * This is the power of the Memento pattern!
     */
}
