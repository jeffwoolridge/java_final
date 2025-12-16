package com.app.util;

import org.mindrot.jbcrypt.BCrypt;
import com.app.util.Logger;

public class PasswordUtil {

    private static final int BCRYPT_ROUNDS = 12;

    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(BCRYPT_ROUNDS));
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || hashedPassword == null) {
            return false;
        }

        try {
            return BCrypt.checkpw(plainPassword, hashedPassword);
        } catch (IllegalArgumentException e) {
            Logger.getInstance().log("Invalid password hash format: " + e.getMessage());
            return false;
        }
    }

    public static boolean isPasswordStrong(String password) {
        return password != null && password.length() >= 6;
    }
}
