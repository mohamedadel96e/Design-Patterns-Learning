package after;

/**
 * Interface for email operations
 * 
 * Using an interface allows us to:
 * - Switch between different email providers (SMTP, SendGrid, AWS SES, etc.)
 * - Test with mock implementations
 * - Follow Dependency Inversion Principle
 */
public interface EmailService {
    void sendEmail(String to, String subject, String body);
}
