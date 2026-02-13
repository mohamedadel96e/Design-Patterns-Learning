/**
 * Main - Demonstrating PROBLEMS with the "before" approach
 * 
 * This shows why the traditional approach becomes problematic
 * as the number of operations grows.
 */
public class Main {
    public static void main(String[] args) {
        printHeader("DOCUMENT PROCESSING - BEFORE VISITOR PATTERN");
        printSubHeader("Demonstrating Problems with Traditional Approach");
        
        // Create a sample document
        Document doc = createSampleDocument();
        
        System.out.println("\n--- DOCUMENT CONTENTS ---\n");
        System.out.println("Title: " + doc.getTitle());
        System.out.println("Elements: " + doc.getElementCount());
        
        // Operation 1: Export to PDF
        System.out.println("\n\n");
        printSubHeader("OPERATION 1: Export to PDF");
        String pdf = doc.exportToPDF();
        System.out.println(pdf);
        
        // Operation 2: Export to HTML
        System.out.println("\n\n");
        printSubHeader("OPERATION 2: Export to HTML");
        String html = doc.exportToHTML();
        System.out.println(html);
        
        // Operation 3: Export to Markdown
        System.out.println("\n\n");
        printSubHeader("OPERATION 3: Export to Markdown");
        String markdown = doc.exportToMarkdown();
        System.out.println(markdown);
        
        // Operation 4: Word Count
        System.out.println("\n\n");
        printSubHeader("OPERATION 4: Word Count");
        int wordCount = doc.getTotalWordCount();
        System.out.println("Total word count: " + wordCount);
        
        // Operation 5: Spell Check
        System.out.println("\n\n");
        printSubHeader("OPERATION 5: Spell Check");
        doc.spellCheckAll();
        
        // Operation 6: Statistics
        System.out.println("\n");
        printSubHeader("OPERATION 6: Statistics");
        String stats = doc.generateFullStatistics();
        System.out.println(stats);
        
        // NOW DEMONSTRATE THE PROBLEMS
        demonstrateProblems();
    }
    
    private static Document createSampleDocument() {
        Document doc = new Document("Design Patterns Guide");
        
        doc.addElement(new Heading("Introduction", 1));
        doc.addElement(new Paragraph(
            "Design patterns are reusable solutions to common software design problems. " +
            "They represent best practices and can speed up the development process."
        ));
        
        doc.addElement(new Heading("Types of Patterns", 2));
        doc.addElement(new Paragraph(
            "Patterns are typically divided into three categories: Creational, Structural, and Behavioral."
        ));
        
        doc.addElement(new Image("patterns-diagram.png", 600, 400, "Overview of design pattern categories"));
        
        doc.addElement(new Heading("Pattern Comparison", 2));
        String[] headers = {"Pattern", "Type", "Purpose"};
        String[][] data = {
            {"Singleton", "Creational", "Ensure single instance"},
            {"Observer", "Behavioral", "Notify dependents of changes"},
            {"Visitor", "Behavioral", "Separate operations from structure"}
        };
        doc.addElement(new Table(headers, data));
        
        doc.addElement(new Paragraph(
            "Understanding these patterns will definately improve your software design skills."
        )); // Intentional typo for spell check
        
        return doc;
    }
    
