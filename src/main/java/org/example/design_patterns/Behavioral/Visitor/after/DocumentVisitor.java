package org.example.design_patterns.Behavioral.Visitor.after;

/**
 * DocumentVisitor - The VISITOR interface
 * 
 * This interface defines all the operations that can be performed
 * on document elements. Each visit method corresponds to one element type.
 * 
 * KEY INSIGHT: This is the "operation" abstraction.
 * Each concrete visitor implements a different operation.
 */
public interface DocumentVisitor {
    /**
     * Visit a Paragraph element
     * The specific visitor will define what "visiting" means
     * (e.g., exporting, counting, spell checking, etc.)
     */
    void visit(Paragraph paragraph);
    
    /**
     * Visit an Image element
     */
    void visit(Image image);
    
    /**
     * Visit a Table element
     */
    void visit(Table table);
    
    /**
     * Visit a Heading element
     */
    void visit(Heading heading);
    
    /**
     * IMPORTANT NOTES:
     * 
     * 1. One visit method per element type
     *    - This is required for double dispatch to work
     *    - Java will select the correct method based on parameter type
     * 
     * 2. When adding a new ELEMENT type:
     *    - Add a new visit method here
     *    - Update all existing visitors
     *    - This is the trade-off: easy to add operations, harder to add elements
     * 
     * 3. When adding a new OPERATION:
     *    - Just create a new class implementing this interface
     *    - No need to modify any existing code!
     *    - This is the main benefit of the pattern
     */
}
