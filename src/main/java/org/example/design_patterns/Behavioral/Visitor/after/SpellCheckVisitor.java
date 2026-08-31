package org.example.design_patterns.Behavioral.Visitor.after;

/**
 * SpellCheckVisitor - Concrete VISITOR for spell checking
 * 
 * This visitor demonstrates:
 * - Side effects (printing output)
 * - Different behavior for different element types
 * - Skipping elements that don't make sense (images don't need spell check)
 */
public class SpellCheckVisitor implements DocumentVisitor {
    private int issuesFound;
    private String[] commonMisspellings = {
        "teh", "recieve", "occured", "definately", "seperate", "occassion"
    };
    
    public SpellCheckVisitor() {
        this.issuesFound = 0;
    }
    
    /**
     * Visit a Paragraph - spell check the text
     */
    @Override
    public void visit(Paragraph paragraph) {
        System.out.println("Spell checking paragraph: \"" + 
            truncate(paragraph.getText(), 50) + "\"");
        
        checkText(paragraph.getText());
    }
    
    /**
     * Visit an Image - could check alt text, but we'll skip for simplicity
     * This demonstrates that visitors can handle elements differently!
     */
    @Override
    public void visit(Image image) {
        // Images don't need spell checking (or just check alt text if desired)
        System.out.println("Skipping spell check for image: " + image.getImagePath());
    }
    
    /**
     * Visit a Table - spell check all cells
     */
    @Override
    public void visit(Table table) {
        System.out.println("Spell checking table with " + table.getRows() + 
                         " rows and " + table.getColumns() + " columns");
        
        // Check headers
        for (String header : table.getHeaders()) {
            checkText(header);
        }
        
        // Check data cells
        for (String[] row : table.getData()) {
            for (String cell : row) {
                checkText(cell);
            }
        }
    }
    
    /**
     * Visit a Heading - spell check the heading text
     */
    @Override
    public void visit(Heading heading) {
        System.out.println("Spell checking heading (h" + heading.getLevel() + 
                         "): \"" + heading.getText() + "\"");
        
        checkText(heading.getText());
    }
    
    /**
     * Helper method to check text for common misspellings
     */
    private void checkText(String text) {
        if (text == null || text.isEmpty()) {
            return;
        }
        
        String lowerText = text.toLowerCase();
        for (String misspelling : commonMisspellings) {
            if (lowerText.contains(misspelling)) {
                System.out.println("  - Found potential misspelling: " + misspelling);
                issuesFound++;
            }
        }
    }
    
    /**
     * Helper method to truncate long text
     */
    private String truncate(String text, int maxLength) {
        if (text.length() <= maxLength) {
            return text;
        }
        return text.substring(0, maxLength - 3) + "...";
    }
    
    /**
     * Get the number of issues found
     */
    public int getIssuesFound() {
        return issuesFound;
    }
    
    /**
     * Reset for reuse
     */
    public void reset() {
        issuesFound = 0;
    }
    
    /**
     * VISITOR WITH SIDE EFFECTS:
     * 
     * This visitor has side effects (printing to console).
     * This is perfectly valid for visitors that:
     * - Generate reports
     * - Log information
     * - Display progress
     * - etc.
     * 
     * Not all visitors need to be purely functional!
     */
}
