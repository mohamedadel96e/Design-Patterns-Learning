package before;

/**
 * BEFORE: Violating Single Responsibility Principle
 * 
 * The UserManager class is doing TOO MANY THINGS:
 * - Data validation
 * - Database operations
 * - Email notifications
 * - Report generation
 * - Business logic
 * 
 * This leads to:
 * - Hard to maintain (change in one area affects everything)
 * - Hard to test (can't test parts in isolation)
 * - Hard to reuse (email logic tied to user management)
 * - High coupling (everything depends on everything)
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== BEFORE: Violating Single Responsibility Principle ===\n");
        
        UserManager userManager = new UserManager();
        
        System.out.println("1️⃣ Creating a valid user:");
        System.out.println("─────────────────────────────────────────────────────");
        userManager.createUser("001", "John Doe", "john.doe@email.com", "SecurePass123");
        
        System.out.println("\n2️⃣ Creating another user:");
        System.out.println("─────────────────────────────────────────────────────");
        userManager.createUser("002", "Jane Smith", "jane.smith@email.com", "StrongPass456");
        
        System.out.println("\n3️⃣ Attempting to create user with invalid email:");
        System.out.println("─────────────────────────────────────────────────────");
        userManager.createUser("003", "Bob Wilson", "invalid-email", "ValidPass789");
        
        System.out.println("\n4️⃣ Attempting to create user with weak password:");
        System.out.println("─────────────────────────────────────────────────────");
        userManager.createUser("004", "Alice Brown", "alice@email.com", "weak");
        
        System.out.println("\n5️⃣ Deactivating a user:");
        System.out.println("─────────────────────────────────────────────────────");
        userManager.deactivateUser("001");
        
        System.out.println("\n6️⃣ Listing all users:");
        System.out.println("─────────────────────────────────────────────────────");
        for (User user : userManager.getAllUsers()) {
            System.out.println(user);
        }
        
        System.out.println("\n❌ PROBLEMS WITH THIS APPROACH:");
        System.out.println("════════════════════════════════════════════════════");
        System.out.println("• UserManager has MULTIPLE reasons to change");
        System.out.println("• Can't easily change email provider without touching user logic");
        System.out.println("• Can't test validation without database/email dependencies");
        System.out.println("• Can't reuse email service for other entities");
        System.out.println("• Hard to maintain as code grows");
        System.out.println("• Violates Single Responsibility Principle!");
    }
}
