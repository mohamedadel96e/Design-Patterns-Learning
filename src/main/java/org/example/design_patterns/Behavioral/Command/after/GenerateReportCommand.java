package org.example.design_patterns.Behavioral.Command.after;

public class GenerateReportCommand implements Command {
    private final ReportService reportService;
    private final String reportName;

    public GenerateReportCommand(ReportService reportService, String reportName) {
        this.reportService = reportService;
        this.reportName = reportName;
    }

    @Override
    public void execute() {
        reportService.generateDailyReport(reportName);
    }

    @Override
    public String getName() {
        return "Generate report (" + reportName + ")";
    }
}
