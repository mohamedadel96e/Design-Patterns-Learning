/**
 * Main - Demonstrating the VISITOR PATTERN
 * 
 * This shows how the Visitor Pattern solves all the problems
 * we saw in the "before" version.
 */
public class Main {
    public static void main(String[] args) {
        printHeader("DOCUMENT PROCESSING - WITH VISITOR PATTERN");
        printSubHeader("Clean Solution Using Visitor Pattern");
        
        // Create a sample document
        Document doc = createSampleDocument();
        
        System.out.println("\n--- DOCUMENT CONTENTS ---\n");
        System.out.println("Title: " + doc.getTitle());
        System.out.println("Elements: " + doc.getElementCount());
        
        // Demonstrate all operations using visitors
        demonstrateOperations(doc);
        
        // Show the benefits
        demonstrateBenefits(doc);
        
        // Show how easy it is to add new operations
        demonstrateExtensibility();
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
    
    private static void demonstrateOperations(Document doc) {
        // Operation 1: PDF Export
        System.out.println("\n\n");
        printSubHeader("OPERATION 1: Export to PDF");
        System.out.println("Creating PDFExportVisitor and visiting document...\n");
        
        PDFExportVisitor pdfVisitor = new PDFExportVisitor();
        doc.accept(pdfVisitor);
        System.out.println(pdfVisitor.getOutput());
        
        // Operation 2: HTML Export
        System.out.println("\n");
        printSubHeader("OPERATION 2: Export to HTML");
        System.out.println("Creating HTMLExportVisitor and visiting document...\n");
        
        HTMLExportVisitor htmlVisitor = new HTMLExportVisitor();
        doc.accept(htmlVisitor);
        System.out.println(htmlVisitor.getOutput());
        
        // Operation 3: Markdown Export
        System.out.println("\n");
        printSubHeader("OPERATION 3: Export to Markdown");
        System.out.println("Creating MarkdownExportVisitor and visiting document...\n");
        
        MarkdownExportVisitor markdownVisitor = new MarkdownExportVisitor();
        doc.accept(markdownVisitor);
        System.out.println(markdownVisitor.getOutput());
        
        // Operation 4: Word Count
        System.out.println("\n");
        printSubHeader("OPERATION 4: Word Count");
        System.out.println("Creating WordCountVisitor and visiting document...\n");
        
        WordCountVisitor wordCountVisitor = new WordCountVisitor();
        doc.accept(wordCountVisitor);
        System.out.println("Total word count: " + wordCountVisitor.getTotalWords());
        
        // Operation 5: Spell Check
        System.out.println("\n\n");
        printSubHeader("OPERATION 5: Spell Check");
        System.out.println("Creating SpellCheckVisitor and visiting document...\n");
        
        SpellCheckVisitor spellCheckVisitor = new SpellCheckVisitor();
        doc.accept(spellCheckVisitor);
        System.out.println("\nTotal issues found: " + spellCheckVisitor.getIssuesFound());
        
        // Operation 6: Statistics
        System.out.println("\n\n");
        printSubHeader("OPERATION 6: Statistics");
        System.out.println("Creating StatisticsVisitor and visiting document...\n");
        
        StatisticsVisitor statsVisitor = new StatisticsVisitor();
        doc.accept(statsVisitor);
        System.out.println(statsVisitor.getReport());
    }
    
    private static void demonstrateBenefits(Document doc) {
        System.out.println("\n\n");
        printHeader("BENEFITS OF THE VISITOR PATTERN");
        
        System.out.println("\nBENEFIT 1: Single Responsibility Principle");
        System.out.println("-".repeat(70));
        System.out.println("Element classes (Paragraph, Image, etc.) only represent data.");
        System.out.println("Visitor classes (PDFExportVisitor, etc.) only perform operations.");
        System.out.println("\nResult: Clean separation of concerns!");
        System.out.println("  - Paragraph.java: ~40 lines (vs ~100 before)");
        System.out.println("  - PDFExportVisitor.java: All PDF logic in one place");
        
        System.out.println("\n\nBENEFIT 2: Open/Closed Principle");
        System.out.println("-".repeat(70));
        System.out.println("To add a new operation (e.g., JSONExportVisitor):");
        System.out.println("  1. Create JSONExportVisitor implementing DocumentVisitor");
        System.out.println("  2. Implement visit methods for each element type");
        System.out.println("  3. Done!");
        System.out.println("\nNo modifications to existing classes needed!");
        System.out.println("Element classes remain unchanged!");
        
        System.out.println("\n\nBENEFIT 3: Centralized Operation Logic");
        System.out.println("-".repeat(70));
        System.out.println("All PDF logic is in: PDFExportVisitor.java (ONE file)");
        System.out.println("All HTML logic is in: HTMLExportVisitor.java (ONE file)");
        System.out.println("\nBefore: Logic scattered across 5+ files");
        System.out.println("After: Logic centralized in 1 file per operation");
        System.out.println("\nResult: Easy to understand, modify, and test!");
        
        System.out.println("\n\nBENEFIT 4: No Irrelevant Methods");
        System.out.println("-".repeat(70));
        System.out.println("Image class no longer has countWords() method.");
        System.out.println("Image class no longer has spellCheck() method.");
        System.out.println("\nInstead: Visitors decide how to handle each element type.");
        System.out.println("WordCountVisitor can count alt text words if desired.");
        System.out.println("SpellCheckVisitor can skip images entirely.");
        System.out.println("\nResult: Clean interfaces, appropriate behavior!");
        
        System.out.println("\n\nBENEFIT 5: Easy Maintenance");
        System.out.println("-".repeat(70));
        System.out.println("Scenario: PDF format needs to change");
        System.out.println("Solution: Edit PDFExportVisitor.java only (ONE file)");
        System.out.println("\nBefore: Had to edit 5+ files");
        System.out.println("After: Edit 1 file");
        System.out.println("\nResult: Less risk, faster changes, fewer bugs!");
        
        System.out.println("\n\nBENEFIT 6: Easier Testing");
        System.out.println("-".repeat(70));
        System.out.println("To test PDF export:");
        System.out.println("  - Create PDFExportVisitor");
        System.out.println("  - Test visit() methods with mock elements");
        System.out.println("  - Verify output");
        System.out.println("\nPDF export tested as a cohesive unit!");
        System.out.println("Can test visitor independently of real elements!");
        
        System.out.println("\n\nBENEFIT 7: Team Collaboration");
        System.out.println("-".repeat(70));
        System.out.println("Developer A works on: PDFExportVisitor.java");
        System.out.println("Developer B works on: HTMLExportVisitor.java");
        System.out.println("Developer C works on: SpellCheckVisitor.java");
        System.out.println("\nNo file conflicts!");
        System.out.println("Each developer has their own file!");
        System.out.println("\nResult: Parallel development, no merge conflicts!");
        
        System.out.println("\n\nBENEFIT 8: Type Safety");
        System.out.println("-".repeat(70));
        System.out.println("Double dispatch ensures correct method is called:");
        System.out.println("  - visitor.visit(paragraph) calls visit(Paragraph)");
        System.out.println("  - visitor.visit(image) calls visit(Image)");
        System.out.println("\nNo instanceof checks needed!");
        System.out.println("No casting needed!");
        System.out.println("Compile-time type checking!");
        System.out.println("\nResult: Safer, cleaner code!");
    }
    
    private static void demonstrateExtensibility() {
        System.out.println("\n\n");
        printHeader("DEMONSTRATING EXTENSIBILITY");
        
        System.out.println("\nSCENARIO: Product manager wants JSON export");
        System.out.println("-".repeat(70));
        System.out.println("\nTraditional Approach:");
        System.out.println("  1. Add exportToJSON() to DocumentElement (abstract)");
        System.out.println("  2. Implement in Paragraph.java");
        System.out.println("  3. Implement in Image.java");
        System.out.println("  4. Implement in Table.java");
        System.out.println("  5. Implement in Heading.java");
        System.out.println("  6. Add exportToJSON() to Document.java");
        System.out.println("\nTotal: Modify 6 files!");
        
        System.out.println("\n\nVisitor Pattern Approach:");
        System.out.println("  1. Create JSONExportVisitor.java");
        System.out.println("     - Implements DocumentVisitor");
        System.out.println("     - Implements visit(Paragraph)");
        System.out.println("     - Implements visit(Image)");
        System.out.println("     - Implements visit(Table)");
        System.out.println("     - Implements visit(Heading)");
        System.out.println("  2. Done!");
        System.out.println("\nTotal: Create 1 new file, modify 0 existing files!");
        
        System.out.println("\n\nSCENARIO: Add 5 more operations");
        System.out.println("-".repeat(70));
        System.out.println("Traditional: 5 operations x 5 element classes = 25+ file modifications");
        System.out.println("Visitor Pattern: 5 new visitor classes = 5 new files, 0 modifications");
        System.out.println("\nThe more operations you add, the more the pattern pays off!");
        
        System.out.println("\n\nTRADE-OFF: Adding a new element type");
        System.out.println("-".repeat(70));
        System.out.println("If we add a new element type (e.g., CodeBlock):");
        System.out.println("  1. Create CodeBlock.java implementing DocumentElement");
        System.out.println("  2. Add visit(CodeBlock) to DocumentVisitor interface");
        System.out.println("  3. Update ALL existing visitors to handle CodeBlock");
        System.out.println("\nIf you have 10 visitors, that's 10 files to update.");
        System.out.println("\nThis is the trade-off:");
        System.out.println("  - Easy to add operations (common)");
        System.out.println("  - Harder to add element types (rare)");
        System.out.println("\nUse Visitor when operations change more than element types!");
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
