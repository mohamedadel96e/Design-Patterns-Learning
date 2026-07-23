/**
 * DocumentElement - The ELEMENT interface
 * 
 * AFTER Visitor Pattern: Much simpler than before!
 * 
 * KEY CHANGES FROM "BEFORE":
 * - No operation methods (exportToPDF, exportToHTML, etc.)
 * - Just one method: accept(DocumentVisitor)
 * - Elements can now focus on WHAT they are, not WHAT can be done to them
 */
public interface DocumentElement {
    /**
     * Accept a visitor - the core of the Visitor pattern
     * 
     * This method enables "double dispatch":
     * 1. Client calls element.accept(visitor)
     * 2. Element calls visitor.visit(this)
     * 3. Java selects correct visit method based on element type
     * 
     * Result: The visitor knows both:
     * - What operation to perform (based on visitor type)
     * - What element to perform it on (based on visit method called)
     */
    void accept(DocumentVisitor visitor);
    
    /**
     * BENEFITS OF THIS APPROACH:
     * 
     * 1. Single Responsibility
     *    - Element classes only represent data
     *    - Visitor classes perform operations
     * 
     * 2. Open/Closed Principle
     *    - Add new operations by creating new visitors
     *    - No need to modify element classes
     * 
     * 3. Centralized Operation Logic
     *    - All PDF logic in PDFExportVisitor
     *    - All HTML logic in HTMLExportVisitor
     *    - Easy to find and maintain
     */
}
