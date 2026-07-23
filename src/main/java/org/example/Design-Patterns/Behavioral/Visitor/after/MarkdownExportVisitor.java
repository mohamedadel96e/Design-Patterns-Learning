/**
 * MarkdownExportVisitor - Concrete VISITOR for Markdown export
 * 
 * Yet another operation, yet another visitor.
 * Adding this required ZERO changes to existing code!
 */
public class MarkdownExportVisitor implements DocumentVisitor {
    private StringBuilder markdownOutput;
    
    public MarkdownExportVisitor() {
        this.markdownOutput = new StringBuilder();
    }
    
    /**
     * Visit a Paragraph - Markdown export logic
     */
    @Override
    public void visit(Paragraph paragraph) {
        markdownOutput.append(paragraph.getText()).append("\n\n");
    }
    
    /**
     * Visit an Image - Markdown export logic
     */
    @Override
    public void visit(Image image) {
        markdownOutput.append(String.format(
            "![%s](%s)\n\n",
            image.getAltText(),
            image.getImagePath()
        ));
    }
    
    /**
     * Visit a Table - Markdown export logic
     */
    @Override
    public void visit(Table table) {
        // Headers
        markdownOutput.append("|");
        for (String header : table.getHeaders()) {
            markdownOutput.append(" ").append(header).append(" |");
        }
        markdownOutput.append("\n");
        
        // Separator
        markdownOutput.append("|");
        for (int i = 0; i < table.getHeaders().length; i++) {
            markdownOutput.append(" --- |");
        }
        markdownOutput.append("\n");
        
        // Data rows
        for (String[] row : table.getData()) {
            markdownOutput.append("|");
            for (String cell : row) {
                markdownOutput.append(" ").append(cell).append(" |");
            }
            markdownOutput.append("\n");
        }
        markdownOutput.append("\n");
    }
    
    /**
     * Visit a Heading - Markdown export logic
     */
    @Override
    public void visit(Heading heading) {
        String hashes = "#".repeat(heading.getLevel());
        markdownOutput.append(hashes)
                     .append(" ")
                     .append(heading.getText())
                     .append("\n\n");
    }
    
    /**
     * Get the complete Markdown output
     */
    public String getOutput() {
        return markdownOutput.toString();
    }
    
    /**
     * Reset for reuse
     */
    public void reset() {
        markdownOutput = new StringBuilder();
    }
}
