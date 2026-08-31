package org.example.solid_principles.single_responsibility_principle.before;

import java.util.*;
import java.util.regex.*;

/**
 * PROBLEM: This class violates the Single Responsibility Principle!
 * 
 * It has MULTIPLE reasons to change:
 * 1. Business logic changes (user management rules)
 * 2. Database schema changes (connection, queries)
 * 3. Email service changes (SMTP configuration, templates)
 * 4. Validation logic changes (new validation rules)
 * 5. Report format changes (CSV, JSON, XML)
 * 
 * This makes the class:
 * - Hard to maintain
 * - Hard to test
 * - Tightly coupled
 * - Low cohesion
 */
public class UserManager {
    private Map<String, User> userDatabase = new HashMap<>();
    
    // Database configuration
    private String dbUrl = "jdbc:mysql://localhost:3306/mydb";
    private String dbUser = "root";
    private String dbPassword = "password";
    
    // Email configuration
    private String smtpHost = "smtp.gmail.com";
    private int smtpPort = 587;
    private String emailFrom = "noreply@company.com";
    
    /**
     * Creates a new user - but does TOO MANY THINGS!
     */
    public void createUser(String id, String name, String email, String password) {
        // 1. VALIDATION - First responsibility
        if (!validateUserData(name, email, password)) {
            System.out.println("❌ Validation failed!");
            return;
        }
        
        // 2. BUSINESS LOGIC - Second responsibility
        User user = new User(id, name, email, password);
        
        // 3. DATABASE OPERATIONS - Third responsibility
        saveToDatabase(user);
        
        // 4. EMAIL NOTIFICATIONS - Fourth responsibility
        sendWelcomeEmail(user);
        
        // 5. LOGGING/REPORTING - Fifth responsibility
        generateUserReport(user);
        
        System.out.println("✅ User created successfully: " + user);
    }
    
    /**
     * RESPONSIBILITY #1: Data Validation
     * This should be in a separate Validator class!
     */
    private boolean validateUserData(String name, String email, String password) {
        // Validate name
        if (name == null || name.trim().isEmpty()) {
            System.out.println("❌ Name cannot be empty");
            return false;
        }
        
        if (name.length() < 2 || name.length() > 50) {
            System.out.println("❌ Name must be between 2 and 50 characters");
            return false;
        }
        
        // Validate email
        if (email == null || email.trim().isEmpty()) {
            System.out.println("❌ Email cannot be empty");
            return false;
        }
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            System.out.println("❌ Invalid email format");
            return false;
        }
        
        // Validate password
        if (password == null || password.length() < 8) {
            System.out.println("❌ Password must be at least 8 characters");
            return false;
        }
        
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("❌ Password must contain at least one uppercase letter");
            return false;
        }
        
        if (!password.matches(".*[a-z].*")) {
            System.out.println("❌ Password must contain at least one lowercase letter");
            return false;
        }
        
        if (!password.matches(".*\\d.*")) {
            System.out.println("❌ Password must contain at least one digit");
            return false;
        }
        
        return true;
    }
    
    /**
     * RESPONSIBILITY #2: Database Operations
     * This should be in a separate Repository class!
     */
    private void saveToDatabase(User user) {
        System.out.println("💾 Saving to database...");
        
        // Simulating database connection and save
        try {
            // In real code, this would use JDBC:
            // Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            // PreparedStatement stmt = conn.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?)");
            // stmt.setString(1, user.getId());
            // stmt.setString(2, user.getName());
            // stmt.setString(3, user.getEmail());
            // stmt.setString(4, user.getPassword());
            // stmt.setBoolean(5, user.isActive());
            // stmt.executeUpdate();
            
            userDatabase.put(user.getId(), user);
            System.out.println("💾 User saved to database");
            
        } catch (Exception e) {
            System.out.println("❌ Database error: " + e.getMessage());
        }
    }
    
    /**
     * RESPONSIBILITY #3: Email Notifications
     * This should be in a separate EmailService class!
     */
    private void sendWelcomeEmail(User user) {
        System.out.println("📧 Sending welcome email...");
        
        // Email template logic (should be separate!)
        String subject = "Welcome to Our Platform!";
        String body = "Dear " + user.getName() + ",\n\n" +
                     "Welcome to our platform! We're excited to have you.\n\n" +
                     "Your account has been created successfully.\n" +
                     "Email: " + user.getEmail() + "\n\n" +
                     "Best regards,\nThe Team";
        
        // SMTP configuration and sending (should be separate!)
        try {
            // In real code, this would use JavaMail API:
            // Properties props = new Properties();
            // props.put("mail.smtp.host", smtpHost);
            // props.put("mail.smtp.port", smtpPort);
            // Session session = Session.getInstance(props);
            // MimeMessage message = new MimeMessage(session);
            // message.setFrom(new InternetAddress(emailFrom));
            // message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            // message.setSubject(subject);
            // message.setText(body);
            // Transport.send(message);
            
            System.out.println("📧 Email sent to: " + user.getEmail());
            System.out.println("   Subject: " + subject);
            
        } catch (Exception e) {
            System.out.println("❌ Email error: " + e.getMessage());
        }
    }
    
    /**
     * RESPONSIBILITY #4: Report Generation
     * This should be in a separate ReportGenerator class!
     */
    private void generateUserReport(User user) {
        System.out.println("📊 Generating user report...");
        
        // Report formatting logic (should be separate!)
        StringBuilder report = new StringBuilder();
        report.append("=== USER REPORT ===\n");
        report.append("ID: ").append(user.getId()).append("\n");
        report.append("Name: ").append(user.getName()).append("\n");
        report.append("Email: ").append(user.getEmail()).append("\n");
        report.append("Status: ").append(user.isActive() ? "Active" : "Inactive").append("\n");
        report.append("Created: ").append(new Date()).append("\n");
        report.append("==================\n");
        
        System.out.println("📊 Report generated:\n" + report.toString());
    }
    
    /**
     * Deactivates a user - also does too many things!
     */
    public void deactivateUser(String userId) {
        User user = userDatabase.get(userId);
        if (user != null) {
            user.setActive(false);
            
            // Database update
            saveToDatabase(user);
            
            // Send deactivation email
            sendDeactivationEmail(user);
            
            System.out.println("✅ User deactivated: " + userId);
        } else {
            System.out.println("❌ User not found: " + userId);
        }
    }
    
    private void sendDeactivationEmail(User user) {
        System.out.println("📧 Sending deactivation email to: " + user.getEmail());
        // More email logic that should be elsewhere...
    }
    
    public User getUser(String userId) {
        return userDatabase.get(userId);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(userDatabase.values());
    }
}
