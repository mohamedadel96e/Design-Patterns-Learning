/**
 * Paragraph - BEFORE Visitor Pattern
 * 
 * PROBLEMS WITH THIS APPROACH:
 * 1. Class has too many responsibilities (data + all operations)
 * 2. To add a new operation, we must modify this class
 * 3. Operation logic for one feature (e.g., PDF export) is scattered across multiple classes
 * 4. Hard to test operations in isolation
 */
public class Paragraph extends DocumentElement {
    private String fontFamily;
    private int fontSize;
    
    public Paragraph(String text, String fontFamily, int fontSize) {
        super(text);
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }
    
    public Paragraph(String text) {
        this(text, "Arial", 12);
    }
    
    // PDF Export logic for paragraphs
    @Override
    public String exportToPDF() {
        return String.format("[PDF-PARAGRAPH font='%s' size=%d] %s [/PDF-PARAGRAPH]",
                fontFamily, fontSize, content);
    }
    
    // HTML Export logic for paragraphs
    @Override
    public String exportToHTML() {
        return String.format("<p style='font-family:%s; font-size:%dpx;'>%s</p>",
                fontFamily, fontSize, content);
    }
    
    // Markdown Export logic for paragraphs
    @Override
    public String exportToMarkdown() {
        return content + "\n\n";
    }
    
    // Word count logic for paragraphs
    @Override
    public int countWords() {
        if (content == null || content.trim().isEmpty()) {
            return 0;
        }
        return content.trim().split("\\s+").length;
    }
    
    // Spell check logic for paragraphs
    @Override
    public void spellCheck() {
        System.out.println("Spell checking paragraph: \"" + 
                (content.length() > 50 ? content.substring(0, 47) + "..." : content) + "\"");
        
        // Simulate spell checking
        String[] commonMisspellings = {"teh", "recieve", "occured", "definately"};
        for (String word : commonMisspellings) {
            if (content.toLowerCase().contains(word)) {
                System.out.println("  - Found potential misspelling: " + word);
            }
        }
    }
    
    // Statistics logic for paragraphs
    @Override
    public String generateStatistics() {
        int words = countWords();
        int chars = content.length();
        return String.format("Paragraph Stats: %d words, %d characters", words, chars);
    }
    
    /**
     * PROBLEM SUMMARY:
     * - This class mixes data representation with 6 different operations
     * - Violates Single Responsibility Principle
     * - To add "exportToJSON()", we must modify this class (violates Open/Closed)
     * - If PDF export logic needs to change, we modify this file
     *   (and Image.java and Table.java and Heading.java...)
     */
    
    // Getters
    public String getText() {
        return content;
    }
    
    public String getFontFamily() {
        return fontFamily;
    }
    
    public int getFontSize() {
        return fontSize;
    }
}
