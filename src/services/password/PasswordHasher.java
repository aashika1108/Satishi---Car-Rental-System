package services.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Utility class for hashing passwords and verifying hashed passwords.
 * Author: Ghazala Anjum
 */
public class PasswordHasher {

    /**
     * Hashes a password using SHA-256 algorithm with a random salt.
     *
     * @param password The password to hash.
     * @return The hashed password.
     */
    public static String hashPassword(String password) {
        try {
            // Generate a random salt
            byte[] salt = generateSalt();
            // Append the salt to the password
            byte[] saltedPassword = appendSalt(password.getBytes(), salt);
            // Hash the salted password
            byte[] hashedPassword = hash(saltedPassword);
            // Combine the salt and hashed password and encode as base64
            return Base64.getEncoder().encodeToString(combineSaltAndHash(salt, hashedPassword));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generates a random salt for password hashing.
     *
     * @return The generated salt.
     */
    private static byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }

    /**
     * Appends a salt to the password.
     *
     * @param password The password bytes.
     * @param salt     The salt bytes.
     * @return The salted password.
     */
    private static byte[] appendSalt(byte[] password, byte[] salt) {
        byte[] saltedPassword = new byte[password.length + salt.length];
        System.arraycopy(password, 0, saltedPassword, 0, password.length);
        System.arraycopy(salt, 0, saltedPassword, password.length, salt.length);
        return saltedPassword;
    }

    /**
     * Hashes the given data using SHA-256 algorithm.
     *
     * @param data The data to hash.
     * @return The hashed data.
     * @throws NoSuchAlgorithmException If the SHA-256 algorithm is not available.
     */
    private static byte[] hash(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(data);
    }

    /**
     * Combines the salt and hashed password into a single array.
     *
     * @param salt The salt bytes.
     * @param hash The hashed password bytes.
     * @return The combined salt and hashed password bytes.
     */
    private static byte[] combineSaltAndHash(byte[] salt, byte[] hash) {
        byte[] combined = new byte[salt.length + hash.length];
        System.arraycopy(salt, 0, combined, 0, salt.length);
        System.arraycopy(hash, 0, combined, salt.length, hash.length);
        return combined;
    }

    /**
     * Verifies a password against a hashed password.
     *
     * @param password       The input password to verify.
     * @param hashedPassword The hashed password to compare against.
     * @return True if the passwords match, false otherwise.
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        try {
            // Decode the base64 encoded hashed password
            byte[] decodedHash = Base64.getDecoder().decode(hashedPassword);
            // Extract the salt and hashed password from the combined array
            byte[] salt = new byte[16];
            byte[] storedHash = new byte[decodedHash.length - salt.length];
            System.arraycopy(decodedHash, 0, salt, 0, salt.length);
            System.arraycopy(decodedHash, salt.length, storedHash, 0, storedHash.length);
            // Compute the hash of the input password with the extracted salt
            byte[] inputHash = hash(appendSalt(password.getBytes(), salt));
            // Compare the computed hash with the stored hash
            return MessageDigest.isEqual(storedHash, inputHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return false;
        }
    }

}
