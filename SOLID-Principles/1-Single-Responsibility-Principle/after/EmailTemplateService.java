package after;

/**
 * RESPONSIBILITY: Email Template Generation
 * 
 * This class has ONE reason to change:
 * - Email templates or formatting changes
 * 
 * Benefits:
 * - Centralizes email templates
 * - Easy to update email content
 * - Can support multiple languages
 * - Can load templates from external files
 */
public class EmailTemplateService {
    
    public String createWelcomeEmail(User user) {
        return "Dear " + user.getName() + ",\n\n" +
               "Welcome to our platform! We're excited to have you.\n\n" +
               "Your account has been created successfully.\n" +
               "Email: " + user.getEmail() + "\n\n" +
               "Best regards,\n" +
               "The Team";
    }
    
    public String createDeactivationEmail(User user) {
        return "Dear " + user.getName() + ",\n\n" +
               "Your account has been deactivated.\n\n" +
               "If you didn't request this or want to reactivate your account,\n" +
               "please contact our support team.\n\n" +
               "Best regards,\n" +
               "The Team";
    }
    
    public String getWelcomeSubject() {
        return "Welcome to Our Platform!";
    }
    
    public String getDeactivationSubject() {
        return "Account Deactivation Notice";
    }
}
