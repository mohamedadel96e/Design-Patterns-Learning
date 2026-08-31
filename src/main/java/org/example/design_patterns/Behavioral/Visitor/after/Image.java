package org.example.design_patterns.Behavioral.Visitor.after;

/**
 * Image - AFTER Visitor Pattern (Concrete ELEMENT)
 * 
 * IMPROVEMENTS:
 * - No irrelevant methods (countWords, spellCheck)
 * - Clean interface focused on image data
 * - Operations are handled by visitors, not here
 */
public class Image implements DocumentElement {
    private String imagePath;
    private int width;
    private int height;
    private String altText;
    
    public Image(String imagePath, int width, int height, String altText) {
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.altText = altText;
    }
    
    /**
     * Accept a visitor
     * Visitor will call visit(Image) with appropriate logic
     */
    @Override
    public void accept(DocumentVisitor visitor) {
        visitor.visit(this);
    }
    
    // Getters - data access for visitors
    public String getImagePath() {
        return imagePath;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public String getAltText() {
        return altText;
    }
    
    /**
     * BENEFITS:
     * - No more countWords() that doesn't make sense
     * - No more spellCheck() that does nothing
     * - Clean, focused interface
     * - Visitors can choose how to handle images appropriately
     */
}
