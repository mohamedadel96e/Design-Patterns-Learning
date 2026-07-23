/**
 * Table - BEFORE Visitor Pattern
 * 
 * MORE PROBLEMS:
 * 1. Tables have complex structure (rows and columns)
 * 2. All operation implementations must understand this complexity
 * 3. Logic for processing tables is duplicated in each operation method
 */
public class Table extends DocumentElement {
    private String[][] data;
    private String[] headers;
    private int rows;
    private int columns;
    
    public Table(String[] headers, String[][] data) {
        super(""); // Tables don't have simple text content
        this.headers = headers;
        this.data = data;
        this.rows = data.length;
        this.columns = headers.length;
    }
    
    // PDF Export logic for tables
    @Override
    public String exportToPDF() {
        StringBuilder pdf = new StringBuilder("[PDF-TABLE]\n");
        
        // Headers
        pdf.append("  [PDF-HEADER]");
        for (String header : headers) {
            pdf.append(" | ").append(header);
        }
        pdf.append(" [/PDF-HEADER]\n");
        
        // Data rows
        for (String[] row : data) {
            pdf.append("  [PDF-ROW]");
            for (String cell : row) {
                pdf.append(" | ").append(cell);
            }
            pdf.append(" [/PDF-ROW]\n");
        }
        
        pdf.append("[/PDF-TABLE]");
        return pdf.toString();
    }
    
    // HTML Export logic for tables
    @Override
    public String exportToHTML() {
        StringBuilder html = new StringBuilder("<table border='1'>\n");
        
        // Headers
        html.append("  <thead><tr>");
        for (String header : headers) {
            html.append("<th>").append(header).append("</th>");
        }
        html.append("</tr></thead>\n");
        
        // Data rows
        html.append("  <tbody>\n");
        for (String[] row : data) {
            html.append("    <tr>");
            for (String cell : row) {
                html.append("<td>").append(cell).append("</td>");
            }
            html.append("</tr>\n");
        }
        html.append("  </tbody>\n</table>");
        
        return html.toString();
    }
    
    // Markdown Export logic for tables
    @Override
    public String exportToMarkdown() {
        StringBuilder md = new StringBuilder();
        
        // Headers
        md.append("|");
        for (String header : headers) {
            md.append(" ").append(header).append(" |");
        }
        md.append("\n");
        
        // Separator
        md.append("|");
        for (int i = 0; i < headers.length; i++) {
            md.append(" --- |");
        }
        md.append("\n");
        
        // Data rows
        for (String[] row : data) {
            md.append("|");
            for (String cell : row) {
                md.append(" ").append(cell).append(" |");
            }
            md.append("\n");
        }
        md.append("\n");
        
        return md.toString();
    }
    
    // Word count logic for tables
    @Override
    public int countWords() {
        int total = 0;
        
        // Count words in headers
        for (String header : headers) {
            if (header != null && !header.isEmpty()) {
                total += header.split("\\s+").length;
            }
        }
        
        // Count words in data
        for (String[] row : data) {
            for (String cell : row) {
                if (cell != null && !cell.isEmpty()) {
                    total += cell.split("\\s+").length;
                }
            }
        }
        
        return total;
    }
    
    // Spell check logic for tables
    @Override
    public void spellCheck() {
        System.out.println("Spell checking table with " + rows + " rows and " + columns + " columns");
        // Would need to check each cell...
        // This logic is complex and specific to tables
    }
    
    // Statistics logic for tables
    @Override
    public String generateStatistics() {
        int wordCount = countWords();
        int cellCount = rows * columns;
        return String.format("Table Stats: %dx%d (%d cells), %d total words",
                rows, columns, cellCount, wordCount);
    }
    
    /**
     * PROBLEM OBSERVATION:
     * - Notice how much code is in each export method
     * - Three different export formats, all with similar table-traversal logic
     * - If we need to change how tables are processed, we touch many methods
     * - The table-processing logic is mixed with format-specific logic
     */
    
    // Getters
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
}
