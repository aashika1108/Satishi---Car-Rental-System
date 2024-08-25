package input;

import services.database.CustomerDAO;
import services.database.EmployeeDAO;

import java.util.List;
import java.util.Scanner;

/**
 * Service class for input and validation related to user login in the Car Rental Management System.
 * Author: Ghazala Anjum
 */
public class LoginInputAndValidationService {

    /**
     * Retrieves the user's choice for login type based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The choice as an integer (1 or 2).
     */
    public static int getChoice(Scanner scanner) {
        int choice = 0;
        boolean isValidChoice = false;

        while (!isValidChoice) {
            System.out.print("Enter your choice (1 or 2): ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    isValidChoice = true;
                } else {
                    System.out.println("Invalid choice. Please enter either 1 or 2.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume invalid input
            }
        }

        scanner.nextLine(); // Consume newline after consuming the integer choice
        return choice;
    }

    /**
     * Retrieves the customer's password based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The password entered by the customer.
     */
    public static String getCustomerPassword(Scanner scanner) {
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        return password;
    }

    /**
     * Retrieves the customer's ID based on user input and validates it.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The validated customer ID.
     */
    public static String getCustomerId(Scanner scanner) {
        List<String> existingCustomerIds = CustomerDAO.getAllCustomerIds();

        String id = "";
        boolean isValidId = false;

        while (!isValidId) {
            System.out.print("Please enter your customer ID: ");
            id = scanner.nextLine();

            // Check if the entered customer ID exists in the list of existing customer IDs
            if (existingCustomerIds.contains(id)) {
                isValidId = true;
            } else {
                System.out.println("Invalid customer ID. Please enter a valid ID.");
            }
        }

        return id;
    }

    /**
     * Retrieves the employee's password based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The password entered by the employee.
     */
    public static String getEmployeePassword(Scanner scanner) {
        System.out.println("Please enter your password: ");
        String password = scanner.nextLine();
        return password;
    }

    /**
     * Retrieves the employee's ID based on user input and validates it.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The validated employee ID.
     */
    public static String getEmployeeId(Scanner scanner) {
        List<String> existingEmployeeIds = EmployeeDAO.getAllEmployeeIds();

        String id = "";
        boolean isValidId = false;

        while (!isValidId) {
            System.out.print("Please enter your employee ID: ");
            id = scanner.nextLine();

            // Check if the entered employee ID exists in the list of existing employee IDs
            if (existingEmployeeIds.contains(id)) {
                isValidId = true;
            } else {
                System.out.println("Invalid employee ID. Please enter a valid ID.");
            }
        }

        return id;
    }

}
