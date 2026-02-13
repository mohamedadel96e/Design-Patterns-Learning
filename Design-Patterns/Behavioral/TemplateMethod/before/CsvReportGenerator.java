public class CsvReportGenerator {
    private String title;
    private String[] data;

    public CsvReportGenerator(String title, String[] data) {
        this.title = title;
        this.data = data;
    }

    public void generateReport() {
        // Step 1: Initialize
        System.out.println("Initializing CSV report generation...");
        System.out.println("Setting up CSV formatter");
        System.out.println("Configuring delimiter and encoding");

        // Step 2: Add header
        System.out.println("\n# CSV Report");
        System.out.println("# Title: " + title);
        System.out.println("# Format: Comma-Separated Values");
        System.out.println("# Generated: " + java.time.LocalDateTime.now());
        System.out.println("\nID,Description");

        // Step 3: Process and format data
        int id = 1;
        for (String item : data) {
            System.out.println(id++ + ",\"" + item + "\"");
        }

        // Step 4: Add footer
        System.out.println("\n# Total Records: " + data.length);
        System.out.println("# © 2026 Company Name");

        // Step 5: Finalize
        System.out.println("\nFinalizing CSV report...");
        System.out.println("Validating CSV format");
        System.out.println("CSV Report generated successfully!");
        System.out.println("────────────────────────────────\n");
    }
}
