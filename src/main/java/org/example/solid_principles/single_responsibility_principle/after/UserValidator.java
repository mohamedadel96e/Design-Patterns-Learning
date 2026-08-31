package org.example.solid_principles.single_responsibility_principle.after;

import java.util.regex.Pattern;

/**
 * RESPONSIBILITY: User Data Validation
 * 
 * This class has ONE reason to change:
 * - Validation rules change
 * 
 * Benefits:
 * - Easy to test in isolation
 * - Can be reused for different contexts (registration, profile update, etc.)
 * - Changes to validation don't affect other components
 */
public class UserValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 50;
    private static final int MIN_PASSWORD_LENGTH = 8;
    
    /**
     * Validates user data and returns validation result
     */
    public ValidationResult validate(String name, String email, String password) {
        ValidationResult result = new ValidationResult();
        
        validateName(name, result);
        validateEmail(email, result);
        validatePassword(password, result);
        
        return result;
    }
    
    private void validateName(String name, ValidationResult result) {
        if (name == null || name.trim().isEmpty()) {
            result.addError("Name cannot be empty");
            return;
        }
        
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            result.addError("Name must be between " + MIN_NAME_LENGTH + " and " + MAX_NAME_LENGTH + " characters");
        }
    }
    
    private void validateEmail(String email, ValidationResult result) {
        if (email == null || email.trim().isEmpty()) {
            result.addError("Email cannot be empty");
            return;
        }
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            result.addError("Invalid email format");
        }
    }
    
    private void validatePassword(String password, ValidationResult result) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            result.addError("Password must be at least " + MIN_PASSWORD_LENGTH + " characters");
            return;
        }
        
        if (!password.matches(".*[A-Z].*")) {
            result.addError("Password must contain at least one uppercase letter");
        }
        
        if (!password.matches(".*[a-z].*")) {
            result.addError("Password must contain at least one lowercase letter");
        }
        
        if (!password.matches(".*\\d.*")) {
            result.addError("Password must contain at least one digit");
        }
    }
}
