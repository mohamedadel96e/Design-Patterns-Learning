public class Main {
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  AFTER: Template Method Pattern                       ║");
        System.out.println("╚════════════════════════════════════════════════════════╝\n");

        String[] salesData = {
            "Q1 Sales: $150,000",
            "Q2 Sales: $175,000",
            "Q3 Sales: $200,000",
            "Q4 Sales: $225,000"
        };

        // The template method defines the algorithm structure
        // Subclasses only implement specific steps

        System.out.println("📊 GENERATING PDF REPORT");
        System.out.println("════════════════════════════════");
        ReportGenerator pdfReport = new PdfReportGenerator("Annual Sales Report", salesData);
        pdfReport.generateReport();

        System.out.println("📊 GENERATING HTML REPORT");
        System.out.println("════════════════════════════════");
        ReportGenerator htmlReport = new HtmlReportGenerator("Annual Sales Report", salesData);
        htmlReport.generateReport();

        System.out.println("📊 GENERATING CSV REPORT");
        System.out.println("════════════════════════════════");
        ReportGenerator csvReport = new CsvReportGenerator("Annual Sales Report", salesData);
        csvReport.generateReport();

        System.out.println("📊 GENERATING EXCEL REPORT (New Type - Easy to Add!)");
        System.out.println("════════════════════════════════");
        ReportGenerator excelReport = new ExcelReportGenerator("Annual Sales Report", salesData);
        excelReport.generateReport();

        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║  BENEFITS:                                             ║");
        System.out.println("║  ✅ Algorithm structure defined once in base class     ║");
        System.out.println("║  ✅ Subclasses only implement variant parts            ║");
        System.out.println("║  ✅ Changes to algorithm flow affect all subclasses    ║");
        System.out.println("║  ✅ Easy to add new report types (see Excel)           ║");
        System.out.println("║  ✅ Hook methods provide optional customization        ║");
        System.out.println("║  ✅ Ensures consistent process across all types        ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
}
