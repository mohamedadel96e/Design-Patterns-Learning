package org.example.design_patterns.Behavioral.Visitor.before;

/**
 * Image - BEFORE Visitor Pattern
 * 
 * PROBLEMS:
 * 1. Many operations don't make sense for images (countWords, spellCheck)
 * 2. But we must implement them because they're in the base class
 * 3. Cluttered interface with irrelevant methods
 * 4. Same maintenance issues as Paragraph
 */
public class Image extends DocumentElement {
    private String imagePath;
    private int width;
    private int height;
    private String altText;
    
    public Image(String imagePath, int width, int height, String altText) {
        super(imagePath);
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        this.altText = altText;
    }
    
    // PDF Export logic for images
    @Override
    public String exportToPDF() {
        return String.format("[PDF-IMAGE src='%s' width=%d height=%d alt='%s' /]",
                imagePath, width, height, altText);
    }
    
    // HTML Export logic for images
    @Override
    public String exportToHTML() {
        return String.format("<img src='%s' width='%d' height='%d' alt='%s' />",
                imagePath, width, height, altText);
    }
    
    // Markdown Export logic for images
    @Override
    public String exportToMarkdown() {
        return String.format("![%s](%s)\n\n", altText, imagePath);
    }
    
    // PROBLEM: This doesn't make sense for images!
    // Images don't have words, but we must implement it
    @Override
    public int countWords() {
        // Return 0 or count alt text words?
        // Either way, the method shouldn't exist in the first place
        if (altText != null && !altText.isEmpty()) {
            return altText.split("\\s+").length;
        }
        return 0;
    }
    
    // PROBLEM: Can't spell check an image!
    // But we're forced to implement this method
    @Override
    public void spellCheck() {
        // Does nothing meaningful, but clutters the interface
        System.out.println("Spell checking image alt text: \"" + altText + "\"");
        // Could check alt text, but the point is this shouldn't be here
    }
    
    // Statistics logic for images
    @Override
    public String generateStatistics() {
        return String.format("Image Stats: %dx%d pixels, alt text: \"%s\"",
                width, height, altText);
    }
    
    /**
     * ADDITIONAL PROBLEMS:
     * - Implementing irrelevant methods (countWords, spellCheck)
     * - These methods exist only because the base class requires them
     * - Violates Interface Segregation Principle
     * - Confusing API - users might call countWords() on an image
     */
    
    // Getters
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
}
