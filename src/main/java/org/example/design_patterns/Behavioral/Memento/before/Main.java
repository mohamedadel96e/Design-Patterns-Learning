package org.example.design_patterns.Behavioral.Memento.before;

/**
 * Main - Demonstrating the PROBLEMS with the "before" approach
 * 
 * This example shows why the naive approach is problematic:
 * 1. Encapsulation violations
 * 2. Tight coupling
 * 3. Ability to accidentally corrupt state
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║   TEXT EDITOR - BEFORE MEMENTO PATTERN           ║");
        System.out.println("║   Demonstrating Problems with Naive Approach      ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        TextEditor editor = new TextEditor();
        HistoryManager history = new HistoryManager();
        
        // Initial save
        history.save(editor);
        
        System.out.println("\n📝 Step 1: Type 'Hello'");
        editor.typeText("Hello");
        editor.displayState();
        history.save(editor);
        
        System.out.println("\n📝 Step 2: Type ' World'");
        editor.typeText(" World");
        editor.displayState();
        history.save(editor);
        
        System.out.println("\n📝 Step 3: Type '!'");
        editor.typeText("!");
        editor.displayState();
        history.save(editor);
        
        // Demonstrate undo
        System.out.println("\n⏮️  Performing Undo...");
        history.undo(editor);
        editor.displayState();
        
        System.out.println("\n⏮️  Performing Undo again...");
        history.undo(editor);
        editor.displayState();
        
        // Demonstrate redo
        System.out.println("\n⏭️  Performing Redo...");
        history.redo(editor);
        editor.displayState();
        
        // NOW LET'S DEMONSTRATE THE PROBLEMS! 🚨
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║        ⚠️  DEMONSTRATING THE PROBLEMS ⚠️           ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // PROBLEM 1: Anyone can directly modify the editor's state!
        System.out.println("🚨 PROBLEM 1: Direct field access allows corruption");
        System.out.println("Someone accidentally sets content = null...");
        editor.content = null;  // This should NOT be possible!
        System.out.println("Result: Editor is now in an invalid state!");
        try {
            editor.displayState();  // This might crash!
        } catch (NullPointerException e) {
            System.out.println("💥 CRASH! NullPointerException: " + e.getMessage());
        }
        
        // Fix it for next demonstration
        editor.content = "Fixed";
        
        // PROBLEM 2: History state can be modified too!
        System.out.println("\n🚨 PROBLEM 2: History state is also exposed");
        System.out.println("Someone gets a historical state and modifies it...");
        TextEditorState oldState = history.getStateAt(0);
        if (oldState != null) {
            System.out.println("Original state content: \"" + oldState.content + "\"");
            oldState.content = "CORRUPTED HISTORY!";
            System.out.println("Modified state content: \"" + oldState.content + "\"");
            System.out.println("Now the history is corrupted!");
        }
        
        // PROBLEM 3: Tight coupling means changes propagate
        System.out.println("\n🚨 PROBLEM 3: Tight coupling");
        System.out.println("If we add a new field to TextEditor (like 'fontSize'),");
        System.out.println("we must also update:");
        System.out.println("  - TextEditorState (add field)");
        System.out.println("  - HistoryManager's undo() (copy field)");
        System.out.println("  - HistoryManager's redo() (copy field)");
        System.out.println("This is error-prone and hard to maintain!");
        
        // PROBLEM 4: No clear responsibility separation
        System.out.println("\n🚨 PROBLEM 4: Responsibility confusion");
        System.out.println("HistoryManager knows too much:");
        System.out.println("  - It knows TextEditor's internal structure");
        System.out.println("  - It knows how to restore state");
        System.out.println("  - It manages history");
        System.out.println("This violates Single Responsibility Principle!");
        
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              💡 THE SOLUTION? 💡                   ║");
        System.out.println("║         Check out the 'after' folder to see        ║");
        System.out.println("║       how the Memento Pattern solves these!        ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
    }
}
