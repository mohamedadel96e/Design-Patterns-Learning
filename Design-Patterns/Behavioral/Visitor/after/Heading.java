/**
 * Heading - AFTER Visitor Pattern (Concrete ELEMENT)
 * 
 * Simple, focused class representing heading data.
 */
public class Heading implements DocumentElement {
    private String text;
    private int level; // 1-6 for h1-h6
    
    public Heading(String text, int level) {
        this.text = text;
        this.level = Math.max(1, Math.min(6, level)); // Clamp to 1-6
    }
    
    /**
     * Accept a visitor
     */
    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
    
    // Getters
    public String getText() {
        return text;
    }
    
    public int getLevel() {
        return level;
    }
}
