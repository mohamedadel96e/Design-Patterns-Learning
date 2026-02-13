/**
 * TextEditor - AFTER Memento Pattern (The ORIGINATOR)
 * 
 * IMPROVEMENTS OVER "BEFORE" VERSION:
 * 1. ✅ All fields are PRIVATE - proper encapsulation
 * 2. ✅ Only this class can create mementos (has access to private state)
 * 3. ✅ Only this class can restore from mementos (knows how to interpret them)
 * 4. ✅ Clear responsibility - manages its own state
 * 5. ✅ Adding new fields only requires changes here, not in HistoryManager
 */
public class TextEditor {
    // ✅ PRIVATE FIELDS - proper encapsulation!
    private String content;
    private int cursorPosition;
    private String selectedText;
    private boolean isModified;
    
    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
        this.selectedText = "";
        this.isModified = false;
    }
    
    // ═══════════════════════════════════════════════════════════
    // MEMENTO PATTERN METHODS - The core of the pattern!
    // ═══════════════════════════════════════════════════════════
    
    /**
     * Create a memento (snapshot) of current state
     * This is the ONLY way to capture the editor's state for undo/redo
     * 
     * @return TextEditorMemento containing current state
     */
    public TextEditorMemento save() {
        System.out.println("📸 Creating snapshot of current state...");
        return new TextEditorMemento(content, cursorPosition, selectedText, isModified);
    }
    
    /**
     * Restore state from a memento
     * This is the ONLY way to restore a previous state
     * 
     * @param memento The memento containing the state to restore
     */
    public void restore(TextEditorMemento memento) {
        System.out.println("🔄 Restoring state from memento...");
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
        this.selectedText = memento.getSelectedText();
        this.isModified = memento.isModified();
    }
    
    // ═══════════════════════════════════════════════════════════
    // REGULAR TEXT EDITOR OPERATIONS
    // ═══════════════════════════════════════════════════════════
    
    /**
     * Type text at current cursor position
     */
    public void typeText(String text) {
        if (selectedText != null && !selectedText.isEmpty()) {
            // Replace selected text
            content = content.replace(selectedText, text);
            selectedText = "";
        } else {
            // Insert at cursor position
            String before = content.substring(0, Math.min(cursorPosition, content.length()));
            String after = content.substring(Math.min(cursorPosition, content.length()));
            content = before + text + after;
            cursorPosition += text.length();
        }
        isModified = true;
    }
    
    /**
     * Delete text (like backspace)
     */
    public void deleteText(int length) {
        if (cursorPosition >= length) {
            String before = content.substring(0, cursorPosition - length);
            String after = content.substring(cursorPosition);
            content = before + after;
            cursorPosition -= length;
            isModified = true;
        }
    }
    
    /**
     * Select text from current position
     */
    public void selectText(int startPos, int endPos) {
        if (startPos >= 0 && endPos <= content.length() && startPos < endPos) {
            selectedText = content.substring(startPos, endPos);
            cursorPosition = endPos;
        }
    }
    
    /**
     * Move cursor to specific position
     */
    public void moveCursor(int position) {
        if (position >= 0 && position <= content.length()) {
            cursorPosition = position;
        }
    }
    
    // ═══════════════════════════════════════════════════════════
    // PUBLIC GETTERS - Only expose what's necessary
    // ═══════════════════════════════════════════════════════════
    
    public String getContent() {
        return content;
    }
    
    public int getCursorPosition() {
        return cursorPosition;
    }
    
    public String getSelectedText() {
        return selectedText;
    }
    
    public boolean isModified() {
        return isModified;
    }
    
    public void displayState() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("Content: \"" + content + "\"");
        System.out.println("Cursor Position: " + cursorPosition);
        System.out.println("Selected: \"" + selectedText + "\"");
        System.out.println("Modified: " + isModified);
        System.out.println("═══════════════════════════════════════");
    }
    
    /**
     * Note the difference from "before":
     * - Fields are private (encapsulated)
     * - We provide CONTROLLED access through save() and restore()
     * - HistoryManager never needs to know about our internal structure
     * - If we add new fields, only this class needs to change
     */
}
