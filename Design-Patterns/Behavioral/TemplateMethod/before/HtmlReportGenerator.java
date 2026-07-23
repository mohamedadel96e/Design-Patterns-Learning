public class HtmlReportGenerator {
    private String title;
    private String[] data;

    public HtmlReportGenerator(String title, String[] data) {
        this.title = title;
        this.data = data;
    }

    public void generateReport() {
        // Step 1: Initialize
        System.out.println("Initializing HTML report generation...");
        System.out.println("Creating HTML structure");
        System.out.println("Loading CSS stylesheets");

        // Step 2: Add header
        System.out.println("\n<html><head>");
        System.out.println("<title>" + title + "</title>");
        System.out.println("</head><body>");
        System.out.println("<h1>" + title + "</h1>");
        System.out.println("<p>Format: HTML Document</p>");
        System.out.println("<p>Generated: " + java.time.LocalDateTime.now() + "</p>");

        // Step 3: Process and format data
        System.out.println("\n<div class='content'>");
        System.out.println("<ul>");
        for (String item : data) {
            System.out.println("  <li>🌐 " + item + "</li>");
        }
        System.out.println("</ul>");
        System.out.println("</div>");

        // Step 4: Add footer
        System.out.println("\n<footer>");
        System.out.println("<p>© 2026 Company Name</p>");
        System.out.println("</footer>");
        System.out.println("</body></html>");

        // Step 5: Finalize
        System.out.println("\nFinalizing HTML report...");
        System.out.println("Minifying HTML");
        System.out.println("HTML Report generated successfully!");
        System.out.println("────────────────────────────────\n");
    }
}
