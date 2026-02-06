package after;

import java.util.Date;

/**
 * RESPONSIBILITY: Report Generation
 * 
 * This class has ONE reason to change:
 * - Report format or content changes
 * 
 * Benefits:
 * - Easy to add new report types (CSV, JSON, XML, PDF)
 * - Can be reused for other entities
 * - Changes to report format don't affect business logic
 * - Easy to test report generation independently
 */
public class UserReportGenerator {
    
    public String generateUserReport(User user) {
        System.out.println("📊 [ReportGenerator] Generating user report...");
        
        StringBuilder report = new StringBuilder();
        report.append("=== USER REPORT ===\n");
        report.append("ID: ").append(user.getId()).append("\n");
        report.append("Name: ").append(user.getName()).append("\n");
        report.append("Email: ").append(user.getEmail()).append("\n");
        report.append("Status: ").append(user.isActive() ? "Active" : "Inactive").append("\n");
        report.append("Generated: ").append(new Date()).append("\n");
        report.append("==================\n");
        
        return report.toString();
    }
    
    public String generateUsersListReport(java.util.List<User> users) {
        System.out.println("📊 [ReportGenerator] Generating users list report...");
        
        StringBuilder report = new StringBuilder();
        report.append("=== USERS LIST REPORT ===\n");
        report.append("Total Users: ").append(users.size()).append("\n");
        report.append("─────────────────────────\n");
        
        for (User user : users) {
            report.append(String.format("%-10s | %-20s | %-30s | %s\n",
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.isActive() ? "Active" : "Inactive"
            ));
        }
        
        report.append("========================\n");
        return report.toString();
    }
}
