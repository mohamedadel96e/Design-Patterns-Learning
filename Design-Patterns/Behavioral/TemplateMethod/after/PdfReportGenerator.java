/**
 * Concrete implementation for PDF report generation.
 * Implements the specific steps required for PDF format.
 */
public class PdfReportGenerator extends ReportGenerator {

    public PdfReportGenerator(String title, String[] data) {
        super(title, data);
    }

    @Override
    protected void initialize() {
        System.out.println("Initializing PDF report generation...");
        System.out.println("Setting up PDF document");
        System.out.println("Configuring PDF properties");
    }

    @Override
    protected void addHeader() {
        System.out.println("\n=== PDF Header ===");
        System.out.println("Title: " + title);
        System.out.println("Format: PDF Document");
        System.out.println("Generated: " + java.time.LocalDateTime.now());
    }

    @Override
    protected void formatContent() {
        System.out.println("\n--- PDF Content ---");
        for (String item : data) {
            System.out.println("📄 " + item);
        }
    }

    @Override
    protected void addFooter() {
        System.out.println("\n=== PDF Footer ===");
        System.out.println("Page 1 of 1");
        System.out.println("© 2026 Company Name");
    }

    @Override
    protected void finalize() {
        System.out.println("\nFinalizing PDF report...");
        System.out.println("Applying PDF encryption");
    }

    @Override
    protected String getReportType() {
        return "PDF";
    }

    // Override hook method to add watermark for PDF
    @Override
    protected boolean shouldAddWatermark() {
        return true;
    }

    @Override
    protected void addWatermark() {
        System.out.println("Adding PDF watermark with encryption...");
    }
}
