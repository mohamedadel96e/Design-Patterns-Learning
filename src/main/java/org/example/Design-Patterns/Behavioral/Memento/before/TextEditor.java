/**
 * TextEditor - BEFORE Memento Pattern
 * 
 * PROBLEMS WITH THIS APPROACH:
 * 1. All fields are public - breaking encapsulation!
 * 2. Anyone can modify the editor's state directly
 * 3. Hard to maintain - changing internal structure affects all code
 * 4. No clear separation of concerns
 */
public class TextEditor {
    // 🚨 PUBLIC FIELDS - BAD PRACTICE!
    // This violates encapsulation - anyone can access/modify these directly
    public String content;
    public int cursorPosition;
    public String selectedText;
    public boolean isModified;
    
    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
        this.selectedText = "";
        this.isModified = false;
    }
    
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
    
    public void displayState() {
        System.out.println("═══════════════════════════════════════");
        System.out.println("Content: \"" + content + "\"");
        System.out.println("Cursor Position: " + cursorPosition);
        System.out.println("Selected: \"" + selectedText + "\"");
        System.out.println("Modified: " + isModified);
        System.out.println("═══════════════════════════════════════");
    }
}
