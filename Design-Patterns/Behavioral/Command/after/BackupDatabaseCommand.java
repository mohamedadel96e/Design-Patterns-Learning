package after;

public class BackupDatabaseCommand implements Command {
    private final DatabaseBackupService backupService;
    private final String databaseName;

    public BackupDatabaseCommand(DatabaseBackupService backupService, String databaseName) {
        this.backupService = backupService;
        this.databaseName = databaseName;
    }

    @Override
    public void execute() {
        backupService.backup(databaseName);
    }

    @Override
    public String getName() {
        return "Backup database (" + databaseName + ")";
    }
}
