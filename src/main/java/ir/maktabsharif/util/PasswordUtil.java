package ir.maktabsharif.util;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordUtil {
    public static String hashPassword(String plainPassword){
        return BCrypt.withDefaults().hashToString(12,plainPassword.toCharArray());

    }

    public static Boolean verifyPassword(String plainPassword,String hashPassword){
        return BCrypt.verifyer().verify(plainPassword.toCharArray(),hashPassword)
                .verified;
    }
}
