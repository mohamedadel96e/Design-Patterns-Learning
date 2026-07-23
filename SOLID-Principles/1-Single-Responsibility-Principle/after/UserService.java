package after;

/**
 * RESPONSIBILITY: User Business Logic & Orchestration
 * 
 * This class has ONE reason to change:
 * - Business rules for user management change
 * 
 * This class orchestrates the workflow but delegates specific responsibilities:
 * - Validation -> UserValidator
 * - Persistence -> UserRepository
 * - Email -> EmailService & EmailTemplateService
 * - Reports -> UserReportGenerator
 * 
 * Benefits:
 * - Focuses only on business logic
 * - Easy to test with mocks
 * - Dependencies can be easily swapped
 * - Follows Single Responsibility Principle
 * - Follows Dependency Inversion Principle (depends on abstractions)
 */
public class UserService {
    private UserValidator validator;
    private UserRepository repository;
    private EmailService emailService;
    private EmailTemplateService templateService;
    private UserReportGenerator reportGenerator;
    
    public UserService(
        UserValidator validator,
        UserRepository repository,
        EmailService emailService,
        EmailTemplateService templateService,
        UserReportGenerator reportGenerator
    ) {
        this.validator = validator;
        this.repository = repository;
        this.emailService = emailService;
        this.templateService = templateService;
        this.reportGenerator = reportGenerator;
    }
    
    /**
     * Creates a new user - orchestrates the workflow
     */
    public boolean createUser(String id, String name, String email, String password) {
        System.out.println("👤 [UserService] Creating user: " + name);
        
        // 1. Validate
        ValidationResult validationResult = validator.validate(name, email, password);
        if (!validationResult.isValid()) {
            System.out.println("❌ [UserService] Validation failed: " + validationResult.getErrorMessage());
            return false;
        }
        
        // 2. Create user
        User user = new User(id, name, email, password);
        
        // 3. Save to repository
        repository.save(user);
        
        // 4. Send welcome email
        String emailBody = templateService.createWelcomeEmail(user);
        String emailSubject = templateService.getWelcomeSubject();
        emailService.sendEmail(user.getEmail(), emailSubject, emailBody);
        
        // 5. Generate report
        String report = reportGenerator.generateUserReport(user);
        System.out.println(report);
        
        System.out.println("✅ [UserService] User created successfully: " + user.getId());
        return true;
    }
    
    /**
     * Deactivates a user
     */
    public boolean deactivateUser(String userId) {
        System.out.println("👤 [UserService] Deactivating user: " + userId);
        
        User user = repository.findById(userId);
        if (user == null) {
            System.out.println("❌ [UserService] User not found: " + userId);
            return false;
        }
        
        user.setActive(false);
        repository.update(user);
        
        // Send deactivation email
        String emailBody = templateService.createDeactivationEmail(user);
        String emailSubject = templateService.getDeactivationSubject();
        emailService.sendEmail(user.getEmail(), emailSubject, emailBody);
        
        System.out.println("✅ [UserService] User deactivated: " + userId);
        return true;
    }
    
    public User getUser(String userId) {
        return repository.findById(userId);
    }
    
    public java.util.List<User> getAllUsers() {
        return repository.findAll();
    }
}
