import java.util.ArrayList;
import java.util.List;

/**
 * HistoryManager - BEFORE Memento Pattern
 * 
 * PROBLEMS WITH THIS APPROACH:
 * 1. Directly accesses TextEditor's public fields (tight coupling)
 * 2. Knows too much about TextEditor's internal structure
 * 3. If TextEditor's structure changes, this class breaks
 * 4. Violates Single Responsibility - it manages history AND knows about TextEditor structure
 */
public class HistoryManager {
    private List<TextEditorState> history;
    private int currentPosition;
    private static final int MAX_HISTORY_SIZE = 50;
    
    public HistoryManager() {
        this.history = new ArrayList<>();
        this.currentPosition = -1;
    }
    
    /**
     * Save current state
     * Problem: This creates tight coupling with TextEditor
     */
    public void save(TextEditor editor) {
        // Remove any "redo" history if we're not at the end
        while (history.size() > currentPosition + 1) {
            history.remove(history.size() - 1);
        }
        
        // Create a new state snapshot
        TextEditorState state = new TextEditorState(editor);
        history.add(state);
        currentPosition++;
        
        // Limit history size to prevent memory issues
        if (history.size() > MAX_HISTORY_SIZE) {
            history.remove(0);
            currentPosition--;
        }
        
        System.out.println("💾 Saved state (History size: " + history.size() + ")");
    }
    
    /**
     * Undo to previous state
     * Problem: Directly modifies TextEditor's public fields!
     */
    public void undo(TextEditor editor) {
        if (currentPosition > 0) {
            currentPosition--;
            TextEditorState state = history.get(currentPosition);
            
            // 🚨 DIRECTLY ACCESSING AND MODIFYING PUBLIC FIELDS
            // This is a major encapsulation violation!
            editor.content = state.content;
            editor.cursorPosition = state.cursorPosition;
            editor.selectedText = state.selectedText;
            editor.isModified = state.isModified;
            
            System.out.println("↶ Undo performed (Position: " + currentPosition + ")");
        } else {
            System.out.println("⚠ Nothing to undo!");
        }
    }
    
    /**
     * Redo to next state
     * Problem: Same as undo - directly modifying public fields
     */
    public void redo(TextEditor editor) {
        if (currentPosition < history.size() - 1) {
            currentPosition++;
            TextEditorState state = history.get(currentPosition);
            
            // 🚨 MORE DIRECT FIELD ACCESS
            editor.content = state.content;
            editor.cursorPosition = state.cursorPosition;
            editor.selectedText = state.selectedText;
            editor.isModified = state.isModified;
            
            System.out.println("↷ Redo performed (Position: " + currentPosition + ")");
        } else {
            System.out.println("⚠ Nothing to redo!");
        }
    }
    
    public boolean canUndo() {
        return currentPosition > 0;
    }
    
    public boolean canRedo() {
        return currentPosition < history.size() - 1;
    }
    
    public int getHistorySize() {
        return history.size();
    }
    
    public int getCurrentPosition() {
        return currentPosition;
    }
    
    /**
     * ADDITIONAL PROBLEM: Anyone can access the history and modify it!
     * This demonstrates another encapsulation issue.
     */
    public TextEditorState getStateAt(int index) {
        if (index >= 0 && index < history.size()) {
            return history.get(index);
        }
        return null;
    }
}
