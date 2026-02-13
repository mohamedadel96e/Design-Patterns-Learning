public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  BEFORE: Code Duplication in Report Generation        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        String[] salesData = {
            "Q1 Sales: $150,000",
            "Q2 Sales: $175,000",
            "Q3 Sales: $200,000",
            "Q4 Sales: $225,000"
        };

        // Each report generator duplicates the same algorithm structure
        // with only minor variations in specific steps

        System.out.println("📊 GENERATING PDF REPORT");
        System.out.println("════════════════════════════════");
        PdfReportGenerator pdfReport = new PdfReportGenerator("Annual Sales Report", salesData);
        pdfReport.generateReport();

        System.out.println("📊 GENERATING HTML REPORT");
        System.out.println("════════════════════════════════");
        HtmlReportGenerator htmlReport = new HtmlReportGenerator("Annual Sales Report", salesData);
        htmlReport.generateReport();

        System.out.println("📊 GENERATING CSV REPORT");
        System.out.println("════════════════════════════════");
        CsvReportGenerator csvReport = new CsvReportGenerator("Annual Sales Report", salesData);
        csvReport.generateReport();

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  PROBLEMS:                                             ║");
        System.out.println("║  • Duplicated algorithm structure across classes       ║");
        System.out.println("║  • Changes require updates in multiple places          ║");
        System.out.println("║  • Difficult to ensure consistency                     ║");
        System.out.println("║  • Hard to add new report types                        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
}
