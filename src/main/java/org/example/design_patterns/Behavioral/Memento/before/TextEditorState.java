package org.example.design_patterns.Behavioral.Memento.before;

/**
 * TextEditorState - A separate class to hold editor state
 * 
 * PROBLEMS:
 * 1. This class needs to know about TextEditor's internal structure
 * 2. If TextEditor changes, this class must change too (tight coupling)
 * 3. It exposes the state to whoever creates it
 * 4. No encapsulation - everyone can see and modify the state
 */
public class TextEditorState {
    // All public fields - anyone can access and modify
    public String content;
    public int cursorPosition;
    public String selectedText;
    public boolean isModified;
    
    /**
     * Create a state snapshot from TextEditor
     * This requires TextEditor to expose all its fields!
     */
    public TextEditorState(TextEditor editor) {
        this.content = editor.content;
        this.cursorPosition = editor.cursorPosition;
        this.selectedText = editor.selectedText;
        this.isModified = editor.isModified;
    }
    
    /**
     * Another problem: If we add a new field to TextEditor,
     * we have to remember to add it here too!
     * This is error-prone and hard to maintain.
     */
}
