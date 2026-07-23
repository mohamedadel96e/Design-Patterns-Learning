/**
 * Concrete implementation for Excel report generation.
 * This demonstrates how easy it is to add new report types.
 */
public class ExcelReportGenerator extends ReportGenerator {

    public ExcelReportGenerator(String title, String[] data) {
        super(title, data);
    }

    @Override
    protected void initialize() {
        System.out.println("Initializing Excel report generation...");
        System.out.println("Creating workbook and worksheet");
        System.out.println("Setting up cell formatting");
    }

    @Override
    protected void addHeader() {
        System.out.println("\n┌─────────────────────────────────┐");
        System.out.println("│ " + title);
        System.out.println("│ Format: Excel Spreadsheet");
        System.out.println("│ Generated: " + java.time.LocalDateTime.now());
        System.out.println("└─────────────────────────────────┘");
        System.out.println("\n│ A │ B              │");
        System.out.println("├───┼────────────────┤");
    }

    @Override
    protected void formatContent() {
        int row = 1;
        for (String item : data) {
            System.out.println("│ " + row++ + " │ " + item);
        }
    }

    @Override
    protected void addFooter() {
        System.out.println("├───┴────────────────┤");
        System.out.println("│ Total: " + data.length + " rows");
        System.out.println("│ © 2026 Company Name");
        System.out.println("└────────────────────┘");
    }

    @Override
    protected void finalize() {
        System.out.println("\nFinalizing Excel report...");
        System.out.println("Applying cell borders and colors");
        System.out.println("Auto-sizing columns");
    }

    @Override
    protected String getReportType() {
        return "Excel";
    }

    // Override hook to add watermark
    @Override
    protected boolean shouldAddWatermark() {
        return true;
    }

    @Override
    protected void addWatermark() {
        System.out.println("Adding Excel sheet watermark...");
    }
}
