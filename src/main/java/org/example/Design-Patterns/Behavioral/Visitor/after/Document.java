import java.util.ArrayList;
import java.util.List;

/**
 * Document - AFTER Visitor Pattern
 * 
 * IMPROVEMENTS:
 * - No operation-specific methods
 * - Just manages elements and accepts visitors
 * - Much cleaner and more focused than "before" version
 */
public class Document {
    private String title;
    private List<DocumentElement> elements;
    
    public Document(String title) {
        this.title = title;
        this.elements = new ArrayList<>();
    }
    
    /**
     * Add an element to the document
     */
    public void addElement(DocumentElement element) {
        elements.add(element);
    }
    
    /**
     * Accept a visitor - the document will pass the visitor to all its elements
     * This is the Composite pattern combined with Visitor!
     */
    public void accept(DocumentVisitor visitor) {
        for (DocumentElement element : elements) {
            element.accept(visitor);
        }
    }
    
    /**
     * Helper method to export with a specific visitor
     * This wraps the common pattern of creating a visitor, accepting it, and getting output
     */
    public String exportWith(DocumentVisitor exportVisitor) {
        accept(exportVisitor);
        
        // This assumes the visitor has a getOutput() method
        // In a real system, you might use reflection or a common interface
        if (exportVisitor instanceof PDFExportVisitor) {
            return ((PDFExportVisitor) exportVisitor).getOutput();
        } else if (exportVisitor instanceof HTMLExportVisitor) {
            return ((HTMLExportVisitor) exportVisitor).getOutput();
        } else if (exportVisitor instanceof MarkdownExportVisitor) {
            return ((MarkdownExportVisitor) exportVisitor).getOutput();
        }
        
        return "";
    }
    
    // Basic getters
    public String getTitle() {
        return title;
    }
    
    public List<DocumentElement> getElements() {
        return elements;
    }
    
    public int getElementCount() {
        return elements.size();
    }
    
    /**
     * COMPARE WITH "BEFORE" VERSION:
     * 
     * Before: Had methods like:
     *   - exportToPDF()
     *   - exportToHTML()
     *   - exportToMarkdown()
     *   - getTotalWordCount()
     *   - spellCheckAll()
     *   - generateFullStatistics()
     * 
     * After: Just has:
     *   - accept(DocumentVisitor)
     *   - Element management methods
     * 
     * Result: Much cleaner, follows Single Responsibility,
     *         easy to add new operations without modifying this class!
     */
}
