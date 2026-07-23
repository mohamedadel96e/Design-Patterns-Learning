/**
 * WordCountVisitor - Concrete VISITOR for counting words
 * 
 * This visitor demonstrates a visitor that:
 * - Returns a result (word count)
 * - Accumulates state across multiple visits
 * - Handles elements differently based on their nature
 */
public class WordCountVisitor implements DocumentVisitor {
    private int totalWords;
    
    public WordCountVisitor() {
        this.totalWords = 0;
    }
    
    /**
     * Visit a Paragraph - count words in text
     */
    @Override
    public void visit(Paragraph paragraph) {
        String text = paragraph.getText();
        if (text != null && !text.trim().isEmpty()) {
            totalWords += text.trim().split("\\s+").length;
        }
    }
    
    /**
     * Visit an Image - count words in alt text
     * Notice: This makes more sense than forcing Image to have countWords()
     * The visitor can decide how to handle images appropriately
     */
    @Override
    public void visit(Image image) {
        String altText = image.getAltText();
        if (altText != null && !altText.trim().isEmpty()) {
            totalWords += altText.trim().split("\\s+").length;
        }
    }
    
    /**
     * Visit a Table - count words in all cells
     */
    @Override
    public void visit(Table table) {
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
     * Visit a Heading - count words in heading text
     */
    @Override
    public void visit(Heading heading) {
        String text = heading.getText();
        if (text != null && !text.trim().isEmpty()) {
            totalWords += text.trim().split("\\s+").length;
        }
    }
    
    /**
     * Get the total word count
     */
    public int getTotalWords() {
        return totalWords;
    }
    
    /**
     * Reset for reuse
     */
    public void reset() {
        totalWords = 0;
    }
    
    /**
     * VISITOR WITH STATE:
     * 
     * This visitor accumulates state (totalWords) as it visits elements.
     * This is a common pattern for visitors that compute aggregates:
     * - Word counting
     * - Statistics collection
     * - Validation result accumulation
     * - etc.
     */
}
