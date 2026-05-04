package after;

public class Main {
    public static void main(String[] args) {
        DatabaseBackupService backupService = new DatabaseBackupService();
        ReportService reportService = new ReportService();
        EmailService emailService = new EmailService();

        JobScheduler scheduler = new JobScheduler();

        scheduler.schedule(new BackupDatabaseCommand(backupService, "customer-db"));
        scheduler.schedule(new GenerateReportCommand(reportService, "sales"));
        scheduler.schedule(new SendEmailCommand(emailService, "ops@example.com", "Backup complete"));
        scheduler.schedule(new GenerateReportCommand(reportService, "inventory"));

        scheduler.runAll();
    }
}
