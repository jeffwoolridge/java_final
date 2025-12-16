// File: src/main/java/com/gym/util/PasswordUtil.java
package com.gym.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Utility class for password hashing and verification using BCrypt
 */
public class PasswordUtil {
    
    // Number of rounds for BCrypt hashing (higher = more secure but slower)
    private static final int BCRYPT_ROUNDS = 12;
    
    /**
     * Hash a plain text password using BCrypt
     * @param plainPassword The plain text password to hash
     * @return The hashed password
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
    }
    
    /**
     * Check if a plain text password matches a hashed password
     * @param plainPassword The plain text password to check
     * @param hashedPassword The hashed password to compare against
     * @return true if the passwords match, false otherwise
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }
        
        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (IllegalArgumentException e) {
            // Invalid hash format
            Logger.getInstance().log("Invalid password hash format: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Validate password strength
     * @param password The password to validate
     * @return true if password meets minimum requirements
     */
    public static boolean isPasswordStrong(String password) {
        if (password == null || password.length() < 6) {
            return false;
        }
        
        // Add more complex validation rules as needed
        // For now, just checking minimum length
        return true;
    }
}