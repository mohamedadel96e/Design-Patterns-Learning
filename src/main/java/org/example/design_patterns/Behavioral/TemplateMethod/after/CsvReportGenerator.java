package org.example.design_patterns.Behavioral.TemplateMethod.after;

/**
 * Concrete implementation for CSV report generation.
 * Implements the specific steps required for CSV format.
 */
public class CsvReportGenerator extends ReportGenerator {

    public CsvReportGenerator(String title, String[] data) {
        super(title, data);
    }

    @Override
    protected void initialize() {
        System.out.println("Initializing CSV report generation...");
        System.out.println("Setting up CSV formatter");
        System.out.println("Configuring delimiter and encoding");
    }

    @Override
    protected void addHeader() {
        System.out.println("\n# CSV Report");
        System.out.println("# Title: " + title);
        System.out.println("# Format: Comma-Separated Values");
        System.out.println("# Generated: " + java.time.LocalDateTime.now());
        System.out.println("\nID,Description");
    }

    @Override
    protected void formatContent() {
        int id = 1;
        for (String item : data) {
            System.out.println(id++ + ",\"" + item + "\"");
        }
    }

    @Override
    protected void addFooter() {
        System.out.println("\n# Total Records: " + data.length);
        System.out.println("# © 2026 Company Name");
    }

    @Override
    protected void finalize() {
        System.out.println("\nFinalizing CSV report...");
        System.out.println("Validating CSV format");
    }

    @Override
    protected String getReportType() {
        return "CSV";
    }

    // CSV reports don't need watermark (uses default hook implementation)
}
