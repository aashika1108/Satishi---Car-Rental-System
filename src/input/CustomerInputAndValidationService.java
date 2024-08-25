package input;

import services.database.CustomerDAO;

import java.util.List;
import java.util.Scanner;

/**
 * Service class for input and validation related to customers in the Car Rental Management System.
 * Author: Amisha Nakrani
 */
public class CustomerInputAndValidationService {

    /**
     * Retrieves the status of whether the customer is removed or not based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return True if the customer is removed, false otherwise.
     */
    public static boolean isRemoved(Scanner scanner) {
        boolean isRemoved = false;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Is the customer removed (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("true")) {
                isRemoved = true;
                validInput = true;
            } else if (input.equals("false")) {
                isRemoved = false;
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }

        return isRemoved;
    }

    /**
     * Retrieves the password of a customer based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The password of the customer as a String.
     */
    public static String getPassword(Scanner scanner) {
        System.out.print("Enter customer password: ");
        String password = scanner.nextLine();
        return password;
    }

    /**
     * Retrieves the contact number of a customer based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The contact number of the customer as a long.
     */
    public static long getContactNo(Scanner scanner) {
        boolean validInput = false;
        long contactNo = 0;

        while (!validInput) {
            System.out.print("Enter customer contact number: ");
            String input = scanner.nextLine();

            // Check if input contains only digits
            if (!input.matches("\\d+")) {
                System.out.println("Invalid input! Please enter only digits.");
                continue;
            }

            // Parse the input to long
            try {
                contactNo = Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            // Check if the contact number is positive
            if (contactNo <= 0) {
                System.out.println("Invalid input! Please enter a positive number.");
                continue;
            }

            // Validation passed, set validInput to true to exit the loop
            validInput = true;
        }

        // Return the valid contact number
        return contactNo;
    }

    /**
     * Retrieves the email of a customer based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The email of the customer as a String.
     */
    public static String getEmail(Scanner scanner) {
        System.out.print("Enter customer email: ");
        String email = scanner.nextLine();
        return email;
    }

    /**
     * Retrieves the address of a customer based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The address of the customer as a String.
     */
    public static String getAddress(Scanner scanner) {
        System.out.print("Enter customer address: ");
        String address = scanner.nextLine();
        return address;
    }

    /**
     * Retrieves the last name of a customer based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The last name of the customer as a String.
     */
    public static String getLastName(Scanner scanner) {
        System.out.print("Enter customer last name: "); // Added
        String lastName = scanner.nextLine(); // Added
        return lastName;
    }

    /**
     * Retrieves the first name of a customer based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The first name of the customer as a String.
     */
    public static String getFirstName(Scanner scanner) {
        System.out.print("Enter customer first name: "); // Added
        String firstName = scanner.nextLine(); // Added
        return firstName;
    }

    /**
     * Retrieves the customer ID for creating a new customer based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The customer ID as a String.
     */
    public static String getCustomerIdForCreate(Scanner scanner) {
        boolean validInput = false;
        String customerId = null;
        while (!validInput) {
            System.out.print("Enter customer ID: ");
            customerId = scanner.nextLine();
            List<String> existingIds = CustomerDAO.getAllCustomerIds();
            if (existingIds.contains(customerId)) {
                System.out.println("The entered customer ID already exists. Please enter a unique ID.");
            } else {
                validInput = true;
            }
        }
        return customerId;
    }

    /**
     * Retrieves the customer ID for updating a customer based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The customer ID as a String.
     */
    public static String getCustomerIdForUpdate(Scanner scanner) {
        boolean validInput = false;
        String customerId = null;
        List<String> existingIds = CustomerDAO.getAllCustomerIds();
        while (!validInput) {
            System.out.print("Enter customer ID: ");
            customerId = scanner.nextLine();
            if (!existingIds.contains(customerId)) {
                System.out.println("The entered customer ID does not exist. Please enter a valid ID.");
            } else {
                validInput = true;
            }
        }
        return customerId;
    }

    /**
     * Retrieves the search term for a customer (customer_id, email, or contactno) based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The search term as a String.
     */
    public static String getSearchTermForCustomer(Scanner scanner) {
        System.out.print("Enter search term (customer_id, email, or contactno): ");
        String searchTerm = scanner.nextLine();
        return searchTerm;
    }

}
