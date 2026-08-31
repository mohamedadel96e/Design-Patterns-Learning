package org.example.design_patterns.Behavioral.TemplateMethod.after;

/**
 * Concrete implementation for HTML report generation.
 * Implements the specific steps required for HTML format.
 */
public class HtmlReportGenerator extends ReportGenerator {

    public HtmlReportGenerator(String title, String[] data) {
        super(title, data);
    }

    @Override
    protected void initialize() {
        System.out.println("Initializing HTML report generation...");
        System.out.println("Creating HTML structure");
        System.out.println("Loading CSS stylesheets");
    }

    @Override
    protected void addHeader() {
        System.out.println("\n<html><head>");
        System.out.println("<title>" + title + "</title>");
        System.out.println("</head><body>");
        System.out.println("<h1>" + title + "</h1>");
        System.out.println("<p>Format: HTML Document</p>");
        System.out.println("<p>Generated: " + java.time.LocalDateTime.now() + "</p>");
    }

    @Override
    protected void formatContent() {
        System.out.println("\n<div class='content'>");
        System.out.println("<ul>");
        for (String item : data) {
            System.out.println("  <li>🌐 " + item + "</li>");
        }
        System.out.println("</ul>");
        System.out.println("</div>");
    }

    @Override
    protected void addFooter() {
        System.out.println("\n<footer>");
        System.out.println("<p>© 2026 Company Name</p>");
        System.out.println("</footer>");
        System.out.println("</body></html>");
    }

    @Override
    protected void finalize() {
        System.out.println("\nFinalizing HTML report...");
        System.out.println("Minifying HTML");
    }

    @Override
    protected String getReportType() {
        return "HTML";
    }

    // HTML reports don't need watermark (uses default hook implementation)
}
