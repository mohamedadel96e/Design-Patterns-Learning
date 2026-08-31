package org.example.design_patterns.Behavioral.Visitor.before;

import java.util.ArrayList;
import java.util.List;

/**
 * Document - BEFORE Visitor Pattern
 * 
 * Represents a document consisting of multiple elements.
 * 
 * PROBLEMS:
 * - To process the entire document, we call methods on each element
 * - Mixing iteration logic with operation execution
 * - Hard to add new document-level operations
 */
public class Document {
    private String title;
    private List<DocumentElement> elements;
    
    public Document(String title) {
        this.title = title;
        this.elements = new ArrayList<>();
    }
    
    public void addElement(DocumentElement element) {
        elements.add(element);
    }
    
    // PROBLEM: Document-level operations are scattered
    // Each operation needs its own method here
    
    public String exportToPDF() {
        StringBuilder pdf = new StringBuilder();
        pdf.append("[PDF-DOCUMENT title='").append(title).append("']\n\n");
        
        for (DocumentElement element : elements) {
            pdf.append(element.exportToPDF()).append("\n\n");
        }
        
        pdf.append("[/PDF-DOCUMENT]");
        return pdf.toString();
    }
    
    public String exportToHTML() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n<html>\n<head>\n");
        html.append("  <title>").append(title).append("</title>\n");
        html.append("</head>\n<body>\n\n");
        
        for (DocumentElement element : elements) {
            html.append("  ").append(element.exportToHTML()).append("\n");
        }
        
        html.append("\n</body>\n</html>");
        return html.toString();
    }
    
    public String exportToMarkdown() {
        StringBuilder md = new StringBuilder();
        md.append("# ").append(title).append("\n\n");
        
        for (DocumentElement element : elements) {
            md.append(element.exportToMarkdown());
        }
        
        return md.toString();
    }
    
    public int getTotalWordCount() {
        int total = 0;
        for (DocumentElement element : elements) {
            total += element.countWords();
        }
        return total;
    }
    
    public void spellCheckAll() {
        System.out.println("Spell checking document: \"" + title + "\"");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < elements.size(); i++) {
            System.out.println("[Element " + (i + 1) + "]");
            elements.get(i).spellCheck();
            System.out.println();
        }
    }
    
    public String generateFullStatistics() {
        StringBuilder stats = new StringBuilder();
        stats.append("Document: \"").append(title).append("\"\n");
        stats.append("=".repeat(50)).append("\n");
        stats.append("Total Elements: ").append(elements.size()).append("\n");
        stats.append("Total Words: ").append(getTotalWordCount()).append("\n\n");
        
        stats.append("Per-element statistics:\n");
        for (int i = 0; i < elements.size(); i++) {
            stats.append((i + 1)).append(". ");
            stats.append(elements.get(i).generateStatistics()).append("\n");
        }
        
        return stats.toString();
    }
    
    /**
     * PROBLEM SUMMARY FOR DOCUMENT CLASS:
     * - Each new operation requires a new method here
     * - Similar iteration pattern repeated for each operation
     * - Can't easily add operations without modifying this class
     * - Violates Open/Closed Principle
     */
    
    public String getTitle() {
        return title;
    }
    
    public List<DocumentElement> getElements() {
        return elements;
    }
    
    public int getElementCount() {
        return elements.size();
    }
}
