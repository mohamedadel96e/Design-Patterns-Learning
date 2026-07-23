package before;

import java.util.ArrayDeque;
import java.util.Queue;

public class JobScheduler {
    private final DatabaseBackupService backupService;
    private final ReportService reportService;
    private final EmailService emailService;
    private final Queue<JobRequest> queue = new ArrayDeque<>();

    public JobScheduler(DatabaseBackupService backupService, ReportService reportService, EmailService emailService) {
        this.backupService = backupService;
        this.reportService = reportService;
        this.emailService = emailService;
    }

    public void schedule(JobRequest request) {
        queue.add(request);
        System.out.println("Queued job: " + request.getType());
    }

    public void runAll() {
        while (!queue.isEmpty()) {
            runNext();
        }
    }

    private void runNext() {
        JobRequest request = queue.poll();
        if (request == null) {
            System.out.println("No jobs to run.");
            return;
        }

        System.out.println("Running job: " + request.getType());

        switch (request.getType()) {
            case BACKUP_DATABASE:
                backupService.backup(request.getPrimary());
                break;
            case GENERATE_REPORT:
                reportService.generateDailyReport(request.getPrimary());
                break;
            case SEND_EMAIL:
                emailService.sendEmail(request.getPrimary(), request.getSecondary());
                break;
            default:
                throw new IllegalStateException("Unknown job type: " + request.getType());
        }
    }
}
