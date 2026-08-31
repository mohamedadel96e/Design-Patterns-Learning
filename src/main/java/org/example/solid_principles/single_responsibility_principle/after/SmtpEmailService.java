package org.example.solid_principles.single_responsibility_principle.after;

/**
 * RESPONSIBILITY: Email Notifications (SMTP Implementation)
 * 
 * This class has ONE reason to change:
 * - Email service provider or configuration changes
 * 
 * Benefits:
 * - Easy to test in isolation (mock email sending)
 * - Can be easily swapped with SendGrid, AWS SES, etc.
 * - Changes to email provider don't affect business logic
 * - Can be reused for other entities (orders, notifications, etc.)
 */
public class SmtpEmailService implements EmailService {
    private String smtpHost;
    private int smtpPort;
    private String fromAddress;
    
    public SmtpEmailService(String smtpHost, int smtpPort, String fromAddress) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
        this.fromAddress = fromAddress;
    }
    
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("📧 [EmailService] Sending email...");
        System.out.println("   From: " + fromAddress);
        System.out.println("   To: " + to);
        System.out.println("   Subject: " + subject);
        System.out.println("   Server: " + smtpHost + ":" + smtpPort);
        
        // In real implementation:
        // - Configure SMTP session
        // - Create MIME message
        // - Send via JavaMail API
        
        System.out.println("✅ [EmailService] Email sent successfully");
    }
}
