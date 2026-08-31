package org.example.design_patterns.Behavioral.Visitor.after;

/**
 * StatisticsVisitor - Concrete VISITOR for gathering statistics
 * 
 * This visitor demonstrates:
 * - Accumulating multiple pieces of state
 * - Returning complex results
 * - Type-specific counting
 */
public class StatisticsVisitor implements DocumentVisitor {
    private int paragraphCount;
    private int imageCount;
    private int tableCount;
    private int headingCount;
    private int totalWords;
    private int totalCells;
    
    public StatisticsVisitor() {
        reset();
    }
    
    /**
     * Visit a Paragraph - count it and its words
     */
    @Override
    public void visit(Paragraph paragraph) {
        paragraphCount++;
        
        String text = paragraph.getText();
        if (text != null && !text.trim().isEmpty()) {
            totalWords += text.trim().split("\\s+").length;
        }
    }
    
    /**
     * Visit an Image - count it
     */
    @Override
    public void visit(Image image) {
        imageCount++;
    }
    
    /**
     * Visit a Table - count it, its cells, and words
     */
    @Override
    public void visit(Table table) {
        tableCount++;
        totalCells += table.getRows() * table.getColumns();
        
        // Count words in headers
        for (String header : table.getHeaders()) {
            if (header != null && !header.trim().isEmpty()) {
                totalWords += header.trim().split("\\s+").length;
            }
        }
        
        // Count words in data cells
        for (String[] row : table.getData()) {
            for (String cell : row) {
                if (cell != null && !cell.trim().isEmpty()) {
                    totalWords += cell.trim().split("\\s+").length;
                }
            }
        }
    }
    
    /**
     * Visit a Heading - count it and its words
     */
    @Override
    public void visit(Heading heading) {
        headingCount++;
        
        String text = heading.getText();
        if (text != null && !text.trim().isEmpty()) {
            totalWords += text.trim().split("\\s+").length;
        }
    }
    
    /**
     * Get formatted statistics report
     */
    public String getReport() {
        StringBuilder report = new StringBuilder();
        report.append("Document Statistics\n");
        report.append("=".repeat(50)).append("\n");
        report.append("Total Elements: ").append(getTotalElements()).append("\n");
        report.append("  - Paragraphs: ").append(paragraphCount).append("\n");
        report.append("  - Images: ").append(imageCount).append("\n");
        report.append("  - Tables: ").append(tableCount).append("\n");
        report.append("  - Headings: ").append(headingCount).append("\n");
        report.append("\nContent Statistics:\n");
        report.append("  - Total Words: ").append(totalWords).append("\n");
        report.append("  - Total Table Cells: ").append(totalCells).append("\n");
        
        return report.toString();
    }
    
    /**
     * Individual getters for specific statistics
     */
    public int getParagraphCount() { return paragraphCount; }
    public int getImageCount() { return imageCount; }
    public int getTableCount() { return tableCount; }
    public int getHeadingCount() { return headingCount; }
    public int getTotalWords() { return totalWords; }
    public int getTotalCells() { return totalCells; }
    
    public int getTotalElements() {
        return paragraphCount + imageCount + tableCount + headingCount;
    }
    
    /**
     * Reset for reuse
     */
    public void reset() {
        paragraphCount = 0;
        imageCount = 0;
        tableCount = 0;
        headingCount = 0;
        totalWords = 0;
        totalCells = 0;
    }
    
    /**
     * COMPLEX STATE VISITOR:
     * 
     * This visitor accumulates multiple pieces of state.
     * This is useful for:
     * - Statistics gathering
     * - Validation (collecting multiple error types)
     * - Analysis (collecting multiple metrics)
     * 
     * The visitor can then provide methods to access
     * or format this accumulated data.
     */
}
