package org.example.solid_principles.dependency_inversion_principle.before;

/**
 * Low-level module: Email sending implementation
 */
public class EmailSender {
    private String smtpServer;
    private int port;
    
    public EmailSender(String smtpServer, int port) {
        this.smtpServer = smtpServer;
        this.port = port;
    }
    
    public void sendEmail(String recipient, String subject, String message) {
        System.out.println("📧 Sending email...");
        System.out.println("   Server: " + smtpServer + ":" + port);
        System.out.println("   To: " + recipient);
        System.out.println("   Subject: " + subject);
        System.out.println("   Message: " + message);
        System.out.println("✅ Email sent successfully!");
    }
}
