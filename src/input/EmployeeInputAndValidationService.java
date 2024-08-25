package input;

import services.database.EmployeeDAO;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

/**
 * Service class for input and validation related to employees in the Car Rental Management System.
 * Author: Arshdeep Singh
 */
public class EmployeeInputAndValidationService {

    /**
     * Retrieves the status of whether the employee is removed or not based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return True if the employee is removed, false otherwise.
     */
    public static boolean isRemoved(Scanner scanner) {
        boolean isRemoved = false;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Is the employee removed (true/false): ");
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
     * Retrieves the password of an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The password of the employee as a String.
     */
    public static String getPassword(Scanner scanner) {
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        return password;
    }

    /**
     * Retrieves whether the employee is a manager or not based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return True if the employee is a manager, false otherwise.
     */
    public static boolean isManager(Scanner scanner) {
        boolean isManager = false;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Is the employee a manager? (true/false): ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("true")) {
                isManager = true;
                validInput = true;
            } else if (input.equals("false")) {
                isManager = false;
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter 'true' or 'false'.");
            }
        }

        return isManager;
    }

    /**
     * Retrieves the hire date of an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The hire date of the employee as a Date object.
     */
    public static Date getHireDate(Scanner scanner) {
        Date hireDate = null;
        boolean validInput = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (!validInput) {
            System.out.print("Enter hire date (yyyy-MM-dd): ");
            String input = scanner.nextLine();

            try {
                java.util.Date parsedDate = dateFormat.parse(input);
                hireDate = new Date(parsedDate.getTime());
                if (hireDate.after(new java.util.Date())) {
                    System.out.println("Hire date cannot be in the future.");
                } else {
                    validInput = true;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            }
        }

        return hireDate;
    }

    /**
     * Retrieves the date of birth of an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The date of birth of the employee as a Date object.
     */
    public static Date getDateOfBirth(Scanner scanner) {
        Date dateOfBirth = null;
        boolean validInput = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        while (!validInput) {
            System.out.print("Enter date of birth (yyyy-MM-dd): ");
            String input = scanner.nextLine();

            try {
                java.util.Date parsedDate = dateFormat.parse(input);
                dateOfBirth = new Date(parsedDate.getTime());
                java.util.Date currentDate = new java.util.Date();
                if (dateOfBirth.after(currentDate)) {
                    System.out.println("Date of birth cannot be in the future.");
                } else {
                    validInput = true;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter the date in the format yyyy-MM-dd.");
            }
        }

        return dateOfBirth;
    }

    /**
     * Retrieves the phone number of an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The phone number of the employee as a long.
     */
    public static long getPhoneNumber(Scanner scanner) {
        boolean validInput = false;
        long phoneNumber = 0;

        while (!validInput) {
            System.out.print("Enter phone number: ");
            String input = scanner.nextLine();

            // Check if the input contains only digits and is not negative
            if (input.matches("\\d+") && !input.contains(".") && Long.parseLong(input) >= 0) {
                phoneNumber = Long.parseLong(input);
                validInput = true;
            } else {
                System.out.println("Invalid input. Please enter a valid non-negative phone number (digits only).");
            }
        }

        return phoneNumber;
    }

    /**
     * Retrieves the email of an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The email of the employee as a String.
     */
    public static String getEmail(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        return email;
    }

    /**
     * Retrieves the address of an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The address of the employee as a String.
     */
    public static String getAddress(Scanner scanner) {
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        return address;
    }

    /**
     * Retrieves the last name of an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The last name of the employee as a String.
     */
    public static String getLastName(Scanner scanner) {
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        return lastName;
    }

    /**
     * Retrieves the first name of an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The first name of the employee as a String.
     */
    public static String getFirstName(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        return firstName;
    }

    /**
     * Retrieves the employee ID for creating a new employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The employee ID as a String.
     */
    public static String getEmployeeIdForCreate(Scanner scanner) {
        boolean isValidId = false;
        String employeeId = "";

        // Retrieve the list of existing employee IDs
        List<String> existingEmployeeIds = EmployeeDAO.getAllEmployeeIds();

        while (!isValidId) {
            System.out.print("Enter employee ID: ");
            employeeId = scanner.nextLine();

            // Check if the entered employee ID already exists
            if (!existingEmployeeIds.contains(employeeId)) {
                isValidId = true;
            } else {
                System.out.println("Employee ID already exists. Please enter a different one.");
            }
        }

        return employeeId;
    }

    /**
     * Retrieves the employee ID for updating or removing an employee based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The employee ID as a String.
     */
    public static String getEmployeeIdForUpdateOrRemove(Scanner scanner) {
        boolean isValidId = false;
        String employeeId = "";

        // Retrieve the list of existing employee IDs
        List<String> existingEmployeeIds = EmployeeDAO.getAllEmployeeIds();

        while (!isValidId) {
            System.out.print("Enter employee ID: ");
            employeeId = scanner.nextLine();

            // Check if the entered employee ID exists
            if (existingEmployeeIds.contains(employeeId)) {
                isValidId = true;
            } else {
                System.out.println("Employee ID does not exist. Please enter a valid one.");
            }
        }

        return employeeId;
    }

    /**
     * Retrieves the search term for an employee (employee_id, email, or phone number) based on user input.
     *
     * @param scanner The Scanner object to read user input from.
     * @return The search term as a String.
     */
    public static String getSearchTermForEmployee(Scanner scanner) {
        System.out.print("Enter search term (employee_id, email, or phone number): ");
        String searchTerm = scanner.nextLine();
        return searchTerm;
    }
}
