package org.example.design_patterns.Behavioral.Command.before;

public class DatabaseBackupService {
    public void backup(String databaseName) {
        System.out.println("Backing up database: " + databaseName);
    }
}
