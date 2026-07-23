package after;

/**
 * AFTER: Following Single Responsibility Principle
 * 
 * We've separated concerns into different classes:
 * - User: Simple data model
 * - UserValidator: Validates user data
 * - UserRepository: Handles database operations
 * - EmailService: Sends emails
 * - EmailTemplateService: Creates email templates
 * - UserReportGenerator: Generates reports
 * - UserService: Orchestrates the workflow
 * 
 * Benefits:
 * - Each class has ONE reason to change
 * - Easy to test each component in isolation
 * - Easy to swap implementations (e.g., change email provider)
 * - Reusable components
 * - Better code organization
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== AFTER: Following Single Responsibility Principle ===\n");
        
        // Setup dependencies (in real app, use Dependency Injection framework)
        UserValidator validator = new UserValidator();
        UserRepository repository = new InMemoryUserRepository();
        EmailService emailService = new SmtpEmailService("smtp.gmail.com", 587, "noreply@company.com");
        EmailTemplateService templateService = new EmailTemplateService();
        UserReportGenerator reportGenerator = new UserReportGenerator();
        
        // Create UserService with all its dependencies
        UserService userService = new UserService(
            validator,
            repository,
            emailService,
            templateService,
            reportGenerator
        );
        
        System.out.println("1️⃣ Creating a valid user:");
        System.out.println("═══════════════════════════════════════════════════════");
        userService.createUser("001", "John Doe", "john.doe@email.com", "SecurePass123");
        
        System.out.println("\n2️⃣ Creating another user:");
        System.out.println("═══════════════════════════════════════════════════════");
        userService.createUser("002", "Jane Smith", "jane.smith@email.com", "StrongPass456");
        
        System.out.println("\n3️⃣ Attempting to create user with invalid email:");
        System.out.println("═══════════════════════════════════════════════════════");
        userService.createUser("003", "Bob Wilson", "invalid-email", "ValidPass789");
        
        System.out.println("\n4️⃣ Attempting to create user with weak password:");
        System.out.println("═══════════════════════════════════════════════════════");
        userService.createUser("004", "Alice Brown", "alice@email.com", "weak");
        
        System.out.println("\n5️⃣ Deactivating a user:");
        System.out.println("═══════════════════════════════════════════════════════");
        userService.deactivateUser("001");
        
        System.out.println("\n6️⃣ Listing all users:");
        System.out.println("═══════════════════════════════════════════════════════");
        String usersReport = reportGenerator.generateUsersListReport(userService.getAllUsers());
        System.out.println(usersReport);
        
        System.out.println("\n✅ BENEFITS OF THIS APPROACH:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("✓ Each class has ONE responsibility");
        System.out.println("✓ Easy to change email provider (just swap EmailService)");
        System.out.println("✓ Easy to test each component independently");
        System.out.println("✓ Can reuse EmailService for other entities");
        System.out.println("✓ Better code organization and maintainability");
        System.out.println("✓ Follows Single Responsibility Principle!");
        
        System.out.println("\n🎓 KEY LEARNINGS:");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("• Separate validation into its own class");
        System.out.println("• Separate persistence into repository pattern");
        System.out.println("• Separate external services (email, SMS, etc.)");
        System.out.println("• Use interfaces for flexibility and testability");
        System.out.println("• Orchestrate workflow in a service class");
    }
}
