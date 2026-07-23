/**
 * Table - AFTER Visitor Pattern (Concrete ELEMENT)
 * 
 * IMPROVEMENTS:
 * - Represents table data only
 * - All export/processing logic moved to visitors
 * - Much cleaner than the "before" version
 */
public class Table implements DocumentElement {
    private String[] headers;
    private String[][] data;
    private int rows;
    private int columns;
    
    public Table(String[] headers, String[][] data) {
        this.headers = headers;
        this.data = data;
        this.rows = data.length;
        this.columns = headers.length;
    }
    
    /**
     * Accept a visitor
     * The visitor will handle table processing logic
     */
    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
    
    // Getters - data access for visitors
    public String[] getHeaders() {
        return headers;
    }
    
    public String[][] getData() {
        return data;
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getColumns() {
        return columns;
    }
    
    /**
     * COMPARE WITH "BEFORE":
     * 
     * Before: Complex export methods mixed with data = ~150 lines
     * After:  Simple data representation = ~40 lines
     * 
     * All the complex table processing logic is now in visitors
     * where it can be maintained separately!
     */
}
