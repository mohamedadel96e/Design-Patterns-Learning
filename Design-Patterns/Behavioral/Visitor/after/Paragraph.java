/**
 * Paragraph - AFTER Visitor Pattern (Concrete ELEMENT)
 * 
 * IMPROVEMENTS:
 * - Focused only on representing paragraph data
 * - No operation methods cluttering the class
 * - Simple accept() method for visitor pattern
 * - Easy to understand and maintain
 */
public class Paragraph implements DocumentElement {
    private String text;
    private String fontFamily;
    private int fontSize;
    
    public Paragraph(String text, String fontFamily, int fontSize) {
        this.text = text;
        this.fontFamily = fontFamily;
        this.fontSize = fontSize;
    }
    
    public Paragraph(String text) {
        this(text, "Arial", 12);
    }
    
    /**
     * Accept a visitor - implements the Visitor pattern
     * 
     * This simple method enables all operations!
     * The visitor will call back with visit(this),
     * and Java will dispatch to visit(Paragraph).
     */
    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
    
    /**
     * Getters - provide access to data for visitors
     * This is the only "exposure" needed
     */
    public String getText() {
        return text;
    }
    
    public String getFontFamily() {
        return fontFamily;
    }
    
    public int getFontSize() {
        return fontSize;
    }
    
    /**
     * COMPARE WITH "BEFORE" VERSION:
     * 
     * Before: 6 operation methods + data + getters = ~100 lines
     * After:  1 accept method + data + getters = ~40 lines
     * 
     * The class is now focused and easy to understand!
     */
}