    private static void demonstrateProblems() {
        System.out.println("\n\n");
        printHeader("PROBLEMS WITH THIS APPROACH");
        
        System.out.println("\nPROBLEM 1: Violates Single Responsibility Principle");
        System.out.println("-".repeat(60));
        System.out.println("Each element class (Paragraph, Image, Table, Heading) contains:");
        System.out.println("  - Data representation logic");
        System.out.println("  - PDF export logic");
        System.out.println("  - HTML export logic");
        System.out.println("  - Markdown export logic");
        System.out.println("  - Word counting logic");
        System.out.println("  - Spell checking logic");
        System.out.println("  - Statistics logic");
        System.out.println("\nResult: Bloated classes with too many responsibilities!");
        
        System.out.println("\n\nPROBLEM 2: Violates Open/Closed Principle");
        System.out.println("-".repeat(60));
        System.out.println("To add a new operation (e.g., exportToJSON()):");
        System.out.println("  1. Add abstract method to DocumentElement");
        System.out.println("  2. Implement in Paragraph");
        System.out.println("  3. Implement in Image");
        System.out.println("  4. Implement in Table");
        System.out.println("  5. Implement in Heading");
        System.out.println("  6. Add method to Document");
        System.out.println("\nResult: Must modify ALL existing classes!");
        System.out.println("What if we have 15 element types? Modify 15+ files!");
        
        System.out.println("\n\nPROBLEM 3: Scattered Operation Logic");
        System.out.println("-".repeat(60));
        System.out.println("PDF export logic is in:");
        System.out.println("  - Paragraph.exportToPDF()");
        System.out.println("  - Image.exportToPDF()");
        System.out.println("  - Table.exportToPDF()");
        System.out.println("  - Heading.exportToPDF()");
        System.out.println("  - Document.exportToPDF()");
        System.out.println("\nResult: To understand/modify PDF export, must read 5+ files!");
        System.out.println("Logic for ONE feature is scattered everywhere!");
        
        System.out.println("\n\nPROBLEM 4: Irrelevant Methods");
        System.out.println("-".repeat(60));
        System.out.println("Image class has countWords() method - doesn't make sense!");
        System.out.println("Image class has spellCheck() method - can't spell check an image!");
        System.out.println("\nResult: Cluttered interface with irrelevant methods");
        System.out.println("Violates Interface Segregation Principle!");
        
        System.out.println("\n\nPROBLEM 5: Maintenance Nightmare");
        System.out.println("-".repeat(60));
        System.out.println("Scenario: PDF export format needs to change");
        System.out.println("Must modify:");
        System.out.println("  - Paragraph.exportToPDF()");
        System.out.println("  - Image.exportToPDF()");
        System.out.println("  - Table.exportToPDF()");
        System.out.println("  - Heading.exportToPDF()");
        System.out.println("  - Document.exportToPDF()");
        System.out.println("\nResult: High risk of inconsistency and bugs!");
        System.out.println("Easy to forget to update one of the methods!");
        
        System.out.println("\n\nPROBLEM 6: Testing Complexity");
        System.out.println("-".repeat(60));
        System.out.println("To test PDF export feature:");
        System.out.println("  - Must test Paragraph.exportToPDF()");
        System.out.println("  - Must test Image.exportToPDF()");
        System.out.println("  - Must test Table.exportToPDF()");
        System.out.println("  - Must test Heading.exportToPDF()");
        System.out.println("  - Must test Document.exportToPDF()");
        System.out.println("\nResult: Can't test PDF export as a cohesive unit!");
        System.out.println("Logic is scattered, so tests are scattered too!");
        
        System.out.println("\n\nPROBLEM 7: Team Collaboration Issues");
        System.out.println("-".repeat(60));
        System.out.println("Developer A works on PDF export");
        System.out.println("Developer B works on HTML export");
        System.out.println("Developer C works on spell checking");
        System.out.println("\nThey all need to modify the same files!");
        System.out.println("  - Paragraph.java (all three developers)");
        System.out.println("  - Image.java (all three developers)");
        System.out.println("  - Table.java (all three developers)");
        System.out.println("\nResult: Constant merge conflicts!");
        System.out.println("Can't work in parallel effectively!");
        
        System.out.println("\n\n");
        printHeader("THE SOLUTION");
        System.out.println("\nCheck out the 'after' folder to see how the Visitor Pattern");
        System.out.println("solves ALL of these problems!");
        System.out.println("\nThe Visitor Pattern will:");
        System.out.println("  - Keep element classes focused on data only");
        System.out.println("  - Put each operation in its own visitor class");
        System.out.println("  - Allow adding operations without modifying elements");
        System.out.println("  - Centralize operation logic for easy maintenance");
        System.out.println("  - Enable parallel development without conflicts");
        System.out.println("  - Make testing much easier");
    }
    
    private static void printHeader(String text) {
        System.out.println("=".repeat(70));
        System.out.println("  " + text);
        System.out.println("=".repeat(70));
    }
    
    private static void printSubHeader(String text) {
        System.out.println("-".repeat(70));
        System.out.println(text);
        System.out.println("-".repeat(70));
    }
}
