/**
 * PDFExportVisitor - Concrete VISITOR for PDF export
 * 
 * This visitor centralizes ALL PDF export logic in one place.
 * 
 * BENEFITS:
 * - All PDF-related code is here (not scattered across element classes)
 * - Easy to modify PDF format (just edit this file)
 * - Easy to understand PDF export (read one file, not five)
 * - Easy to test PDF export (test one class)
 */
public class PDFExportVisitor implements DocumentVisitor {
    private StringBuilder pdfOutput;
    
    public PDFExportVisitor() {
        this.pdfOutput = new StringBuilder();
    }
    
    /**
     * Visit a Paragraph - PDF export logic for paragraphs
     */
    @Override
    public void visit(Paragraph paragraph) {
        pdfOutput.append(String.format(
            "[PDF-PARAGRAPH font='%s' size=%d] %s [/PDF-PARAGRAPH]\n",
            paragraph.getFontFamily(),
            paragraph.getFontSize(),
            paragraph.getText()
        ));
    }
    
    /**
     * Visit an Image - PDF export logic for images
     */
    @Override
    public void visit(Image image) {
        pdfOutput.append(String.format(
            "[PDF-IMAGE src='%s' width=%d height=%d alt='%s' /]\n",
            image.getImagePath(),
            image.getWidth(),
            image.getHeight(),
            image.getAltText()
        ));
    }
    
    /**
     * Visit a Table - PDF export logic for tables
     */
    @Override
    public void visit(Table table) {
        pdfOutput.append("[PDF-TABLE]\n");
        
        // Export headers
        pdfOutput.append("  [PDF-HEADER]");
        for (String header : table.getHeaders()) {
            pdfOutput.append(" | ").append(header);
        }
        pdfOutput.append(" [/PDF-HEADER]\n");
        
        // Export data rows
        for (String[] row : table.getData()) {
            pdfOutput.append("  [PDF-ROW]");
            for (String cell : row) {
                pdfOutput.append(" | ").append(cell);
            }
            pdfOutput.append(" [/PDF-ROW]\n");
        }
        
        pdfOutput.append("[/PDF-TABLE]\n");
    }
    
    /**
     * Visit a Heading - PDF export logic for headings
     */
    @Override
    public void visit(Heading heading) {
        int fontSize = 24 - (heading.getLevel() * 2);
        pdfOutput.append(String.format(
            "[PDF-HEADING level=%d size=%d] %s [/PDF-HEADING]\n",
            heading.getLevel(),
            fontSize,
            heading.getText()
        ));
    }
    
    /**
     * Get the complete PDF output
     */
    public String getOutput() {
        return pdfOutput.toString();
    }
    
    /**
     * Reset for reuse
     */
    public void reset() {
        pdfOutput = new StringBuilder();
    }
    
    /**
     * KEY OBSERVATIONS:
     * 
     * 1. ALL PDF logic is in ONE place
     *    - Before: Scattered across Paragraph.java, Image.java, Table.java, Heading.java
     *    - After: All here in PDFExportVisitor.java
     * 
     * 2. Easy to modify PDF format
     *    - Change one file, not four
     *    - Consistent changes across all element types
     * 
     * 3. Easy to test
     *    - Test PDFExportVisitor independently
     *    - Mock elements for unit testing
     * 
     * 4. Visitor maintains state (pdfOutput)
     *    - Accumulates output as it visits elements
     *    - This is a common visitor pattern
     */
}
