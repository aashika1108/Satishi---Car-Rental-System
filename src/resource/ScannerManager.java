package resource;

import java.util.Scanner;

/**
 * Utility class to manage a single Scanner instance for input.
 * Author: Ghazala Anjum
 */
public class ScannerManager {
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Returns the Scanner instance.
     *
     * @return The Scanner instance for input.
     */
    public static Scanner getScanner() {
        return scanner;
    }

    /**
     * Closes the Scanner instance.
     */
    public static void closeScanner() {
        scanner.close();
    }
}
