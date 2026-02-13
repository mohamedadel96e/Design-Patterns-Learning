/**
 * Heading - BEFORE Visitor Pattern
 * 
 * Yet another element type with all the same problems.
 * Notice the pattern: similar structure, same issues repeated.
 */
public class Heading extends DocumentElement {
    private int level; // 1-6 for h1-h6
    
    public Heading(String text, int level) {
        super(text);
        this.level = Math.max(1, Math.min(6, level)); // Clamp to 1-6
    }
    
    // PDF Export logic for headings
    @Override
    public String exportToPDF() {
        int fontSize = 24 - (level * 2); // Larger font for lower level numbers
        return String.format("[PDF-HEADING level=%d size=%d] %s [/PDF-HEADING]",
                level, fontSize, content);
    }
    
    // HTML Export logic for headings
    @Override
    public String exportToHTML() {
        return String.format("<h%d>%s</h%d>", level, content, level);
    }
    
    // Markdown Export logic for headings
    @Override
    public String exportToMarkdown() {
        String hashes = "#".repeat(level);
        return hashes + " " + content + "\n\n";
    }
    
    // Word count logic for headings
    @Override
    public int countWords() {
        if (content == null || content.trim().isEmpty()) {
            return 0;
        }
        return content.trim().split("\\s+").length;
    }
    
    // Spell check logic for headings
    @Override
    public void spellCheck() {
        System.out.println("Spell checking heading (h" + level + "): \"" + content + "\"");
        // Simulate finding issues
        if (content.toLowerCase().contains("definately")) {
            System.out.println("  - Found misspelling: 'definately' -> should be 'definitely'");
        }
    }
    
    // Statistics logic for headings
    @Override
    public String generateStatistics() {
        int words = countWords();
        return String.format("Heading Stats: Level %d, %d words", level, words);
    }
    
    // Getters
    public String getText() {
        return content;
    }
    
    public int getLevel() {
        return level;
    }
}
