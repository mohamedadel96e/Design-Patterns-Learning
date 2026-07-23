/**
 * HTMLExportVisitor - Concrete VISITOR for HTML export
 * 
 * Another operation, another visitor!
 * Notice how similar the structure is to PDFExportVisitor,
 * but with HTML-specific logic.
 */
public class HTMLExportVisitor implements DocumentVisitor {
    private StringBuilder htmlOutput;
    
    public HTMLExportVisitor() {
        this.htmlOutput = new StringBuilder();
    }
    
    /**
     * Visit a Paragraph - HTML export logic
     */
    @Override
    public void visit(Paragraph paragraph) {
        htmlOutput.append(String.format(
            "<p style='font-family:%s; font-size:%dpx;'>%s</p>\n",
            paragraph.getFontFamily(),
            paragraph.getFontSize(),
            paragraph.getText()
        ));
    }
    
    /**
     * Visit an Image - HTML export logic
     */
    @Override
    public void visit(Image image) {
        htmlOutput.append(String.format(
            "<img src='%s' width='%d' height='%d' alt='%s' />\n",
            image.getImagePath(),
            image.getWidth(),
            image.getHeight(),
            image.getAltText()
        ));
    }
    
    /**
     * Visit a Table - HTML export logic
     */
    @Override
    public void visit(Table table) {
        htmlOutput.append("<table border='1'>\n");
        
        // Export headers
        htmlOutput.append("  <thead><tr>");
        for (String header : table.getHeaders()) {
            htmlOutput.append("<th>").append(header).append("</th>");
        }
        htmlOutput.append("</tr></thead>\n");
        
        // Export data rows
        htmlOutput.append("  <tbody>\n");
        for (String[] row : table.getData()) {
            htmlOutput.append("    <tr>");
            for (String cell : row) {
                htmlOutput.append("<td>").append(cell).append("</td>");
            }
            htmlOutput.append("</tr>\n");
        }
        htmlOutput.append("  </tbody>\n</table>\n");
    }
    
    /**
     * Visit a Heading - HTML export logic
     */
    @Override
    public void visit(Heading heading) {
        int level = heading.getLevel();
        htmlOutput.append(String.format(
            "<h%d>%s</h%d>\n",
            level,
            heading.getText(),
            level
        ));
    }
    
    /**
     * Get the complete HTML output
     */
    public String getOutput() {
        return htmlOutput.toString();
    }
    
    /**
     * Reset for reuse
     */
    public void reset() {
        htmlOutput = new StringBuilder();
    }
    
    /**
     * ADDING THIS VISITOR WAS EASY:
     * 
     * 1. Created new class HTMLExportVisitor
     * 2. Implemented DocumentVisitor interface
     * 3. Wrote HTML-specific logic for each element type
     * 4. Done!
     * 
     * Did NOT need to modify:
     * - Paragraph.java
     * - Image.java
     * - Table.java
     * - Heading.java
     * - DocumentElement.java
     * - Any other existing code!
     * 
     * This is the Open/Closed Principle in action!
     */
}
