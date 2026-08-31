package org.example.design_patterns.Behavioral.Visitor.before;

/**
 * DocumentElement - BEFORE Visitor Pattern
 * 
 * BASE CLASS for all document elements.
 * This is the base that all specific elements inherit from.
 */
public abstract class DocumentElement {
    protected String content;
    
    public DocumentElement(String content) {
        this.content = content;
    }
    
    // PROBLEM: All these operations are defined here,
    // forcing all subclasses to implement them
    
    public abstract String exportToPDF();
    public abstract String exportToHTML();
    public abstract String exportToMarkdown();
    public abstract int countWords();
    public abstract void spellCheck();
    public abstract String generateStatistics();
    
    /**
     * PROBLEM: Every time we add a new operation,
     * we must add a new abstract method here,
     * which forces ALL subclasses to implement it.
     * 
     * This violates the Open/Closed Principle!
     */
}
