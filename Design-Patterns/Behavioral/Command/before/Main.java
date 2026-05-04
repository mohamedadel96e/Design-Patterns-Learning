package before;

public class Main {
    public static void main(String[] args) {
        DatabaseBackupService backupService = new DatabaseBackupService();
        ReportService reportService = new ReportService();
        EmailService emailService = new EmailService();

        JobScheduler scheduler = new JobScheduler(backupService, reportService, emailService);

        scheduler.schedule(new JobRequest(JobType.BACKUP_DATABASE, "customer-db", null));
        scheduler.schedule(new JobRequest(JobType.GENERATE_REPORT, "sales", null));
        scheduler.schedule(new JobRequest(JobType.SEND_EMAIL, "ops@example.com", "Backup complete"));
        scheduler.schedule(new JobRequest(JobType.GENERATE_REPORT, "inventory", null));

        scheduler.runAll();
    }
}
