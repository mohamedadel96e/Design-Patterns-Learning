/**
 * Main - Demonstrating the MEMENTO PATTERN
 * 
 * This example shows how the Memento Pattern solves all the problems
 * we saw in the "before" version:
 * ✅ Encapsulation is preserved
 * ✅ Clear separation of concerns
 * ✅ Loosely coupled components
 * ✅ Easy to maintain and extend
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║     TEXT EDITOR - WITH MEMENTO PATTERN            ║");
        System.out.println("║     Clean Solution to Undo/Redo                   ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        // Create our components
        TextEditor editor = new TextEditor();
        HistoryManager history = new HistoryManager();
        
        // Initial state save
        System.out.println("💾 Initial State:");
        history.saveState(editor.save());  // editor.save() creates memento
        editor.displayState();
        
        // Make some changes
        System.out.println("\n📝 Step 1: Type 'Hello'");
        editor.typeText("Hello");
        editor.displayState();
        history.saveState(editor.save());
        
        System.out.println("\n📝 Step 2: Type ' World'");
        editor.typeText(" World");
        editor.displayState();
        history.saveState(editor.save());
        
        System.out.println("\n📝 Step 3: Type '!'");
        editor.typeText("!");
        editor.displayState();
        history.saveState(editor.save());
        
        System.out.println("\n📝 Step 4: Select and replace with '!!!'");
        editor.selectText(11, 12);  // Select the '!'
        editor.typeText("!!!");
        editor.displayState();
        history.saveState(editor.save());
        
        // Show history
        history.displayHistory();
        
        // Demonstrate undo
        System.out.println("\n" + "═".repeat(50));
        System.out.println("⏮️  UNDO OPERATIONS");
        System.out.println("═".repeat(50));
        
        performUndo(editor, history);
        performUndo(editor, history);
        performUndo(editor, history);
        
        // Demonstrate redo
        System.out.println("\n" + "═".repeat(50));
        System.out.println("⏭️  REDO OPERATIONS");
        System.out.println("═".repeat(50));
        
        performRedo(editor, history);
        performRedo(editor, history);
        
        // Make a new change after undo (this clears redo history)
        System.out.println("\n" + "═".repeat(50));
        System.out.println("📝 NEW CHANGE AFTER UNDO");
        System.out.println("═".repeat(50));
        
        System.out.println("\n📝 Type ' Amazing'");
        editor.typeText(" Amazing");
        editor.displayState();
        history.saveState(editor.save());
        
        System.out.println("\n💡 Notice: Redo history is now cleared");
        System.out.println("Can redo? " + history.canRedo());
        
        history.displayHistory();
        
        // Demonstrate the benefits
        demonstrateBenefits(editor, history);
    }
    
    /**
     * Helper method to perform undo with nice output
     */
    private static void performUndo(TextEditor editor, HistoryManager history) {
        System.out.println("\n⏮️  Performing Undo...");
        TextEditorMemento memento = history.undo();
        if (memento != null) {
            editor.restore(memento);  // editor.restore() reads the memento
            editor.displayState();
        }
    }
    
    /**
     * Helper method to perform redo with nice output
     */
    private static void performRedo(TextEditor editor, HistoryManager history) {
        System.out.println("\n⏭️  Performing Redo...");
        TextEditorMemento memento = history.redo();
        if (memento != null) {
            editor.restore(memento);
            editor.displayState();
        }
    }
    
    /**
     * Demonstrate the benefits of the Memento pattern
     */
    private static void demonstrateBenefits(TextEditor editor, HistoryManager history) {
        System.out.println("\n\n");
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║        ✅ BENEFITS OF MEMENTO PATTERN ✅           ║");
        System.out.println("╚════════════════════════════════════════════════════╝\n");
        
        System.out.println("1. 🔒 ENCAPSULATION PRESERVED");
        System.out.println("   - TextEditor's fields are private");
        System.out.println("   - No one can directly access or modify internal state");
        System.out.println("   - Try: editor.content = \"hack\" // Compile error!");
        System.out.println();
        
        System.out.println("2. 🎯 CLEAR RESPONSIBILITIES");
        System.out.println("   - TextEditor: Creates and restores from mementos");
        System.out.println("   - Memento: Holds state snapshot (immutable)");
        System.out.println("   - HistoryManager: Manages when to save/restore");
        System.out.println("   - Each class has ONE clear job!");
        System.out.println();
        
        System.out.println("3. 🔌 LOOSE COUPLING");
        System.out.println("   - HistoryManager doesn't know TextEditor's structure");
        System.out.println("   - Can add fields to TextEditor without changing HistoryManager");
        System.out.println("   - Can even use HistoryManager with different editors!");
        System.out.println();
        
        System.out.println("4. 🛡️ MEMENTO IMMUTABILITY");
        System.out.println("   - Once created, mementos can't be modified");
        System.out.println("   - Historical states are protected from corruption");
        System.out.println("   - Try: memento.content = \"hack\" // No such method!");
        System.out.println();
        
        System.out.println("5. 🧩 EASY TO EXTEND");
        System.out.println("   - Want to add a 'fontSize' field? Just:");
        System.out.println("     a) Add private field to TextEditor");
        System.out.println("     b) Include in memento creation/restoration");
        System.out.println("     c) HistoryManager needs NO changes!");
        System.out.println();
        
        System.out.println("6. 🎛️ FLEXIBLE MANAGEMENT");
        System.out.println("   - Can limit history size (memory management)");
        System.out.println("   - Can add metadata to mementos (timestamps, labels)");
        System.out.println("   - Can implement different storage strategies");
        System.out.println();
        
        System.out.println("╔════════════════════════════════════════════════════╗");
        System.out.println("║              🎓 KEY TAKEAWAY 🎓                    ║");
        System.out.println("║                                                    ║");
        System.out.println("║  The Memento Pattern lets you capture and restore ║");
        System.out.println("║  object state WITHOUT breaking encapsulation!     ║");
        System.out.println("║                                                    ║");
        System.out.println("║  Three simple rules:                               ║");
        System.out.println("║  1. Originator creates & restores from mementos   ║");
        System.out.println("║  2. Memento stores state (opaque to others)       ║");
        System.out.println("║  3. Caretaker manages WHEN, not HOW               ║");
        System.out.println("╚════════════════════════════════════════════════════╝");
        
        System.out.println("\n🎉 That's the Memento Pattern! Clean, simple, and powerful.");
    }
}
